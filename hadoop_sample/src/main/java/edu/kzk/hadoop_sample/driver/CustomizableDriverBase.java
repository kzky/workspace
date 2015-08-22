package edu.kzk.hadoop_sample.driver;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

/**
 * @author kzk
 *
 */
public abstract class CustomizableDriverBase implements Driver{ //extends Configured implements Tool {
	// logger
	Logger logger = Logger.getLogger(CustomizableDriverBase.class);

	// Job related property
	private String jobName;
	private String inputPath;
	private String outputDir;

	// outputkey and outputvalue property of mapper and reducer
	private String MaperOutputKeyClassName;
	private String MaperOutputValueClassName;
	private String ReducerOutputKeyClassName;
	private String ReducerOutputValueClassName;

	// input and output format ClassName
	private String inputFormatClassName;
	private String outputFormatClassName;

	// Mapper and Reducer property
	private String mapperClassName;
	private String partitionerClassName = "org.apache.hadoop.mapreduce.lib.partition.HashPartitioner";
	private String combinerClassName;
	private String reducerClassName;
	private int numReducer;

	// SplitMapper property
	private String splitterClassName;
	private String indices;
	private String delimiter;

	// Site property
	private String yarnAppMapreduceAmResourceMb;
	private String mapreduceJobMaps;
	private String mapreduceJobReduces;

	// Map property/Reduce property
	private String mapreduceTaskIoSortMb;
	private String mapreduceTaskIoSortFactor;

	private String mapreduceMapMemoryMb;
	private String mapreduceMapJavaOpts;
	private String mapreduceMapSortSpillPercent;

	private String mapreduceReduceMemoryMb;
	private String mapreduceReduceJavaOpts;
	private String mapreduceReduceMergeInmemThreshold;
	private String mapreduceReduceInputBufferPercent;
	private String mapreduceReduceShuffleInputBufferPercent;
	private String mapreduceReduceShuffleMergePercent;

	// Job execution time
	long startTime;
	long endTime;

	public CustomizableDriverBase() {
	}

