package com.techelevator.model;

public class BookUser {
private int booksUsersId;
private int bookId;
private int UserId;
private boolean currentBook;
private boolean completed;


public BookUser() {}
public BookUser(int booksUsersId, int bookId, int userId, boolean currentBook, boolean completed) {
	super();
	this.booksUsersId = booksUsersId;
	this.bookId = bookId;
	UserId = userId;
	this.currentBook = currentBook;
	this.completed = completed;
}
public int getBooksUsersId() {
	return booksUsersId;
}
public void setBooksUsersId(int booksUsersId) {
	this.booksUsersId = booksUsersId;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public int getUserId() {
	return UserId;
}
public void setUserId(int userId) {
	UserId = userId;
}
public boolean isCurrentBook() {
	return currentBook;
}
public void setCurrentBook(boolean currentBook) {
	this.currentBook = currentBook;
}
public boolean isCompleted() {
	return completed;
}
public void setCompleted(boolean completed) {
	this.completed = completed;
}



}
