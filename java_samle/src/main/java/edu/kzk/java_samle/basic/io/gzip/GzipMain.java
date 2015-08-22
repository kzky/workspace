package edu.kzk.java_samle.basic.io.gzip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "/home/kzk/tmp/java/gzip.gz";
		try {
			deleteGipFile(filename);
			writeGzipFile(filename);
			readGzipFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readGzipFile(String filename) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new GZIPInputStream(
								new BufferedInputStream(
										new FileInputStream(
												new File(filename))))))
				)
				{

			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
				}
	}

	public static void writeGzipFile(String filename) throws FileNotFoundException, IOException {
		try(BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(
						new GZIPOutputStream(
								new BufferedOutputStream(
										new FileOutputStream(
												new File(filename))))))
				){
			for (int i = 0; i < 1000; i++) {
				bw.write(String.valueOf(i) + ": " + String.valueOf(Math.random()));
				bw.newLine();
			}
		}
	}

	public static boolean deleteGipFile(String filename) {
		return new File(filename).delete();
	}
}
