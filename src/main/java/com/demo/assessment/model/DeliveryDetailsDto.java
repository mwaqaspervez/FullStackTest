package com.demo.assessment.model;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.model.types.TicketPriorityType;

import java.time.ZonedDateTime;

public class DeliveryDetailsDto {
    private int id;
    private CustomerType customerType;
    private DeliveryStatus deliveryStatus;
    private ZonedDateTime expectedDeliveryTime;
    private int currentDistance;
    private int riderRating;
    private int timeToPrepare;
    private int timeToReach;
    private DeliveryPriority deliveryPriority;
    private Integer ticketId;
    private TicketPriorityType ticketPriority;

    public DeliveryDetailsDto(int id, CustomerType customerType, DeliveryStatus deliveryStatus,DeliveryPriority deliveryPriority,
                              ZonedDateTime expectedDeliveryTime,
                              int currentDistance, int riderRating, int timeToPrepare,
                              int timeToReach, Integer ticketId, TicketPriorityType ticketPriority) {
        this.id = id;
        this.customerType = customerType;
        this.deliveryStatus = deliveryStatus;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.currentDistance = currentDistance;
        this.riderRating = riderRating;
        this.timeToPrepare = timeToPrepare;
        this.timeToReach = timeToReach;
        this.deliveryPriority = deliveryPriority;
        this.ticketId = ticketId;
        this.ticketPriority = ticketPriority;
    }

    public DeliveryDetailsDto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public ZonedDateTime getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(ZonedDateTime expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    public int getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(int riderRating) {
        this.riderRating = riderRating;
    }

    public int getTimeToPrepare() {
        return timeToPrepare;
    }

    public void setTimeToPrepare(int timeToPrepare) {
        this.timeToPrepare = timeToPrepare;
    }

    public int getTimeToReach() {
        return timeToReach;
    }

    public void setTimeToReach(int timeToReach) {
        this.timeToReach = timeToReach;
    }

    public DeliveryPriority getDeliveryPriority() {
        return deliveryPriority;
    }

    public void setDeliveryPriority(DeliveryPriority deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public TicketPriorityType getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriorityType ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public DeliveryDetails getDeliveryDetails() {
        return new DeliveryDetails(this.id, this.customerType, this.deliveryStatus, this.deliveryPriority, this.expectedDeliveryTime,
                this.currentDistance, this.riderRating, this.timeToPrepare, this.timeToReach);
    }
}
