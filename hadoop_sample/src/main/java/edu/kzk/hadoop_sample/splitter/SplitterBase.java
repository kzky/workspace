package edu.kzk.hadoop_sample.splitter;


public abstract class SplitterBase implements Splitter{

	public void init() {
			
	}

	public String[] split(String line) {
		return split(line, ",");
	}

	public String[] split(String line, String delimiter) {
		return line.split(delimiter, -1);
	}

	public String[] split(String line, int[] indices) {
		String[] sl = line.split(",", -1);
		String[] ssl = new String[indices.length];
		for (int i = 0; i < ssl.length; i++) {
			ssl[i] = sl[indices[i]];
		}
		return ssl;
	}
	
	public String[] split(String line, String delimiter, int[] indices) {
		String[] sl = line.split(delimiter, -1);
		String[] ssl = new String[indices.length];
		for (int i = 0; i < ssl.length; i++) {
			ssl[i] = sl[indices[i]];
		}
		return ssl;
	}	
}
