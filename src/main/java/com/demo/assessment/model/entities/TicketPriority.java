package com.demo.assessment.model.entities;

import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.TicketPriorityType;

import javax.persistence.*;

@Entity
@Table(name = "ticket_priority")
public class TicketPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ticket_priority")
    private TicketPriorityType ticketPriority;

    @Column(name = "customer_type")
    private CustomerType customerType;

    public TicketPriority(int id, TicketPriorityType ticketPriority, CustomerType customerType) {
        this.id = id;
        this.ticketPriority = ticketPriority;
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

    public TicketPriorityType getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriorityType ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
