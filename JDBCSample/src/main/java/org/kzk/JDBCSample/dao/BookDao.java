package org.kzk.JDBCSample.dao;

import java.util.List;
import java.util.Map;

import org.kzk.JDBCSample.pojo.Book;

public interface BookDao {

	public abstract void insert(String title, int price, String author);
	public abstract void updatePriceByTitle(int price, String title);
	public abstract List<Book> findByTitle(String title);
	public abstract List<Book> findAll();
	public abstract void deleteByTitle(String title);
	/**
	 * Map<columnName, value>をBookオブジェクトに変換します。
	 * @param map
	 * @return Book
	 */
	public abstract Book convertBook(Map<String, Object> map);
}
