BEGIN TRANSACTION;

-- drop tables
DROP TABLE IF EXISTS users;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_user_id;

--create sequences manually - so that you can set the value after inserting seed data
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

-- create tables
CREATE TABLE users (
	user_id INTEGER DEFAULT nextval('seq_user_id') PRIMARY KEY NOT NULL,
	username VARCHAR(64) NOT NULL,
	password_hash VARCHAR(200) NOT NULL,
	role VARCHAR(64) NOT NULL,
	first_name VARCHAR(64),
	last_name VARCHAR(64),
	email VARCHAR(64)
);

-- create foreign key constraints 
COMMIT TRANSACTION;
