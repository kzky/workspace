package edu.kzk.hadoop_sample.fs.file;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class MakeDirMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String pathname = "hdfs://localhost:8020/user/kzk/test/mkdir_dir";
			FileSystem fs = FileSystem.get(new URI(pathname), new Configuration());
			
			System.out.println("mdkir");
			fs.mkdirs(new Path(pathname));
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

	}

}
