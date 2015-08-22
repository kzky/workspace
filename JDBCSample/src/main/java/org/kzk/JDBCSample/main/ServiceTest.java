package org.kzk.JDBCSample.main;

import java.util.List;

import org.kzk.JDBCSample.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService = (BookService)ac.getBean("bookService");
		
		bookService.findByTitle("hoge");
		//bookService.deleteByTitle("hoge");
		bookService.updatePriceByTitle(5000, "title");
		
		print(bookService.findAll());
		
	}

	public static void print(List<?> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
