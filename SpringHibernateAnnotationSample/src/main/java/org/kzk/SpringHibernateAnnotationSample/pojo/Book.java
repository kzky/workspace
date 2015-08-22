package org.kzk.SpringHibernateAnnotationSample.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Book")
@Table(name="book_info")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="price")
	private int price;
	@Column(name="author")
	private String author;
	
	public Book() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book = [ id=" + id + ", title=" + title + ", price=" + price + 
				", author=" + author + " ]\n"; 
	}
}
