BEGIN TRANSACTION;

DROP TABLE IF EXISTS prizes;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_prize_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_prize_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE prizes(
    prize_id INTEGER DEFAULT nextval('seq_prize_id') PRIMARY KEY NOT NULL,
    family_id INTEGER NOT NULL,
    prize_name VARCHAR(50) NOT NULL, 
    minutes_needed INTEGER NOT NULL,
    max_award_count INTEGER ,
    awarded_count INTEGER 
);

COMMIT TRANSACTION;