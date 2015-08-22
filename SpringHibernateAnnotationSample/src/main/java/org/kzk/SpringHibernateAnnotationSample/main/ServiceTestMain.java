package org.kzk.SpringHibernateAnnotationSample.main;

import java.util.List;

import org.kzk.SpringHibernateAnnotationSample.pojo.Book;
import org.kzk.SpringHibernateAnnotationSample.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("beans/applicationContext.xml");
		BookService bookService = (BookService)ac.getBean("bookService");
		
		Book book1 = new Book();
		book1.setId(0);
		book1.setTitle("hoge2");
		book1.setPrice(100);
		book1.setAuthor("akutagawa");
		
		// insert
		bookService.save(book1);
		
		// delete 
		book1.setId(103);
		bookService.delete(book1);
		
		// update
		book1.setAuthor("morioogai");
		book1.setId(110);
		bookService.update(book1);
		
		// select
		List<Book> books = bookService.findByAuthor("akutagawa");
		System.out.println(books);
		
	}

}
