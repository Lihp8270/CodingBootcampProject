-- set production categories.
use theatre;
insert into theatre.production_category (category_name)
values
('Opera'),
('Concert'),
('Musical'),
('Theatre')
;

insert into theatre.production (title, production_description, duration, category_id, price, production_language)
	values
	('Oliver!','Oliver! - The Musical', 120, 3, 30, 'English'),
	('The Mousetrap','Whodunit?', 150, 4, 30,'English'),
	('Matilda','Adapted from Roald Dahl\'s novel', 150, 3, 35,'English'),
    ('La bohème','Mimì and Rodolfo\'s passion burns with a brilliant flame. In a city of lights, can love eclipse Death itself?', 130, 1, 40, 'Italian')
;

insert into performer(performer_name)
	values
    ('Ron Moody'),
    ('Georgia Brown'),
    ('Danny Sewell'),
    ('Keith Hamshire'),
    ('Martin Horsey'),
    
    ('Karen Archer'),
    ('Simon Haynes'),
    ('Jamie Hutchins'),
    ('Chrisopher Knott'),
    ('Hannah Lee'),
    ('Simon Roberts'),
    ('Tom Rooke'),
    ('Kirsten Hazel Smith'),
    
    ('Cleo Demetriou'),
    ('Bertie Carvel'),
    ('Lauren Ward'),
    ('Paul Kaye'),
    ('Josie Walker'),
    
    ('Evan Gorga'),
    ('Cesira Ferrani'),
    ('Tieste Wilmant'),
    
    ('La Scala Orchestra')
    
;

insert into production_performers (production_id, performer_id, production_role)
	values
    (1,1, 'Fagin'),
    (1,2, 'Nancy'),
    (1,3, 'Bill Sykes'),
    (1,4, 'Oliver Twist'),
    (1,5, 'The Artful Dodger'),
    
    (2,6, 'Mrs Boyle'),
    (2,7, 'Christopher Wren'),
    (2,8, 'Detective Trotter'),
    (2,9, 'Major Metcalf'),
    (2,10, 'Miss Casewell'),
    (2,11, 'Mr Paravicini'),
    (2,12, 'Giles Ralston'),
    (2,13, 'Mollie Ralston'),
    
    (3,14, 'Matilda Wormwood'),
    (3,15, 'Miss Trunchbull'),
    (3,16, 'Jennifer Honey'),
    (3,17, 'Mr Wormwood'),
    (3,18, 'Mrs Wormwood'),
    
    (4,19, 'Rodolfo'),
    (4,20, 'Mimi'),
    (4,21, 'Marcello')
;

insert into music_performers (production_id, performer_id, music_role)
	values
    (4,22, 'Orchestra')
;



insert into seat (id, location) 
	values
	(1,'stalls'),
	(2,'stalls'),
    (3,'stalls'),
    (4,'stalls'),
    (5,'circle'),
    (6,'circle')
    
;

insert into customer (customer_name, customer_email, customer_password) value ('bob','bob@gmail','bob123');
insert into concession (concession_name, discount)
	values
    ('Standard', 1),
    ('Child', 0.75)
;
insert into performance( performance_date, time_slot, production_id)
	values 
    (date_add(curdate(), interval 1 day), 'matinee', 1),
    (date_add(curdate(), interval 1 day), 'evening', 1),
    
	(date_add(curdate(), interval 2 day), 'matinee', 2),
    (date_add(curdate(), interval 2 day), 'evening', 2),
    
	(date_add(curdate(), interval 3 day), 'matinee', 3),
    (date_add(curdate(), interval 3 day), 'evening', 3),
    
	(date_add(curdate(), interval 4 day), 'matinee', 4),
    (date_add(curdate(), interval 4 day), 'evening', 4)
    
;

insert into ticket (concession_id, performance_id, seat_id, customer_id, ticket_status)
	values 
    (1,1,1,1,'basket'),
    (1,1,2,1,'purchased')
;
