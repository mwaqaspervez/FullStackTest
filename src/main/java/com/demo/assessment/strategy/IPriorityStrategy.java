package com.demo.assessment.strategy;

import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;

public interface IPriorityStrategy {

    DeliveryPriority getPriority(CustomerType customerType);
}
