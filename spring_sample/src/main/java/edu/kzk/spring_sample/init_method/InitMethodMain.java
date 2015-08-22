package edu.kzk.spring_sample.init_method;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitMethodMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("--- before new ClassPathXmlApplicationContext");
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("init_method.xml");
		System.out.println("--- after new ClassPathXmlApplicationContext");
		
		System.out.println("--- before getBean");
		Parent parent = (Parent)ac.getBean("child");
		System.out.println("--- after getBean");

		System.out.println(parent.getVar());
	}

}
