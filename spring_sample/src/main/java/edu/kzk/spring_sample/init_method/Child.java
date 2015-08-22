package edu.kzk.spring_sample.init_method;

import org.apache.log4j.Logger;

public class Child extends Parent {
	static Logger logger = Logger.getLogger(Child.class); 
		
	public Child() {
		super();
		logger.info("### Constructor ###");
	}
	
	public void init_child() {
		logger.info("## init_child ##");
	}
}
