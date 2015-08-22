package org.kzk.JDBCSample.dao;

import java.util.List;
import java.util.Map;

import org.kzk.JDBCSample.pojo.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BookDaoImpl extends JdbcDaoSupport implements BookDao {

	private String insertSql= "insert into book_info values(0, ?, ?, ?)";
	private String deleteByTitleSql= "delete from book_info where title = ?";
	private String findByTitleSql= "select * from book_info where title = ?";
	private String findAllSql = "select * from book_info";
	private String updatePriceByTitleSql = "update book_info set price = ? where title = ?"; 
	

	public void insert(String title, int price, String author) {
		// TODO Auto-generated method stub
		getJdbcTemplate().update(insertSql, new Object[]{title, price, author});		
	}

	public void deleteByTitle(String title) {
		getJdbcTemplate().update(deleteByTitleSql, new Object[]{title});
	}

	public List<Book> findByTitle(String title) {
		return getJdbcTemplate().query(findByTitleSql, new Object[]{title}, new BeanPropertyRowMapper(Book.class));
	} 

	public List<Book> findAll() {
		return getJdbcTemplate().query(findAllSql, new BeanPropertyRowMapper(Book.class));
	}

	public void updatePriceByTitle(int price, String title) {
		getJdbcTemplate().update(updatePriceByTitleSql, new Object[]{price, title});
	}


	public Book convertBook(Map<String, Object> map) {

		Book book = new Book();
		book.setId((Integer)map.get("id"));
		book.setTitle((String)map.get("title"));
		book.setPrice((Integer)map.get("price"));
		book.setAuthor((String)map.get("author"));
		return book;
	}
	
}
