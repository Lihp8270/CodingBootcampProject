-- set show categories.
use theatre;
insert into theatre.production_category (category_name)
values
('Opera'),
('Concert'),
('Musical'),
('Theatre')
;

insert into theatre.production (title, production_description, duration, category_id, price)
values
('Oliver!','Oliver! - The Musical', 120, 1, 30);

insert into performer(performer_name)
	values
    ('Ron Moody'),
    ('Georgia Brown'),
    ('Danny Sewell'),
    ('Keith Hamshire'),
    ('Martin Horsey')
;

insert into production_performers (production_id, performer_id, production_role)
	values
    (1,1, 'Fagin'),
    (1,2, 'Nancy'),
    (1,3, 'Bill Sykes'),
    (1,4, 'Oliver Twist'),
    (1,5, 'The Artful Dodger')
    ;
    
insert into seat (id, location) 
	values
	(1,'stalls'),
	(2,'stalls')
;

insert into customer (customer_name, customer_email, customer_password) value ('bob','bob@gmail','bob123');
insert into concession (concession_name, discount)
	values
    ('Standard', 1),
    ('Child', 0.75)
;
insert into performance( performance_date, time_slot, production_id)
	values 
    (curdate()+1, 'matinee', 1),
    (curdate()+1, 'evening', 1)
;

insert into ticket (concession_id, performance_id, seat_id, customer_id, ticket_status)
	values 
    (1,1,1,1,'basket'),
    (1,1,2,1,'purchased')
;
