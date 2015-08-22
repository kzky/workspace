package edu.kzk.spring_sample.init_method;

import org.apache.log4j.Logger;



public class Parent {
	static Logger logger = Logger.getLogger(Parent.class); 
	private String var;
	
	public Parent() {
		super();
		logger.info("### Constructor ###");
	}

	public void init_parent() {
		logger.info("## init_parent ##");
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
