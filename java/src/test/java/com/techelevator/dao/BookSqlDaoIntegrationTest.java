package com.techelevator.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Book;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;

public class BookSqlDaoIntegrationTest extends DAOIntegrationTest {

    private BookSqlDAO bookSqlDAO;
    private JdbcTemplate jdbcTemplate;
    private int BOOK_ID;
    private String MESSAGE;
    private String ISBN;
    private String TITLE;
    private String AUTHOR;
    private String IMG;
    private int USER_ID = 2;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        bookSqlDAO = new BookSqlDAO(jdbcTemplate);
    }

    @Test
    public void createBookReturnsCreatedBook(){
        // arrange 
    	TITLE = "TITLE createNewBookReturnsTrue";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz";    	
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);

        // act
        Book actual = bookSqlDAO.createBook(bookToCreate, USER_ID);

        // assert
        assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
        assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
        assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
        assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);
    }
    
    @Test
    public void createExistingBookForNewUser_updatesBooksUsersTable() {
    	// arrange
    	MESSAGE = "createBook with existing book for new user updates books_users table";
    	
    	// act
    	
    	// assert
    }
    
    @Test 
    public void getBookIdByTitle() {
    	// arrange
    	MESSAGE = "getBookIdByTitle returns the book id for the given title";
    	TITLE = "TITLE getBookIdByTitle";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz"; 
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
        Book testBook = bookSqlDAO.getBookByTitle(TITLE);
        BOOK_ID = testBook.getBookId();
        
    	// act
        int actual = bookSqlDAO.getBookIdByTitle(TITLE);
    	
    	// assert
        assertEquals(BOOK_ID, actual, MESSAGE);
    }
    
    @Test
    public void createNewBookUpdatesBooksUsersTable(){
        // arrange         
    	MESSAGE = "Creating a new book updates the books_users table.";
    	TITLE = "TITLE createNewBookUpdatesBooksUsersTable";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz"; 
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        
        // act
        bookSqlDAO.createBook(bookToCreate, USER_ID);
        BOOK_ID = bookSqlDAO.getBookIdByTitle(TITLE);       
        int bookUserId = bookSqlDAO.getBookUserId(BOOK_ID, USER_ID);
        
        // assert
        assertNotEquals(-1, bookUserId, MESSAGE);
    }
    
    @Test
    public void getBookById(){
        // arrange         
    	TITLE = "TITLE getBooksById";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN" ;
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
        BOOK_ID = bookSqlDAO.getBookIdByTitle(TITLE); 

        // act
       Book actual = bookSqlDAO.getBookByID(BOOK_ID);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);
       assertEquals(BOOK_ID, actual.getBookId(), "The book id should be " + BOOK_ID);
    }
    
    @Test
    public void getBooksByUserId(){
        // arrange    
    	MESSAGE = "Returns the size of the user's book list";
        for (int i = 0; i < 5; i++) {        	
        	TITLE = "TITLE" + i;
        	AUTHOR = "AUTHOR";
        	ISBN = "ISBN" + i;
        	IMG = "abcefghijklmnopqrstuvwxyz";        	
        	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
            bookSqlDAO.createBook(bookToCreate, USER_ID);
    	}
    	
    	int expectedCount = 5;

        // act
    	List<Book> actual = bookSqlDAO.getBooksByUserId(USER_ID);

        // assert
        assertEquals(expectedCount, actual.size(), MESSAGE);
    }
    
    @Test
    public void getBookByIsbn() {
        // arrange 
    	TITLE = "TITLE getBookByIsbn";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN1234567890";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);

        // act
       Book actual = bookSqlDAO.getBookByIsbn(ISBN);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);    
    }
    
    @Test
    public void getBookByTitle() {
        // arrange 
    	TITLE = "TITLE getBookByTitle";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);

        // act
       Book actual = bookSqlDAO.getBookByTitle(TITLE);

        // assert
       assertEquals(TITLE, actual.getTitle(), "The title should be " + TITLE);
       assertEquals(AUTHOR, actual.getAuthor(), "The author should be " + AUTHOR);
       assertEquals(ISBN, actual.getIsbn(), "The isbn should be " + ISBN);
       assertEquals(IMG, actual.getImgLink(), "The cover image link should be " + IMG);     
    }
    
    @Test
    public void getBookUserId() {
        // arrange 
    	MESSAGE = "Get Book User Id should return the book user id matching the book id and the user id.";
    	TITLE = "TITLE getBookUserId";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
    	
        BOOK_ID = bookSqlDAO.getBookIdByTitle(TITLE); 
    	int expected = 0;
    	
    	String sql = "SELECT books_users_id FROM books_users WHERE book_id = ? AND user_id = ?";
    	SqlRowSet result = jdbcTemplate.queryForRowSet(sql, BOOK_ID, USER_ID);
    	if(result.next()) {
    		expected = result.getInt("books_users_id");
    	}

        // act
       int actual = bookSqlDAO.getBookUserId(BOOK_ID, USER_ID);

        // assert
       assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void updateReaderDetails_ReadStatus() {
        // arrange 
     	MESSAGE = "Update Reader Details should update Read status to true.";
     	TITLE = "TITLE updateReaderDetails ReadStatus";
     	AUTHOR = "AUTHOR";
     	ISBN = "ISBN1234";
     	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
    	   	
     	Book expected = bookSqlDAO.getBookByTitle(TITLE);     	
     	expected.setRead(true);
     	
     	// act
     	Book actual = bookSqlDAO.updateReaderDetails(expected, USER_ID);
     	
     	// assert 
     	assertEquals(expected.getRead(), actual.getRead(), MESSAGE);
    }
    
    @Test
    public void updateReaderDetails_ReadingStatus() {
        // arrange 
     	MESSAGE = "Update Reader Details should update Reading status to true.";
     	TITLE = "TITLE updateReaderDetails ReadingStatus";
     	AUTHOR = "AUTHOR";
     	ISBN = "ISBN5678";
     	IMG = "abcefghijklmnopqrstuvwxyz";     	
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
    	    	
     	Book expected = bookSqlDAO.getBookByTitle(TITLE);     	
     	expected.setReading(true);
     	
     	// act
     	Book actual = bookSqlDAO.updateReaderDetails(expected, USER_ID);
     	
     	// assert 
     	assertEquals(expected.getReading(), actual.getReading(), MESSAGE);
    }
    
    @Test
    public void insertNewBookUserReturnsBookUserId() {
    	// arrange
    	MESSAGE = "Inserting a new book user returns the new book user id";
     	TITLE = "TITLE insertNewBookUserReturnsBookUserId";
     	AUTHOR = "AUTHOR";
     	ISBN = "ISBN";
     	IMG = "abcefghijklmnopqrstuvwxyz";     	
     	
     	String sql = "INSERT INTO books (title, author, isbn, cover_img_link)"
     			+ " VALUES(?, ?, ?, ?);";
     	
     	jdbcTemplate.update(sql, TITLE, AUTHOR, ISBN, IMG);    	    	
        BOOK_ID = bookSqlDAO.getBookIdByTitle(TITLE); 
     	
     	// act
     	int actual = bookSqlDAO.insertNewBookUser(BOOK_ID, USER_ID);
        int expected = bookSqlDAO.getBookUserId(BOOK_ID, USER_ID);     	
     	
     	// assert
     	assertEquals(expected, actual, MESSAGE);     	
    }
    
    @Test
    public void checkForDuplicateBookReturnsTrueIfUserAlreadyHasBook() {
        // arrange 
     	MESSAGE = "CreateDuplicate returns true if user already has the requested book.";
     	TITLE = "TITLE checkDuplicateReturnsTrueIfUserAlreadyHasBook";
     	AUTHOR = "AUTHOR";
     	ISBN = "ISBN";
     	IMG = "abcefghijklmnopqrstuvwxyz";     	
    	Book bookToCreate = new Book(ISBN, TITLE, AUTHOR, IMG);
        bookSqlDAO.createBook(bookToCreate, USER_ID);
        boolean expected = true;
        
        // act
        boolean actual = bookSqlDAO.checkForDuplicateBook(bookToCreate, USER_ID);
        
        // assert
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void removeBook() {
        // arrange 
    	MESSAGE = "Remove book should return true";
    	TITLE = "TITLE_removeBook";
    	AUTHOR = "AUTHOR";
    	ISBN = "ISBN";
    	IMG = "abcefghijklmnopqrstuvwxyz";
    	Book book = new Book(ISBN, TITLE, AUTHOR, IMG);
    	bookSqlDAO.createBook(book, USER_ID);
    	Book testBook = bookSqlDAO.getBookByTitle(TITLE);
    	
    	//act
    	boolean actual = bookSqlDAO.removeBook(testBook, USER_ID);
    	
    	//assert
    	assertEquals(true, actual, MESSAGE);
 
    }
}