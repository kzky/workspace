package org.kzk.JDBCSample.service;

import java.util.List;

import org.kzk.JDBCSample.pojo.Book;

public interface BookService {
	public abstract void insert(String title, int price, String author);
	public abstract void updatePriceByTitle(int price, String title);
	public abstract List<Book> findByTitle(String title);
	public abstract List<Book> findAll();
	public abstract void deleteByTitle(String title);
}
