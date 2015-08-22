package edu.kzk.hadoop_sample.fs.file;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import edu.kzk.hadoop_sample.fs.file.filter.RegexPathFilter;

public class ListEntries {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String pathname = "hdfs://localhost:8020/user/kzk/";
			FileSystem fs = FileSystem.get(new URI(pathname), new Configuration());
			
			// usual version
			System.out.println("Usual Version");
			FileStatus[] fileStatusArray = fs.listStatus(new Path(pathname));
			for (int i = 0; i < fileStatusArray.length; i++) {
				FileStatus fileStatus = fileStatusArray[i];
				fileStatus.isFile();
				fileStatus.isDirectory();
				System.out.println(fileStatus.getPath());
			}
			System.out.println();
			
			// fileutils version
			System.out.println("FileUtils Version");
			Path[] pathes = FileUtil.stat2Paths(fileStatusArray);
			for (int i = 0; i < pathes.length; i++) {
				System.out.println(pathes[i]);
			}
			System.out.println();
			
			// glob
			System.out.println("GlobStatus Version");
			pathname = "hdfs://localhost:8020/user/kzk/*/*";
			fileStatusArray = fs.globStatus(new Path(pathname));
						
			pathes = FileUtil.stat2Paths(fileStatusArray);
			for (int i = 0; i < pathes.length; i++) {
				System.out.println(pathes[i]);
			}
			System.out.println();
			
			// glob and filter (new RegexPathFilter("input") does not work...)
			System.out.println("GlobStatus Filter Version");
			pathname = "hdfs://localhost:8020/user/kzk/*/*";
			fileStatusArray = fs.globStatus(new Path(pathname), new RegexPathFilter(".*input.*"));
			
			pathes = FileUtil.stat2Paths(fileStatusArray);
			for (int i = 0; i < pathes.length; i++) {
				System.out.println(pathes[i]);
			}
			System.out.println();
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

	}

}
