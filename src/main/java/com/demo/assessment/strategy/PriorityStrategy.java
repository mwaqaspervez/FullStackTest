package com.demo.assessment.strategy;

import com.demo.assessment.model.entities.TicketPriority;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.TicketPriorityType;
import com.demo.assessment.repository.TicketPriorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PriorityStrategy implements IPriorityStrategy {

    private final TicketPriorityRepository ticketPriorityRepository;
    private final Map<CustomerType, TicketPriorityType> priorityMap;
    private final Logger LOGGER = LoggerFactory.getLogger(PriorityStrategy.class.getName());

    public PriorityStrategy(TicketPriorityRepository ticketPriorityRepository) {
        this.ticketPriorityRepository = ticketPriorityRepository;
        priorityMap = new HashMap<>();
        init();
    }

    public void init() {
        LOGGER.info("Initializing customer type with ticker priority");
        List<TicketPriority> list = this.ticketPriorityRepository.findAll();
        list.forEach(e -> priorityMap.put(e.getCustomerType(), e.getTicketPriority()));
    }

    @Override
    public TicketPriorityType getPriority(CustomerType customerType) {
        TicketPriorityType priority = priorityMap.get(customerType);
        if (priority == null) {
            LOGGER.error("Priority not found for customer type {}", customerType);
            throw new IllegalArgumentException("No priority found for the given customer type");
        }
        return priority;
    }
}
