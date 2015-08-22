package edu.kzk.hadoop_sample.splitter;

public interface Splitter {
	/**
	 * initialize Splitter.
	 */
	public void init();
	
	/**
	 * spilt the line.
	 * @return
	 */
	public String[] split(String line);
	
	
	/**
	 * split the line with the specified delimiter. 
	 * @param line
	 * @param delimiter
	 * @return
	 */
	public String[] split(String line, String delimiter);
	
	/**
	 * split the line, and return the splitted line with the sepcified indices.
	 * @param line
	 * @param indices
	 * @return
	 */
	public String[] split(String line, int[] indices);
	
	/**
	 * split the line with the specified delimiter, 
	 * and return the splitted line with the specified indecies.  
	 * @param line
	 * @param delimiter
	 * @param indices
	 * @return
	 */
	public String[] split(String line, String delimiter, int[] indices);
}
