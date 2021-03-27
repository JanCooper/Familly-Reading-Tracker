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
	user_id int DEFAULT nextval('seq_user_id') NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


-- create foreign key constraints


COMMIT TRANSACTION;
