package com.demo.assessment.model.entities;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_details_id")
    private DeliveryDetails deliveryDetails;

    public TicketDetail(int id, DeliveryPriority deliveryPriority, DeliveryDetails deliveryDetails) {
        this.id = id;
        this.deliveryPriority = deliveryPriority;
        this.deliveryDetails = deliveryDetails;
    }

    public TicketDetail() {
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

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
