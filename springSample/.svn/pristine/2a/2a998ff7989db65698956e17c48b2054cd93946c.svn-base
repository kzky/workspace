package org.kzk.springSample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	Hello hello = (Hello)ac.getBean("helloBean");
    	hello.printHello();
    }
}
