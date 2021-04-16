BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS books;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_book_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_book_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE books(
	book_id INTEGER DEFAULT nextval('seq_book_id') PRIMARY KEY NOT NULL,
	isbn VARCHAR(32),
	title VARCHAR(256),
	author VARCHAR(128),
	cover_img_link VARCHAR(512)        
);

COMMIT TRANSACTION;
