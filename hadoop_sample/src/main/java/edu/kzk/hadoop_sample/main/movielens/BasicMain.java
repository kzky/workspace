package edu.kzk.hadoop_sample.main.movielens;

import edu.kzk.hadoop_sample.driver.Driver;
import edu.kzk.hadoop_sample.driver.impl.DriverImpl;

public class BasicMain {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String jobName = "movie_lens_rating_count";
		String inputPath = "/home/kzk/hadoop/movie_lens/input/ratings_rand.dat";
		String outputDir = "/home/kzk/hadoop/movie_lens/output/u_num_rating";
		String indices = "0";
		String splitterClassName = "edu.kzk.hadoop_sample.splitter.impl.SplitterImpl";
		String delimiter = "::";
		
		Driver driver = new DriverImpl(jobName, inputPath, outputDir, 
				indices, splitterClassName, delimiter);
		int status = driver.run(args);
		System.exit(status);
	}

}
