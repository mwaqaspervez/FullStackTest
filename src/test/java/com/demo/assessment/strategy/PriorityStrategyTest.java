package com.demo.assessment.strategy;

import com.demo.assessment.model.entities.TicketPriority;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
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
                .thenReturn(List.of(new TicketPriority(1, DeliveryPriority.MEDIUM, CustomerType.NEW),
                        new TicketPriority(2, DeliveryPriority.HIGH, CustomerType.LOYAL),
                        new TicketPriority(3, DeliveryPriority.HIGHEST, CustomerType.VIP)));
        priorityStrategy = new PriorityStrategy(ticketPriorityRepository);
    }

    @Test
    void getPriority_ForLoyal() {
        DeliveryPriority priority = priorityStrategy.getPriority(CustomerType.LOYAL);

        assertEquals(DeliveryPriority.HIGH, priority);
    }

    @Test
    void getPriority_ForNew() {
        DeliveryPriority priority = priorityStrategy.getPriority(CustomerType.NEW);

        assertEquals(DeliveryPriority.MEDIUM, priority);
    }

    @Test
    void getPriority_ForVIP() {
        DeliveryPriority priority = priorityStrategy.getPriority(CustomerType.VIP);

        assertEquals(DeliveryPriority.HIGHEST, priority);
    }

    @Test
    void getPriority_noPriorityFound() {
        assertThrows(IllegalArgumentException.class, () -> priorityStrategy.getPriority(null));
    }
}