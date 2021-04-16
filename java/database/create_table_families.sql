BEGIN TRANSACTION;

--drops:
DROP TABLE IF EXISTS families;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_family_id;

--create sequences manually - so that you can set the value when inserting new data
CREATE SEQUENCE seq_family_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE families(
    family_id INTEGER DEFAULT nextval('seq_family_id') PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    family_name VARCHAR(50) NOT NULL
);

COMMIT TRANSACTION;