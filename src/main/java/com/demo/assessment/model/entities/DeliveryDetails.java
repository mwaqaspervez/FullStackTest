package com.demo.assessment.model;

import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "delivery_details")
public class DeliveryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int id;

    @Column(name = "customer_type")
    private CustomerType customerType;

    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "expected_delivery_time")
    private ZonedDateTime expectedDeliveryTime;

    @Column(name = "current_distance_from_destination_in_meters")
    private int currentDistance;

    @Column(name = "rider_rating")
    private int riderRating;

    @Column(name = "mean_time_to_prepare_food_in_seconds")
    private int timeToPrepare;

    @Column(name = "time_to_reach_destination_in_seconds")
    private int timeToReach;


    public int getId() {
        return id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public ZonedDateTime getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public int getTimeToReach() {
        return timeToReach;
    }

    public int getRiderRating() {
        return riderRating;
    }

    public int getTimeToPrepare() {
        return timeToPrepare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRiderRating(int riderRating) {
        this.riderRating = riderRating;
    }

    public void setTimeToPrepare(int timeToPrepare) {
        this.timeToPrepare = timeToPrepare;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setExpectedDeliveryTime(ZonedDateTime expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    public void setTimeToReach(int timeToReach) {
        this.timeToReach = timeToReach;
    }
}
