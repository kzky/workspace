package edu.kzk.hadoop_sample.fs.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class WriteMain {

	/**
	 * @param args
	 * @throws Exception 
	 */

	public static void main(String[] args) {
		String inputFile = 
				"/home/kzk/tmp/ratings_rand_10.dat";

		String outputFile = 
				"hdfs://localhost/user/kzk/test/ratings_rand_10.dat";

		try {
			InputStream is = new BufferedInputStream(
					new FileInputStream(new File(inputFile)));

			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(URI.create(outputFile), conf);
			
			OutputStream out= fs.create(new Path(outputFile), 
					new Progressable() {
				public void progress() {
					System.out.println("--- now copying ---");
				}
			});
			
			IOUtils.copyBytes(is, out, 4096, false);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
