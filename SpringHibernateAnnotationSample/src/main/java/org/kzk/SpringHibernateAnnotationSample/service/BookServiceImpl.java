package org.kzk.SpringHibernateAnnotationSample.service;

import java.util.List;

import org.kzk.SpringHibernateAnnotationSample.dao.BookDao;
import org.kzk.SpringHibernateAnnotationSample.pojo.Book;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	public void save(Book book) {
		bookDao.save(book);
	}

	public void update(Book book) {
		bookDao.update(book);
	}

	public void delete(Book book) {
		bookDao.delete(book);
	}

	public List<Book> findByAuthor(String author) {
		return bookDao.findByAuthor(author);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
