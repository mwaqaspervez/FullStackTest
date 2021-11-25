package com.demo.assessment.model;

import com.demo.assessment.model.types.DeliveryPriority;

import javax.persistence.*;

@Entity
@Table(name = "ticket_detail")
public class TicketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private int id;

    @Column(name = "delivery_priority")
    private DeliveryPriority deliveryPriority;

    @Column(name = "delivery_details_id")
    @OneToOne
    private DeliveryDetails deliveryDetails;

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

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
