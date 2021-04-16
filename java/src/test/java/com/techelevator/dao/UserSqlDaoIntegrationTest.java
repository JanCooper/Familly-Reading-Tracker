package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private UserSqlDAO userSqlDAO;
	private String MESSAGE;
	private String FIRST_NAME = "test_firstName";
	private String LAST_NAME = "test_lastName";
	private String EMAIL = "test@email.com";
	private String USERNAME = "TEST_USER";
	private String PASSWORD = "test_password";
	private String ROLE = "user";
	

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userSqlDAO = new UserSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
		// arrange
		MESSAGE = "Should return true if user created in database";	
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByUsername(USERNAME);
		String actual = user.getUsername();
		String expected = USERNAME;
		
		// assert        
        assertTrue(userCreated);        
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void findByUsername() {
		// arrange
		MESSAGE = "Should return user with matching username";
		String expected = USERNAME;
		
		// act
		userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByUsername(USERNAME);
		String actual = user.getUsername();
		
		// assert        
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void findIdByUsername() {
		// arrange    	
		MESSAGE = "Should return user id of user with matching username";
		int nextId = userSqlDAO.getNextUserId();
		String expected = String.valueOf(nextId);
		
		// act
		userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		int userId = userSqlDAO.getUserIdByUsername(USERNAME);
		String actual = String.valueOf(userId);
		
		// assert        
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void findByFirstName() {
		// arrange
		MESSAGE = "Should return user with matching first name";
		String expected = FIRST_NAME;
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
		User user = userSqlDAO.getUserByFirstName(FIRST_NAME);
		String actual = user.getFirstName();
		
		// assert
		assertTrue(userCreated); 
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void findByEmail() {
		// arrange
		MESSAGE = "Should return user with matching email";
		String expected = EMAIL;
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
        User user = userSqlDAO.getUserByEmail(EMAIL);
        String actual = user.getEmail();
		
		// assert
        assertTrue(userCreated);
        assertEquals(expected, actual, MESSAGE);
    }
    
    @Test
    public void createNewUserWithoutLastName() {
		// arrange
		MESSAGE = "Should be able to create user with empty string or null last name";
		String expected = USERNAME;
		
		// act
		boolean userCreated = userSqlDAO.createUser(FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, ROLE);
        User user = userSqlDAO.getUserByUsername(USERNAME);
        String actual = user.getUsername();
		
		// assert        
        assertTrue(userCreated);
        assertEquals(expected, actual, MESSAGE);
    }  
}
