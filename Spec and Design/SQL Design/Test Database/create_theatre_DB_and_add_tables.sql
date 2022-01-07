-- Create DB
DROP database IF EXISTS theatre ;

CREATE database IF NOT EXISTS theatre;
USE theatre
;

-- Customer table
DROP TABLE IF EXISTS theatre.customer ;

CREATE TABLE IF NOT EXISTS theatre.customer (
  id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(45),
  customer_email VARCHAR(45),
  customer_password VARCHAR(45),
  address_line_1 VARCHAR(45),
  address_line_2 VARCHAR(45),
  postcode VARCHAR(45),
  temp_status boolean NOT NULL DEFAULT 1,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id))
;


-- Seat table
DROP TABLE IF EXISTS theatre.seat ;

CREATE TABLE IF NOT EXISTS theatre.seat (
  id INT NOT NULL,
  location ENUM('stalls', 'circle') NOT NULL,
  PRIMARY KEY (id))
;


-- Performer table
DROP TABLE IF EXISTS theatre.performer ;

CREATE TABLE IF NOT EXISTS theatre.performer (
  id INT NOT NULL AUTO_INCREMENT,
  performer_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id, performer_name))
;


-- production_category table
DROP TABLE IF EXISTS theatre.production_category ;

CREATE TABLE IF NOT EXISTS theatre.production_category (
  id INT NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (category_name ASC))
;


-- Concession table
DROP TABLE IF EXISTS theatre.concession ;

CREATE TABLE IF NOT EXISTS theatre.concession (
  id INT NOT NULL AUTO_INCREMENT,
  concession_name VARCHAR(45) NOT NULL,
  discount FLOAT NOT NULL,
  PRIMARY KEY (id))
;


-- production table
DROP TABLE IF EXISTS theatre.production ;

CREATE TABLE IF NOT EXISTS theatre.production (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  production_description VARCHAR(250) NULL,
  duration INT NOT NULL,
  category_id INT NOT NULL,
  price DECIMAL(5,2) NOT NULL,
  production_language VARCHAR(45) NULL,
  PRIMARY KEY (id),
  INDEX category_id_idx (category_id ASC) ,
  CONSTRAINT category_id
    FOREIGN KEY (category_id)
    REFERENCES theatre.production_category (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Performance table
DROP TABLE IF EXISTS theatre.performance ;

CREATE TABLE IF NOT EXISTS theatre.performance (
  id INT NOT NULL AUTO_INCREMENT,
  performance_date DATE NOT NULL,
  time_slot ENUM('matinee', 'evening') NOT NULL,
  production_id INT NULL,
  PRIMARY KEY (id),
  INDEX production_id_idx (production_id ASC) ,
  UNIQUE INDEX unique_index (performance_date ASC, time_slot ASC, production_id ASC) ,
  CONSTRAINT FK_production_id
    FOREIGN KEY (production_id)
    REFERENCES theatre.production (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Ticket table
DROP TABLE IF EXISTS theatre.ticket ;

CREATE TABLE IF NOT EXISTS theatre.ticket (
  id INT NOT NULL AUTO_INCREMENT,
  concession_id INT NOT NULL,
  performance_id INT NOT NULL,
  seat_id INT NOT NULL,
  customer_id INT NOT NULL,
  ticket_status ENUM('basket', 'purchased') NOT NULL,
  sale_price Decimal (5,2) NOT NULL DEFAULT 99,
  created TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX concession_id_idx (concession_id ASC) ,
  INDEX FK_performance_id_idx (performance_id ASC) ,
  INDEX FK_seat_id_idx (seat_id ASC) ,
  INDEX FK_customer_id_idx (customer_id ASC) ,
  UNIQUE INDEX ticket_unique_idx (performance_id ASC, seat_id ASC) ,
  CONSTRAINT FK_concession_id
    FOREIGN KEY (concession_id)
    REFERENCES theatre.concession (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_performance_id
    FOREIGN KEY (performance_id)
    REFERENCES theatre.performance (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_seat_id
    FOREIGN KEY (seat_id)
    REFERENCES theatre.seat (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_customer_id
    FOREIGN KEY (customer_id)
    REFERENCES theatre.customer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- production_performers table
DROP TABLE IF EXISTS theatre.production_performers ;

CREATE TABLE IF NOT EXISTS theatre.production_performers (
  id INT NOT NULL AUTO_INCREMENT,
  production_id INT NOT NULL,
  performer_id INT NOT NULL,
  production_role VARCHAR(100) NULL,
  PRIMARY KEY (id),
  INDEX production_id_idx (production_id ASC) ,
  INDEX perfomer_id_idx (performer_id ASC) ,
  CONSTRAINT production_id
    FOREIGN KEY (production_id)
    REFERENCES theatre.production (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT perfomer_id
    FOREIGN KEY (performer_id)
    REFERENCES theatre.performer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Music_performers table
DROP TABLE IF EXISTS theatre.music_performers ;

CREATE TABLE IF NOT EXISTS theatre.music_performers (
  id INT NOT NULL AUTO_INCREMENT,
  production_id INT NOT NULL,
  performer_id INT NOT NULL,
  music_role VARCHAR(45) NULL,
  PRIMARY KEY (id),
  INDEX FK_music_performer_idx (performer_id ASC) ,
  INDEX FK_production_id_idx (production_id ASC) ,
  CONSTRAINT FK_music_performer
    FOREIGN KEY (performer_id)
    REFERENCES theatre.performer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_production
    FOREIGN KEY (production_id)
    REFERENCES theatre.production (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- application_admin table
DROP TABLE IF EXISTS theatre.application_admin;

CREATE TABLE IF NOT EXISTS theatre.application_admin (
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL,
  user_password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;