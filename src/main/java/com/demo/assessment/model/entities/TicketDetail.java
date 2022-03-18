package com.demo.assessment.model.entities;

import com.demo.assessment.model.types.TicketPriorityType;

import javax.persistence.*;

@Entity
@Table(name = "ticket_detail")
public class TicketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ticket_priority")
    private TicketPriorityType ticketPriorityType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_details_id")
    private DeliveryDetails deliveryDetails;

    public TicketDetail(int id, TicketPriorityType ticketPriorityType, DeliveryDetails deliveryDetails) {
        this.id = id;
        this.ticketPriorityType = ticketPriorityType;
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

    public TicketPriorityType getTicketPriority() {
        return ticketPriorityType;
    }

    public void setTicketPriority(TicketPriorityType ticketPriorityType) {
        this.ticketPriorityType = ticketPriorityType;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
