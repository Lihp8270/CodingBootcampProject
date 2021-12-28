drop user if exists 'theatre_user'@'localhost';
create user 'theatre_user'@'localhost' identified by 'Pa$$word123';
grant all privileges on theatre.* to 'theatre_user'@'localhost';
flush privileges;