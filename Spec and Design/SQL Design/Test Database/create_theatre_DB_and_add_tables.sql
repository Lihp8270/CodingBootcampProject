-- Create DB
DROP database IF EXISTS theatre ;

CREATE database IF NOT EXISTS theatre;
USE theatre
;

-- Customer table
DROP TABLE IF EXISTS theatre.customer ;

CREATE TABLE IF NOT EXISTS theatre.customer (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `address_line_1` VARCHAR(45) NULL,
  `address_line_2` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
;


-- Seat table
DROP TABLE IF EXISTS theatre.seat ;

CREATE TABLE IF NOT EXISTS theatre.seat (
  `id` INT NOT NULL,
  `location` ENUM('stalls', 'circle') NOT NULL,
  PRIMARY KEY (`id`))
;


-- Performer table
DROP TABLE IF EXISTS theatre.performer ;

CREATE TABLE IF NOT EXISTS theatre.performer (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `name`))
;


-- show_category table
DROP TABLE IF EXISTS theatre.show_category ;

CREATE TABLE IF NOT EXISTS theatre.show_category (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
;


-- Concession table
DROP TABLE IF EXISTS theatre.concession ;

CREATE TABLE IF NOT EXISTS theatre.concession (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `discount` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
;


-- Show table
DROP TABLE IF EXISTS theatre.show ;

CREATE TABLE IF NOT EXISTS theatre.show (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(250) NULL,
  `duration` INT NOT NULL,
  `category_id` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `language` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `category_id_idx` (`category_id` ASC) ,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `theatre`.`show_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Performance table
DROP TABLE IF EXISTS theatre.performance ;

CREATE TABLE IF NOT EXISTS theatre.performance (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time_slot` ENUM('matinee', 'evening') NOT NULL,
  `show_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `show_id_idx` (`show_id` ASC) ,
  UNIQUE INDEX `unique_index` (`date` ASC, `time_slot` ASC, `show_id` ASC) ,
  CONSTRAINT `FK_show_id`
    FOREIGN KEY (`show_id`)
    REFERENCES `theatre`.`show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Ticket table
DROP TABLE IF EXISTS theatre.ticket ;

CREATE TABLE IF NOT EXISTS theatre.ticket (
  `id` INT NOT NULL AUTO_INCREMENT,
  `concession_id` INT NOT NULL,
  `performance_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `status` ENUM('basket', 'purchased') NOT NULL,
  `created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `concession_id_idx` (`concession_id` ASC) ,
  INDEX `FK_performance_id_idx` (`performance_id` ASC) ,
  INDEX `FK_seat_id_idx` (`seat_id` ASC) ,
  INDEX `FK_customer_id_idx` (`customer_id` ASC) ,
  UNIQUE INDEX `ticket_unique_idx` (`performance_id` ASC, `seat_id` ASC) ,
  CONSTRAINT `FK_concession_id`
    FOREIGN KEY (`concession_id`)
    REFERENCES `theatre`.`concession` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_performance_id`
    FOREIGN KEY (`performance_id`)
    REFERENCES `theatre`.`performance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_seat_id`
    FOREIGN KEY (`seat_id`)
    REFERENCES `theatre`.`seat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `theatre`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Show_performers table
DROP TABLE IF EXISTS theatre.show_performers ;

CREATE TABLE IF NOT EXISTS theatre.show_performers (
  `id` INT NOT NULL AUTO_INCREMENT,
  `show_id` INT NOT NULL,
  `performer_id` INT NOT NULL,
  `show_role` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `show_id_idx` (`show_id` ASC) ,
  INDEX `perfomer_id_idx` (`performer_id` ASC) ,
  CONSTRAINT `show_id`
    FOREIGN KEY (`show_id`)
    REFERENCES `theatre`.`show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `perfomer_id`
    FOREIGN KEY (`performer_id`)
    REFERENCES `theatre`.`performer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- Music_performers table
DROP TABLE IF EXISTS theatre.music_performers ;

CREATE TABLE IF NOT EXISTS theatre.music_performers (
  `id` INT NOT NULL,
  `show_id` INT NOT NULL,
  `performer_id` INT NOT NULL,
  `music_role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_music_performer_idx` (`performer_id` ASC) ,
  INDEX `FK_show_id_idx` (`show_id` ASC) ,
  CONSTRAINT `FK_music_performer`
    FOREIGN KEY (`performer_id`)
    REFERENCES `theatre`.`performer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_show`
    FOREIGN KEY (`show_id`)
    REFERENCES `theatre`.`show` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- application_admin table
DROP TABLE IF EXISTS theatre.application_admin;

CREATE TABLE IF NOT EXISTS theatre.application_admin (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
;