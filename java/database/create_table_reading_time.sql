BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS reading_time;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_reading_time_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_reading_time_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE reading_time(
    reading_time_id INTEGER DEFAULT nextval('seq_reading_time_id') PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    minutes_read INTEGER
);

COMMIT TRANSACTION;