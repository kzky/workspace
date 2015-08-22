package edu.kzk.suffix_array;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kzk.suffix_array.base.SuffixArrayAbstract;
import edu.kzk.suffix_array.impl.SuffixArray;

public class TrivialSuffixArrayTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		SuffixArrayAbstract sa = new SuffixArray();
		String str = "abracadabra";
		//sa.phonyBubleSort(str.toCharArray());
		//sa.bubleSort(str);
		sa.redBlackTreeSort(str);
		sa.print();
		sa.print(str.toCharArray());
	}

	
}
