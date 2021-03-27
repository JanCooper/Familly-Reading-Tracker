-- ********************************************
-- this script inserts seed data for all tables
-- ********************************************

BEGIN TRANSACTION;


-- users
	-- the default password for both users is password
INSERT INTO users (user_id,username,password_hash,role) 
VALUES (1, 'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER')
	 , (2, 'admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- reset the sequence numbering for the users table
ALTER SEQUENCE seq_user_id RESTART WITH 3;



COMMIT TRANSACTION;
