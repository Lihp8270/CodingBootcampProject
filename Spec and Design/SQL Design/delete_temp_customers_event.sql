-- show variables where variable_name='event_scheduler';
use theatre;
drop event if exists every_2_days_remove_temp_customers;
delimiter $$

create event every_2_days_remove_temp_customers
	on schedule every 2 day starts now()
    on completion preserve
do begin
	delete from customer
    where timestampdiff(day, created, now())>2 AND temp_status !=0 ;
end$$
delimiter ;