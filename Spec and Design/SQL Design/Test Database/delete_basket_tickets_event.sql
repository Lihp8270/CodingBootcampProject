-- show variables where variable_name='event_scheduler';

drop event if exists every_5_mins_remove_basket_tickets;
delimiter $$

create event every_5_mins_remove_basket_tickets
	on schedule every 5 minute starts now()
    on completion preserve
do begin
	delete from ticket
    where timestampdiff(minute, created, now())>5;
end$$
delimiter ;