	public Job initJob(String[] args) {

		try {
			// configuration
			Configuration config = new Configuration();
			setSiteConfig(config);
			setMapReduceConfig(config);
			setSplitterConfig(config);			

			// Job instance			
			Job job = Job.getInstance(config, jobName);

			// set jar class
			job.setJarByClass(getClass());

			// set input and output path
			setInputPaths(job, config, inputPath);
			FileOutputFormat.setOutputPath(job, new Path(outputDir));

			// set input format
			job.setInputFormatClass((Class<? extends InputFormat>) Class.forName(inputFormatClassName));

			// set mapper			
			job.setMapperClass((Class<? extends Mapper>) Class.forName(mapperClassName));

			// set mapper output key and value.			
			job.setMapOutputKeyClass(Class.forName(MaperOutputKeyClassName));
			job.setMapOutputKeyClass(Class.forName(MaperOutputValueClassName));

			// set partitioner
			job.setPartitionerClass((Class<? extends Partitioner>) Class.forName(partitionerClassName));

			// set combiner
			job.setCombinerClass((Class<? extends Reducer>) Class.forName(reducerClassName));

			// set reducer
			job.setNumReduceTasks(numReducer);
			job.setReducerClass((Class<? extends Reducer>) Class.forName(reducerClassName));

			// set output key and output value class
			job.setOutputKeyClass(Class.forName(ReducerOutputKeyClassName));
			job.setOutputValueClass(Class.forName(ReducerOutputValueClassName));

			// set output format
			job.setInputFormatClass((Class<? extends InputFormat>) Class.forName(outputFormatClassName));

			// print config
			printConfig(config);

			return job;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setSplitterConfig(Configuration config) {
		config.set("splitterClassName", splitterClassName);
		config.set("indices", indices);
		config.set("delimiter", delimiter);
	}

	public int run(String[] args) {
		Job job = initJob(args);
		boolean ret = false;
		startTime = System.currentTimeMillis();
		try {
			ret = job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		logger.info("Job execution time: " + (endTime - startTime)/1000 + " [s].");

		return ret ? 0 : 1;
	}

	private void setInputPaths(Job job, Configuration config,String inputPath) {
		try {
			FileSystem fs = FileSystem.get(config);
			for (FileStatus fStatus: fs.listStatus(new Path(inputPath))) {
				FileInputFormat.addInputPath(job, fStatus.getPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param config
	 */
	private void printConfig(Configuration config) {

		logger.info("###############################");
		logger.info("### Configuration Paremters ###");
		logger.info("###############################");		
		for (Entry<String, String> entry : config) {
			logger.info(entry.getKey() + ": " + entry.getValue());			
		}
		logger.info("###############################");
	}

	/**
	 * site config
	 * @param config
	 */
	private void setSiteConfig(Configuration config) {
		config.set("yarn.app.mapreduce.am.resource.mb", yarnAppMapreduceAmResourceMb);
		config.set("mapreduce.job.maps", mapreduceJobMaps);
		config.set("mapreduce.job.reduces", mapreduceJobReduces);
	}

	/**
	 * map-side config
	 * @param config
	 */
	private void setMapReduceConfig(Configuration config) {
		config.set("mapreduce.task.io.sort.mb", mapreduceTaskIoSortMb);
		config.set("mapreduce.task.io.sort.factor", mapreduceTaskIoSortFactor);
		config.set("mapreduce.map.memory.mb", mapreduceMapMemoryMb);
		config.set("mapreduce.map.java.opts", mapreduceMapJavaOpts);
		config.set("mapreduce.map.sort.spill.percent", mapreduceMapSortSpillPercent);
		config.set("mapreduce.reduce.memory.mb", mapreduceReduceMemoryMb);
		config.set("mapreduce.reduce.java.opts", mapreduceReduceJavaOpts);
		config.set("mapreduce.reduce.merge.inmem.threshold", mapreduceReduceMergeInmemThreshold);
		config.set("mapreduce.reduce.input.buffer.percent", mapreduceReduceInputBufferPercent);
		config.set("mapreduce.reduce.shuffle.input.buffer.percent", mapreduceReduceShuffleInputBufferPercent);
		config.set("mapreduce.reduce.shuffle.merge.percent", mapreduceReduceShuffleMergePercent);
	}

	// getters and setters
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setIOPath(String inputPath, String outputDir) {
		setInputPath(inputPath);
		setOutputDir(outputDir);
	}

	public String getMaperOutputKeyClassName() {
		return MaperOutputKeyClassName;
	}

	public void setMaperOutputKeyClassName(String maperOutputKeyClassName) {
		MaperOutputKeyClassName = maperOutputKeyClassName;
	}

	public String getMaperOutputValueClassName() {
		return MaperOutputValueClassName;
	}

	public void setMaperOutputValueClassName(String maperOutputValueClassName) {
		MaperOutputValueClassName = maperOutputValueClassName;
	}

	public String getYarnAppMapreduceAmResourceMb() {
		return yarnAppMapreduceAmResourceMb;
	}

	public void setYarnAppMapreduceAmResourceMb(String yarnAppMapreduceAmResourceMb) {
		this.yarnAppMapreduceAmResourceMb = yarnAppMapreduceAmResourceMb;
	}

	public String getMapreduceJobMaps() {
		return mapreduceJobMaps;
	}

	public void setMapreduceJobMaps(String mapreduceJobMaps) {
		this.mapreduceJobMaps = mapreduceJobMaps;
	}

	public String getMapreduceJobReduces() {
		return mapreduceJobReduces;
	}

	public void setMapreduceJobReduces(String mapreduceJobReduces) {
		this.mapreduceJobReduces = mapreduceJobReduces;
	}

	public String getMapreduceTaskIoSortMb() {
		return mapreduceTaskIoSortMb;
	}

	public void setMapreduceTaskIoSortMb(String mapreduceTaskIoSortMb) {
		this.mapreduceTaskIoSortMb = mapreduceTaskIoSortMb;
	}

	public String getMapreduceTaskIoSortFactor() {
		return mapreduceTaskIoSortFactor;
	}

	public void setMapreduceTaskIoSortFactor(String mapreduceTaskIoSortFactor) {
		this.mapreduceTaskIoSortFactor = mapreduceTaskIoSortFactor;
	}

	public String getMapreduceMapMemoryMb() {
		return mapreduceMapMemoryMb;
	}

	public void setMapreduceMapMemoryMb(String mapreduceMapMemoryMb) {
		this.mapreduceMapMemoryMb = mapreduceMapMemoryMb;
	}

	public String getMapreduceMapJavaOpts() {
		return mapreduceMapJavaOpts;
	}

	public void setMapreduceMapJavaOpts(String mapreduceMapJavaOpts) {
		this.mapreduceMapJavaOpts = mapreduceMapJavaOpts;
	}

	public String getMapreduceMapSortSpillPercent() {
		return mapreduceMapSortSpillPercent;
	}

	public void setMapreduceMapSortSpillPercent(String mapreduceMapSortSpillPercent) {
		this.mapreduceMapSortSpillPercent = mapreduceMapSortSpillPercent;
	}

	public String getMapreduceReduceMemoryMb() {
		return mapreduceReduceMemoryMb;
	}

	public void setMapreduceReduceMemoryMb(String mapreduceReduceMemoryMb) {
		this.mapreduceReduceMemoryMb = mapreduceReduceMemoryMb;
	}

	public String getMapreduceReduceJavaOpts() {
		return mapreduceReduceJavaOpts;
	}

	public void setMapreduceReduceJavaOpts(String mapreduceReduceJavaOpts) {
		this.mapreduceReduceJavaOpts = mapreduceReduceJavaOpts;
	}

	public String getMapreduceReduceMergeInmemThreshold() {
		return mapreduceReduceMergeInmemThreshold;
	}

	public void setMapreduceReduceMergeInmemThreshold(
			String mapreduceReduceMergeInmemThreshold) {
		this.mapreduceReduceMergeInmemThreshold = mapreduceReduceMergeInmemThreshold;
	}

	public String getMapreduceReduceInputBufferPercent() {
		return mapreduceReduceInputBufferPercent;
	}

	public void setMapreduceReduceInputBufferPercent(
			String mapreduceReduceInputBufferPercent) {
		this.mapreduceReduceInputBufferPercent = mapreduceReduceInputBufferPercent;
	}

	public String getMapreduceReduceShuffleInputBufferPercent() {
		return mapreduceReduceShuffleInputBufferPercent;
	}

	public void setMapreduceReduceShuffleInputBufferPercent(
			String mapreduceReduceShuffleInputBufferPercent) {
		this.mapreduceReduceShuffleInputBufferPercent = mapreduceReduceShuffleInputBufferPercent;
	}

	public String getMapreduceReduceShuffleMergePercent() {
		return mapreduceReduceShuffleMergePercent;
	}

	public void setMapreduceReduceShuffleMergePercent(
			String mapreduceReduceShuffleMergePercent) {
		this.mapreduceReduceShuffleMergePercent = mapreduceReduceShuffleMergePercent;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getMapperOutputKeyClassName() {
		return MaperOutputKeyClassName;
	}

	public void setMapperOutputKeyClassName(String MaperOutputKeyClassName) {
		this.MaperOutputKeyClassName = MaperOutputKeyClassName;
	}

	public String getMapperOutputValueClassName() {
		return MaperOutputValueClassName;
	}

	public void setMapperOutputValueClassName(String MaperOutputValueClassName) {
		this.MaperOutputValueClassName = MaperOutputValueClassName;
	}

	public String getReducerOutputKeyClassName() {
		return ReducerOutputKeyClassName;
	}

	public void setReducerOutputKeyClassName(String ReducerOutputKeyClassName) {
		this.ReducerOutputKeyClassName = ReducerOutputKeyClassName;
	}

	public String getReducerOutputValueClassName() {
		return ReducerOutputValueClassName;
	}

	public void setReducerOutputValueClassName(String ReducerOutputValueClassName) {
		this.ReducerOutputValueClassName = ReducerOutputValueClassName;
	}

	public String getInputFormatClassName() {
		return inputFormatClassName;
	}

	public void setInputFormatClassName(String inputFormatClassName) {
		this.inputFormatClassName = inputFormatClassName;
	}

	public String getOutputFormatClassName() {
		return outputFormatClassName;
	}

	public void setOutputFormatClassName(String outputFormatClassName) {
		this.outputFormatClassName = outputFormatClassName;
	}

	public String getMapperClassName() {
		return mapperClassName;
	}

	public void setMapperClassName(String mapperClassName) {
		this.mapperClassName = mapperClassName;
	}

	public String getPartitionerClassName() {
		return partitionerClassName;
	}

	public void setPartitionerClassName(String partitionerClassName) {
		this.partitionerClassName = partitionerClassName;
	}

	public String getCombinerClassName() {
		return combinerClassName;
	}

	public void setCombinerClassName(String combinerClassName) {
		this.combinerClassName = combinerClassName;
	}

	public String getReducerClassName() {
		return reducerClassName;
	}

	public void setReducerClassName(String reducerClassName) {
		this.reducerClassName = reducerClassName;
	}

	public int getNumReducer() {
		return numReducer;
	}

	public void setNumReducer(int numReducer) {
		this.numReducer = numReducer;
	}

	public String getSplitterClassName() {
		return splitterClassName;
	}

	public void setSplitterClassName(String splitterClassName) {
		this.splitterClassName = splitterClassName;
	}

	public String getIndices() {
		return indices;
	}

	public void setIndices(String indices) {
		this.indices = indices;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}