package edu.kzk.java_samle.basic.effective;

import org.apache.log4j.Logger;

public class FinalizeMain {
	static Logger logger = Logger.getLogger(FinalizeMain.class);
	public static void main(String[] args) {
		
	}
	
	// applicationの終了時には呼ばれない
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		logger.info("finalize");
	}
}
