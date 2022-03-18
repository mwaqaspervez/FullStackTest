DELETE FROM ticket_detail;
DELETE FROM delivery_details;
DELETE FROM ticket_priority;
DELETE FROM app_user;

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (1, 2, 2, 2,DATEADD('ss',2100, CURRENT_TIMESTAMP),50,5,0,0);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (2, 1, 1, 1,DATEADD('ss',3300, CURRENT_TIMESTAMP),10,4,1800,1500);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (3, 0, 0, 0,DATEADD('ss',3300, CURRENT_TIMESTAMP),40,3,0,0);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (4, 2, 2, 2,DATEADD('ss',5100, CURRENT_TIMESTAMP),0,2,3600,1500);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (5, 2, 2, 2,DATEADD('ss',3980, CURRENT_TIMESTAMP),500,1,2600,1380);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (6, 0, 0, 0,DATEADD('ss',3980, CURRENT_TIMESTAMP),100,4,1500,2390);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (7, 2, 2, 2,DATEADD('ss',6700, CURRENT_TIMESTAMP),65,2,1200,5000);

INSERT INTO delivery_details (id, customer_type,delivery_status,delivery_priority,expected_delivery_time,
current_distance_from_destination_in_meters,rider_rating,mean_time_to_prepare_food_in_seconds,
time_to_reach_destination_in_seconds) VALUES (8, 0, 0, 0,DATEADD('ss',4330, CURRENT_TIMESTAMP),32,5,1350,1980);

INSERT into ticket_priority (id, customer_type, ticket_priority) VALUES (1, 2, 0);
INSERT into ticket_priority (id, customer_type, ticket_priority) VALUES (2, 1, 1);
INSERT into ticket_priority (id, customer_type, ticket_priority) VALUES (3, 0, 2);

INSERT into app_user(id, username, password) VALUES (
1, 'admin', '$2a$10$0M/mFpLlG5N9NZrCvuaoAeFbE02vpl2cOnx9T7DDhqjjSSpP2Krke');