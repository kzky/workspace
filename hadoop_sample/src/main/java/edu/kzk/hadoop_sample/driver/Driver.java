package edu.kzk.hadoop_sample.driver;

import org.apache.hadoop.mapreduce.Job;

public interface Driver {
	public Job initJob(String args[]);
	public int run(String[] args);
	public void setIOPath(String inputPath, String outputDir);
}
