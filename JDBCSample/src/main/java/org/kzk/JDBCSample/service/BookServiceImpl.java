package org.kzk.JDBCSample.service;

import java.util.List;

import org.kzk.JDBCSample.dao.BookDao;
import org.kzk.JDBCSample.pojo.Book;

public class BookServiceImpl implements BookService {
	
	private BookDao bookDao; 

	public void insert(String title, int price, String author) {
		bookDao.insert(title, price, author);
	}

	public void updatePriceByTitle(int price, String title) {
		bookDao.updatePriceByTitle(price, title);
	}

	public List<Book> findByTitle(String title) {
		return bookDao.findByTitle(title);
	}

	public List<Book> findAll() {
		return bookDao.findAll();
	}

	public void deleteByTitle(String title) {
		bookDao.deleteByTitle(title);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}


}
