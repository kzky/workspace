package edu.kzk.groovy_sample.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<? extends Number> list = new ArrayList<Integer>(10);
		List<? extends Number> list = new ArrayList<Integer>(10);
		
		//list.add(new Integer(123));	//IntegerはNumberのサブクラスなのにコンパイルエラー
		//list.add(new Long(123));	//LongもNumberのサブクラスだけどコンパイルエラー
		
		System.out.println(list);
	}

}
