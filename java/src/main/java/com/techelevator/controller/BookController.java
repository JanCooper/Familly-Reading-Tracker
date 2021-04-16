package com.techelevator.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.BookDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Book;
import com.techelevator.model.BookList;

@RestController
@CrossOrigin
public class BookController
{
	@Autowired
	private BookDAO booksDAO;
	@Autowired
	private UserDAO userDAO;

	@PostMapping("/addBook")
	public ResponseEntity<Book> createBook(@RequestBody Book newBook, Principal currentUser)
	{	
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400 status code
		int currentUserId = userDAO.getUserIdByUsername(currentUser.getName());
		boolean isDuplicate = booksDAO.checkForDuplicateBook(newBook, currentUserId);
		Book createdBook = null;
			
		if(isDuplicate) {
			// user already has this book
			status = HttpStatus.NO_CONTENT; // 204 status code
		} else {
			createdBook = booksDAO.createBook(newBook, currentUserId);
			
			if(createdBook.toString() != null){
				status = HttpStatus.CREATED; // 201 status code
			} else {
				status = HttpStatus.EXPECTATION_FAILED; //417 status code	
			}
		}
		return new ResponseEntity<Book>(createdBook, status);
	}

	@GetMapping("/myBooks")
	public ResponseEntity<BookList> getBooksByUserId(Principal user)
	{	
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400 status code
		String userName = user.getName();
		int currentUserId = userDAO.getUserIdByUsername(userName);
		BookList bookList = new BookList(booksDAO.getBooksByUserId(currentUserId));
		List<Book> myBooks = bookList.getBooks();
		
		if(myBooks.size() > 0) {
			status = HttpStatus.OK; // 400 status code
		} 
		return new ResponseEntity<BookList>(bookList, status);
	}
	
	@PostMapping("/myBooks")
	public ResponseEntity<Book> updateBookDetails(@RequestBody Book bookToUpdate, Principal currentUser){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String userName = currentUser.getName();
		int currentUserId = userDAO.getUserIdByUsername(userName);		
		Book updatedBook = booksDAO.updateReaderDetails(bookToUpdate, currentUserId);
		boolean readUpdated = updatedBook.getRead() == bookToUpdate.getRead();
		boolean readingUpdated = updatedBook.getReading() == bookToUpdate.getReading();
		
		if(readUpdated && readingUpdated) {
			httpStatus = HttpStatus.ACCEPTED; //202 status code
		} else {
			httpStatus = HttpStatus.EXPECTATION_FAILED; //417 status code
		}	
		return new ResponseEntity<Book>(updatedBook, httpStatus);
	}
	
	@DeleteMapping("/myBooks") 
    public ResponseEntity<String> removeBook(@RequestBody Book bookToRemove, Principal currentUser) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		int bookId = bookToRemove.getBookId();
        String userName = currentUser.getName();
		int currentUserId = userDAO.getUserIdByUsername(userName);
        boolean isDeleted = booksDAO.removeBook(bookToRemove, currentUserId);
        
        if (isDeleted){
        	status = HttpStatus.OK;
        } else {
        	status = HttpStatus.EXPECTATION_FAILED;
        }
        
        return new ResponseEntity<String>(Integer.toString(bookId), status);
    }
}