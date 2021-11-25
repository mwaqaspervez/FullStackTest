package com.demo.assessment.service;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.repository.DeliveryDetailRepository;
import com.demo.assessment.repository.TicketDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class AutomatedTicketSchedulerTest {

    @Autowired
    @InjectMocks
    private AutomatedTicketScheduler scheduler;

    @Mock
    private DeliveryDetailRepository repository;

    @Mock
    private TicketDetailRepository ticketDetailRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void noOrderIsFound() {
        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(new ArrayList<>());
        scheduler.runScheduler();
        verify(ticketDetailRepo, times(0)).save(any());
    }

    @Test
    void estimatedTimeIsNotOver() {
        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(List.of(
                        new DeliveryDetails(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP,
                                ZonedDateTime.now().plusMinutes(20), 50, 5, 50, 50)
                ));
        scheduler.runScheduler();
        verify(ticketDetailRepo, times(0)).save(any());
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsVip() {
        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(List.of(
                        new DeliveryDetails(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP,
                                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50)
                ));
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getDeliveryPriority() == DeliveryPriority.HIGHEST && o.getDeliveryDetails().getId() == 1
        ));
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsNew() {
        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(List.of(
                        new DeliveryDetails(1, CustomerType.NEW, DeliveryStatus.ORDER_PICKED_UP,
                                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50)
                ));
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getDeliveryPriority() == DeliveryPriority.MEDIUM && o.getDeliveryDetails().getId() == 1
        ));
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsLoyal() {
        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(List.of(
                        new DeliveryDetails(1, CustomerType.LOYAL, DeliveryStatus.ORDER_PICKED_UP,
                                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50)
                ));
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getDeliveryPriority() == DeliveryPriority.HIGH && o.getDeliveryDetails().getId() == 1
        ));
    }
}