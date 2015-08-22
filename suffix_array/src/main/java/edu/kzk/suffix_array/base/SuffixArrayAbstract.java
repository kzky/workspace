package edu.kzk.suffix_array.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.kzk.suffix_array.SuffixArray;
import edu.kzk.suffix_array.error.SourceDocumentTooShortException;

public abstract class SuffixArrayAbstract implements SuffixArray {

	private int[] idx; 

	public void phonyBubleSort(char[] src) {
		try {
			init(src);
		} catch (SourceDocumentTooShortException e) {
			e.printStackTrace();
		}

		// phony buble sorting
		for (int i = 0; i < src.length - 1; i++) {
			for (int j = i+1; j < src.length; j++) {
				if (src[i] > src[j]) {
					// swap idx
					int idxI = idx[i];
					idx[i] = idx[j];
					idx[j] = idxI;

					// swap char
					char srcI = src[i];
					src[i] = src[j];
					src[j] = srcI;
				}
			}
		}
	}


	public void bubleSort(String src) {

		List<String> srcList = new ArrayList<String>(src.length());
		for (int i = 0; i < src.length(); i++) {
			srcList.add(src.substring(i));
		}
				
		try {
			init(src);
		} catch (SourceDocumentTooShortException e) {
			e.printStackTrace();
		}

		// buble sorting
		for (int i = 0; i < src.length() - 1; i++) {
			for (int j = i+1; j < src.length(); j++) {
				if (srcList.get(i).compareTo(srcList.get(j)) > 0) {
					// swap idx
					int idxI = idx[i];
					idx[i] = idx[j];
					idx[j] = idxI;

					// swap subString List
					String srcI = srcList.get(i);
					srcList.set(i, srcList.get(j));
					srcList.set(j, srcI);
				} else {
					
				}
			}
		}
		//return srcList;
	}


	public void binarySearch(char[] in) {
		// TODO Auto-generated method stub

	}


	public void init(char[] src) throws SourceDocumentTooShortException {

		// TODO
		// init called exceptionがあったほうがいいかも．

		if (src.length < 3) {
			throw new SourceDocumentTooShortException();
		}

		idx = new int[src.length];
		for (int i = 0; i < idx.length; i++) {
			idx[i] = i;
		}	
	}

	public void init(String src) throws SourceDocumentTooShortException {

		// TODO
		// init called exceptionがあったほうがいいかも．

		if (src.length() < 3) {
			throw new SourceDocumentTooShortException();
		}

		idx = new int[src.length()];
		for (int i = 0; i < idx.length; i++) {
			idx[i] = i;
		}	
	}

	public void quickSort(char[] src) {
		throw new UnsupportedOperationException();
	}

	
	public void redBlackTreeSort(String src) {
		try {
			init(src);
		} catch (SourceDocumentTooShortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// red-black tree sort
		Map<String, Integer> srcMap = new TreeMap<String, Integer>();
		for (int i = 0; i < src.length(); i++) {
			srcMap.put(src.substring(i), i);
		}
		
		int count = 0;
		for (Entry<String, Integer> map : srcMap.entrySet()) {
			idx[count] = map.getValue();
			count++;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < idx.length - 1; i++) {
			sb.append(idx[i]);
			sb.append(",");
		}
		sb.append(idx[idx.length - 1]);
		return sb.toString();
	}

	// for debug
	public void print() {
		System.out.println(toString());
	}


	// for debug
	// return src ordered by idx
	public void print(char[] src) {
		for (int i = 0; i < idx.length - 1; i++) {
			System.out.print(src[idx[i]] + ",");
		}
		System.out.println(src[idx[idx.length - 1]]);
	}
	

}
