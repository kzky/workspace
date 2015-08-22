package edu.kzk.spring_sample.extended_singleton;

import org.apache.log4j.Logger;

public class Child extends Parent {
	static Logger logger = Logger.getLogger(Child.class); 
		
	public Child() {
		super();
		logger.info("### Constructor ###");
	}

}
