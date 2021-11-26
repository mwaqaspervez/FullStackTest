

CREATE TABLE if NOT exists APP_USER(
    ID BIGINT AUTO_INCREMENT,
    PASSWORD VARCHAR(255),
    USERNAME VARCHAR(255)
) ;

CREATE TABLE if NOT exists DELIVERY_DETAILS(
    ID INTEGER NOT NULL,
    CURRENT_DISTANCE_FROM_DESTINATION_IN_METERS INTEGER,
    CUSTOMER_TYPE INTEGER,
    DELIVERY_STATUS INTEGER,
    EXPECTED_DELIVERY_TIME TIMESTAMP,
    RIDER_RATING INTEGER,
    MEAN_TIME_TO_PREPARE_FOOD_IN_SECONDS INTEGER,
    TIME_TO_REACH_DESTINATION_IN_SECONDS INTEGER
);

CREATE TABLE if NOT exists TICKET_DETAIL(
    ID INTEGER NOT NULL,
    DELIVERY_PRIORITY INTEGER,
    DELIVERY_DETAILS_ID INTEGER
);