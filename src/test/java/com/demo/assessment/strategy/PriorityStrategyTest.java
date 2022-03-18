package com.demo.assessment.strategy;

import com.demo.assessment.model.entities.TicketPriority;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.TicketPriorityType;
import com.demo.assessment.repository.TicketPriorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityStrategyTest {

    private PriorityStrategy priorityStrategy;
    @Mock
    private TicketPriorityRepository ticketPriorityRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(ticketPriorityRepository.findAll())
                .thenReturn(List.of(new TicketPriority(1, TicketPriorityType.LOW, CustomerType.NEW),
                        new TicketPriority(2, TicketPriorityType.HIGH, CustomerType.LOYAL),
                        new TicketPriority(3, TicketPriorityType.HIGHEST, CustomerType.VIP)));
        priorityStrategy = new PriorityStrategy(ticketPriorityRepository);
    }

    @Test
    void getPriority_ForLoyal() {
        TicketPriorityType priority = priorityStrategy.getPriority(CustomerType.LOYAL);

        assertEquals(TicketPriorityType.HIGH, priority);
    }

    @Test
    void getPriority_ForNew() {
        TicketPriorityType priority = priorityStrategy.getPriority(CustomerType.NEW);

        assertEquals(TicketPriorityType.LOW, priority);
    }

    @Test
    void getPriority_ForVIP() {
        TicketPriorityType priority = priorityStrategy.getPriority(CustomerType.VIP);

        assertEquals(TicketPriorityType.HIGHEST, priority);
    }

    @Test
    void getPriority_noPriorityFound() {
        assertThrows(IllegalArgumentException.class, () -> priorityStrategy.getPriority(null));
    }
}