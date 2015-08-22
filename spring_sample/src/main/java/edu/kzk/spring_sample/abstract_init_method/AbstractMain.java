package edu.kzk.spring_sample.abstract_init_method;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("abstract_init_method.xml");
		ac.getBean("child");
	}

}
