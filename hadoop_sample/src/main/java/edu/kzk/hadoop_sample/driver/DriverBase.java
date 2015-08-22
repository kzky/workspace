package edu.kzk.hadoop_sample.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

import edu.kzk.hadoop_sample.mapper.SplitMapper;
import edu.kzk.hadoop_sample.reducer.TextLongSumReducer;

public abstract class DriverBase implements Driver{ //extends Configured implements Tool {
	
	// logger
	Logger logger = Logger.getLogger(DriverBase.class);
	
	// set job related property
	private String jobName;
	private String inputPath;
	private String outputDir;

	// set SplitMapper property
	private String splitterClassName;
	private String indices;
	private String delimiter;

	// job execution time
	long startTime;
	long endTime;

	public DriverBase(){};
	
	public DriverBase(String jobName, String inputPath, String outputDir,
			String indices, String splitterClassName, String delimiter) {
		super();
		this.jobName = jobName;
		this.inputPath = inputPath;
		this.outputDir = outputDir;
		this.indices = indices;
		this.splitterClassName = splitterClassName;
		this.delimiter = delimiter;
	}
	
	public void setIOPath(String inputPath, String outputDir) {
		// TODO Auto-generated method stub
		
	}
	
	public Job initJob(String[] args) {

		// configuration
		Configuration conf = new Configuration();
		setConf(conf);

		// Job instance
		Job job = null;
		try {
			//job = new Job(conf, jobName);
			job = Job.getInstance(conf, jobName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set jar class
		job.setJarByClass(getClass());

		// set mapper and reduer
		job.setMapperClass(SplitMapper.class);
		job.setReducerClass(TextLongSumReducer.class);

		// set outputkey and outputvalue class
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		// set input and output format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// set input and output path
		try {
			FileInputFormat.setInputPaths(job, new Path(inputPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputFormat.setOutputPath(job, new Path(outputDir));

		return job;
	}

	private void setConf(Configuration conf) {
		conf.set("splitterClassName", splitterClassName);
		conf.set("indices", indices);
		conf.set("delimiter", delimiter);

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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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

	public String getIndices() {
		return indices;
	}

	public void setIndices(String indices) {
		this.indices = indices;
	}

	public String getSplitterClassName() {
		return splitterClassName;
	}

	public void setSplitterClassName(String splitterClassName) {
		this.splitterClassName = splitterClassName;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}
