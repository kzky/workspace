package edu.kzk.hadoop_sample.fs.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class ReadMain {
	//static String uri = "hdfs://localhost/user/kzk/output/part-00000";
	static String uri = "hdfs://localhost/user/kzk/test/ratings_rand_10.dat";
	/**
	 * @param args
	 * @throws Exception 
	 */

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		FileSystem fs;
		InputStream in = null;
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096, false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
