package com.demo.assessment.strategy;

import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.TicketPriorityType;

public interface IPriorityStrategy {

    TicketPriorityType getPriority(CustomerType customerType);
}
