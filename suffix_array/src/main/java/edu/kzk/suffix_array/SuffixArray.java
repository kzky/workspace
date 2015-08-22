package edu.kzk.suffix_array;

import edu.kzk.suffix_array.error.SourceDocumentTooShortException;

/**
 * @author kzk
 * sorting algorithm to construct suffix array. 
 */
/**
 * @author kzk
 *
 */
/**
 * @author kzk
 *
 */
public interface SuffixArray {

	/**
	 * initialize index array
	 * @param src
	 * @throws SourceDocumentTooShortException
	 */
	public void init(String src) throws SourceDocumentTooShortException;
	
	/**
	 * initialize index array
	 * @param src
	 * @throws SourceDocumentTooShortException
	 */
	public void init(char[] src) throws SourceDocumentTooShortException;
	
	/**
	 * bublde sort
	 * @param src
	 */
	public void bubleSort(String src);
	
	/**
	 * phony bublde sort
	 * @param src
	 */
	public void phonyBubleSort(char[] src);
	
	/**
	 * quick sort
	 * @param src
	 */
	public void quickSort(char[] src);

	//ここにsuffix arrayのソートアルゴリズムを付け加えていく．
	
	public void binarySearch(char[] in);
	
	public void redBlackTreeSort(String src);
}
