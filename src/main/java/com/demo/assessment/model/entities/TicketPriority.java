package com.demo.assessment.model.entities;

import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;

import javax.persistence.*;

@Entity
@Table(name = "ticket_priority")
public class TicketPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "delivery_priority")
    private DeliveryPriority deliveryPriority;

    @Column(name = "customer_type")
    private CustomerType customerType;

    public TicketPriority(int id, DeliveryPriority deliveryPriority, CustomerType customerType) {
        this.id = id;
        this.deliveryPriority = deliveryPriority;
        this.customerType = customerType;
    }

    public TicketPriority() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryPriority getDeliveryPriority() {
        return deliveryPriority;
    }

    public void setDeliveryPriority(DeliveryPriority deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
