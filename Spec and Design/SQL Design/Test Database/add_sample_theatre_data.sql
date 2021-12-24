-- set show categories.
use theatre;
insert into theatre.show_category (name)
values
('Opera'),
('Concert'),
('Musical'),
('Theatre')
;

insert into theatre.show (title, `description`, duration, category_id, price)
values
('Oliver!','Oliver! - The Musical', 120, 1, 30);

insert into performer(name)
	values
    ('Ron Moody'),
    ('Georgia Brown'),
    ('Danny Sewell'),
    ('Keith Hamshire'),
    ('Martin Horsey')
;

insert into show_performers (show_id, performer_id, show_role)
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

insert into customer (name, email, password) value ('bob','bob@gmail','bob123');
insert into concession (name, discount)
	values
    ('Standard', 1),
    ('Child', 0.75)
;
insert into performance( date, time_slot,show_id)
	values 
    (curdate()+1, 'matinee', 1),
    (curdate()+1, 'evening', 1)
;

insert into ticket (concession_id, performance_id, seat_id, customer_id, status)
	values 
    (1,1,1,1,'basket'),
    (1,1,2,1,'purchased')
;
