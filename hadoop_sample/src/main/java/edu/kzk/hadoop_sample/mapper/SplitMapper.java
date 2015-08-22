package edu.kzk.hadoop_sample.mapper;

import java.io.IOException;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import edu.kzk.hadoop_sample.splitter.Splitter;

public class SplitMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	// Set in the context of the Job. 
	String splitterClassName = null;
	String delimiter;
	int[] indices;
	
	Class<? extends Splitter> clazz = null;
	Splitter splitter = null;

	Logger logger = Logger.getLogger(SplitMapper.class);

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		try {
			String[] sl = splitter.split(value.toString(), delimiter, indices);
			context.write(new Text(sl[0]), new LongWritable(1L));
		} catch (Exception e) {
			logger.info("Runtime Exception.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {

		// Instantiate splitter class
		Configuration conf = context.getConfiguration();
		splitterClassName = conf.get("splitterClassName");
		try {
			clazz = (Class<? extends Splitter>) Class.forName(splitterClassName);
			splitter = (Splitter)clazz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		splitter.init();
		
		// set properties
		delimiter = conf.get("delimiter");
		indices = toIntArray(conf.get("indices"));
	}
	
	protected int[] toIntArray(String indices) {
		String[] idxs = indices.split(",", -1);
		int[] _idxs = new int[idxs.length];
		for (int i = 0; i < idxs.length; i++) {
			_idxs[i] = NumberUtils.toInt(idxs[i]);
		}
		return _idxs;
	}
}