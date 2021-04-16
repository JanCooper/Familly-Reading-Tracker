package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Book;

public interface BookDAO
{
	public Book createBook(Book bookToCreate, int currentUserId);
	
	public Book getBookByID(int bookId);	
	
	public Book getBookByIsbn(String isbn);
	
	public Book getBookByTitle(String title);
	
	public Book updateReaderDetails(Book book, int userId);
	
	public Boolean removeBook(Book bookToRemove, int userId);
	
	public List<Book> getBooksByUserId(int userId);
	
	public List<Book> listAll();
	
	public boolean checkForDuplicateBook(Book bookToCreate, int userId);
	
	public int getBookIdByTitle(String title);
	
	public int getBookUserId(int book_id, int user_id);
}
