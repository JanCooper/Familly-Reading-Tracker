package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByUsername(String username);

    int getUserIdByUsername(String username);
    
    User getUserByFirstName(String firstName);
    
    User getUserByEmail(String email);

    boolean createUser(String firstName, String lastName, String email, String username, String password, String role);
}
