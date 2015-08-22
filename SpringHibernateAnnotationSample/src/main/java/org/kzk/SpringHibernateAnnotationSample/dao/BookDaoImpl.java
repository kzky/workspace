package org.kzk.SpringHibernateAnnotationSample.dao;

import java.util.List;

import org.kzk.SpringHibernateAnnotationSample.pojo.Book;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

	public void save(Book book) {
		getHibernateTemplate().save(book);
	}

	public void update(Book book) {
		getHibernateTemplate().update(book);
	}

	public void delete(Book book) {
		getHibernateTemplate().delete(book);
	}

	public List<Book> findByAuthor(String author) {
		return getHibernateTemplate().find("from Book where author=?", author);
	}
}
