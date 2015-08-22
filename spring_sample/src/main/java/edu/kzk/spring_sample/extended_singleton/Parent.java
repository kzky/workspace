package edu.kzk.spring_sample.extended_singleton;

import org.apache.log4j.Logger;



public class Parent {
	static Logger logger = Logger.getLogger(Parent.class); 
	private String var;

	
	public Parent() {
		super();
		logger.info("### Constructor ###");
	}

	public String getVar() {
		logger.info("## getVar ##");
		return var;
	}

	public void setVar(String var) {
		logger.info("## setVar ##");
		this.var = var;
	}
}
