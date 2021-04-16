BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS books_users;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_books_users_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_books_users_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE books_users (
  books_users_id INTEGER DEFAULT nextval('seq_books_users_id') PRIMARY KEY NOT NULL,
  book_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  reading BOOLEAN NOT NULL,
  read BOOLEAN NOT NULL
);

COMMIT TRANSACTION;