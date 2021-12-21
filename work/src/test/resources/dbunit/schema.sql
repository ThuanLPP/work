CREATE TABLE IF NOT EXISTS WORKS
( id INT NOT NULL AUTO_INCREMENT,
  work_name VARCHAR(100) NOT NULL,
  start_date DATETIME,
  end_date DATETIME,
  status VARCHAR(100),
  CONSTRAINT contacts_pk PRIMARY KEY (id)
);