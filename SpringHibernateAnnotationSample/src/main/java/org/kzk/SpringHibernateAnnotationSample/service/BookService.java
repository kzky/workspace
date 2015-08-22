package org.kzk.SpringHibernateAnnotationSample.service;

import java.util.List;

import org.kzk.SpringHibernateAnnotationSample.pojo.Book;

public interface BookService {
	
	public abstract void save(Book book);
	public abstract void update(Book book);
	public abstract void delete(Book book);
	public abstract List<Book> findByAuthor(String author);
}
