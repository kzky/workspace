package edu.kzk.hadoop_sample.main.movielens;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.kzk.hadoop_sample.driver.Driver;
import edu.kzk.hadoop_sample.driver.impl.CustomizableDriver;

public class CustomizedMain {

	private static Logger logger = Logger.getLogger(CustomizedMain.class); 
	/**.
	 * @param args
	 */
	public static void main(String[] args) {

		
		if (args.length != 2) {
			logger.error("usage error");
			logger.error("usage: hadoop jar mainClass intputpath outputdir");
			System.exit(1);
		}
		
		// input and output path
		String inputPath = args[0];
		String outputDir= args[1];
		
		ApplicationContext ac= new ClassPathXmlApplicationContext("beans/driverBeans.xml");
		Driver driver = (CustomizableDriver)ac.getBean("basicDriver");
		driver.setIOPath(inputPath, outputDir);
		int status = driver.run(args);
		
		System.exit(status);
	}
}
