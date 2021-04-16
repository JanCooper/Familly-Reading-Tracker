package com.techelevator.model;

import java.util.List;

public class BookList {
	private List<Book> books;
	
	public BookList(List<Book> books) {
		this.books = books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public List<Book> getBooks(){
		return books;
	}

}
