package org.kzk.springSample;

import org.kzk.springSample.pojo.IHelloWorld;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("aop/beanAopSample.xml");
		
		IHelloWorld helloWorld = (IHelloWorld)ac.getBean("helloWorld");
		helloWorld.helloWorld();
		
	}
}
