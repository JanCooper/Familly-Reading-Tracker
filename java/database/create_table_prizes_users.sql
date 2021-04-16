BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS prizes_users;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_prizes_users_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_prizes_users_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE prizes_users(
    prizes_users_id INTEGER DEFAULT nextval('seq_prizes_users_id') PRIMARY KEY NOT NULL,
    prize_id INTEGER,
    user_id INTEGER,
	--Date format should be yyyy-mm-dd by default, so we will need to account for that in how it interacts with the date formatter on the front end.
    awarded_date DATE NOT NULL 
);

COMMIT TRANSACTION;