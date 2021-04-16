package com.techelevator.model;

public class Book {
	private int bookId;
	private String isbn;
	private String title;
	private String author;
	private String imgLink;
	private boolean read;
	private boolean reading;	
	
	public Book(int bookId, String isbn, String title, String author, String imgLink, boolean read, boolean reading) {
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.imgLink = imgLink;
		this.read = read;
		this.reading = reading;
	}
	public Book(int bookId, String isbn, String title, String author, String imgLink) {
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.imgLink = imgLink;
	}
	
	public Book(String isbn, String title, String author, String imgLink) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.imgLink = imgLink;
	}
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public Book () {}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getImgLink() {
		return imgLink;
	}
	
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public boolean getRead() {
		return read;
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean getReading() {
		return reading;
	}
	
	public void setReading(boolean reading) {
		this.reading = reading;
	}
	
	@Override
	public String toString() {
		return "Book [\r\n"
				+ "bookId=" + bookId
				+ ",\r\nisbn=" + isbn 
				+ ",\r\ntitle=" + title 
				+ ",\r\nauthor=" + author 
				+ ",\r\nimgLink=" + imgLink 
				+ ",\r\nread=" + read 
				+ ",\r\nreading=" + reading 
				+ "\r\n]";
	}
	
	public boolean isEmpty() {
		if(title == null
				&& author == null
				&& isbn == null 
				&& imgLink == null
				&& read == false
				&& reading == false
			) {
			return true;
		}
		return false;
	}
}
