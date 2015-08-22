package edu.kzk.spring_sample.extended_singleton;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExtendedSingletonMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- before new ClassPathXmlApplicationContext");
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("extended_singleton.xml");
		System.out.println("--- after new ClassPathXmlApplicationContext");
		
		System.out.println("--- before getBean");
		Parent parent = (Parent)ac.getBean("child");
		System.out.println("--- after getBean");

		
		System.out.println(parent.getVar());
	}

}
