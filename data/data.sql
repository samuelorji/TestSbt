DROP TABLE IF EXISTS bank_record;
CREATE TABLE bank_record (
  id serial,
  created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  bank_name varchar NOT NULL,
  bank_identifier varchar NOT NULL,
  PRIMARY KEY (id)
  );