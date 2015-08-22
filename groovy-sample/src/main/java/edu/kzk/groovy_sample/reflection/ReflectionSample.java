package edu.kzk.groovy_sample.reflection;

import java.util.List;

public class ReflectionSample {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// reflection のサンプル

		System.out.println("class generation");;
		Class<?> clazz = null;
		try {
			clazz = Class.forName("java.util.ArrayList");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClassLoader loader = ClassLoader.getSystemClassLoader(); // class loaderを使用して.
		Class<?> clazz2 = null;
		try {
			clazz2 = loader.loadClass("java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> list = null;
		try {

			list = (List<String>) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add("hoo");
		list.add("foo");
		list.add("hoge");
		System.out.println(list);
	}

}
