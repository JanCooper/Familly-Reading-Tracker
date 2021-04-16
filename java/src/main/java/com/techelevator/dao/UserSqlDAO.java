package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNextUserId() {
    	return jdbcTemplate.queryForObject("SELECT last_value + 1 FROM seq_user_id", int.class);
    }
    
    @Override
    public int getUserIdByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT user_id FROM users WHERE username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT user_id "
						+ ", username "
						+ ", password_hash "
						+ ", role "
						+ ", first_name "
						+ ", last_name "
						+ ", email  "
					+ "FROM users "
					+ "WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
		String sql = "SELECT user_id "
						+ ", username "
						+ ", password_hash "
						+ ", role "
						+ ", first_name "
						+ ", last_name "
						+ ", email  "
					+ "FROM users ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.getAllUsers()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }    
    
    @Override
    public User getUserByEmail(String email) throws UsernameNotFoundException {
        for (User user : this.getAllUsers()) {
            if(user.getEmail() != null && user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + email + " was not found.");
    }
    @Override
    public User getUserByFirstName(String firstName) throws UsernameNotFoundException {
        for (User user : this.getAllUsers()) {
            if(user.getFirstName() != null && user.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + firstName + " was not found.");
    }

    @Override
    public boolean createUser(String firstName, String lastName, String email, String username, String password, String role) {
        String insertUser = "INSERT INTO users (username, password_hash, role, first_name, last_name, email) VALUES(?,?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();        

        GeneratedKeyHolder userKeyHolder = new GeneratedKeyHolder();
        String user_id_column = "user_id";
        boolean userCreated = false;
		
        userCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertUser, new String[]{user_id_column});
			ps.setString(1, username);
			ps.setString(2, password_hash);
			ps.setString(3, ssRole);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6,  email);
			return ps;
		}, userKeyHolder) == 1;
		
        int newUserId = (int) userKeyHolder.getKeys().get(user_id_column);
        return userCreated;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setActivated(true);
        return user;
    }
}
