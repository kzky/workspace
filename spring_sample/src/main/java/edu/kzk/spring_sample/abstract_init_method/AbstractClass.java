package edu.kzk.spring_sample.abstract_init_method;

import org.apache.log4j.Logger;

public class AbstractClass {
	static Logger logger = Logger.getLogger(AbstractClass.class);
	
	private String var;
	
	public void init() {
		logger.info("## init ##");
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		logger.info("setVar");
		this.var = var;
	}

}
