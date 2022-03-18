package com.demo.assessment.service;

import com.demo.assessment.model.DeliveryDetailsDto;
import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.model.types.TicketPriorityType;
import com.demo.assessment.repository.DeliveryDetailRepository;
import com.demo.assessment.repository.TicketDetailRepository;
import com.demo.assessment.schedular.AutomatedTicketScheduler;
import com.demo.assessment.strategy.PriorityStrategy;
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

    @Mock
    private PriorityStrategy priorityStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        scheduler = new AutomatedTicketScheduler(repository, ticketDetailRepo, priorityStrategy);
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
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGHEST,
                ZonedDateTime.now().plusMinutes(20), 50, 5, 50, 50, 1, TicketPriorityType.LOW));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        scheduler.runScheduler();
        verify(ticketDetailRepo, times(0)).save(any());
    }

    @Test
    void expectedDeliveryTimeIsOver() {
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGHEST,
                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50, 1, TicketPriorityType.LOW));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        scheduler.runScheduler();
        verify(repository, times(1)).save(argThat((DeliveryDetails o) ->
                o.getDeliveryPriority() == DeliveryPriority.HIGHEST
        ));
    }

    @Test
    void expectedDeliveryTimeIsNotOver() {
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGHEST,
                ZonedDateTime.now().plusMinutes(20), 50, 5, 50, 50, 1, TicketPriorityType.LOW));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        scheduler.runScheduler();
        verify(repository, times(0)).save(argThat((DeliveryDetails o) ->
                o.getDeliveryPriority() == DeliveryPriority.HIGHEST
        ));
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsVip() {
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.VIP, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGHEST,
                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50, 0, null));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        Mockito.when(priorityStrategy.getPriority(any()))
                .thenReturn(TicketPriorityType.HIGHEST);
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getTicketPriority() == TicketPriorityType.HIGHEST && o.getDeliveryDetails().getId() == 1
        ));
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsNew() {
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.NEW, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGH,
                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50, 0, null));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        Mockito.when(priorityStrategy.getPriority(any()))
                .thenReturn(TicketPriorityType.LOW);
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getTicketPriority() == TicketPriorityType.LOW && o.getDeliveryDetails().getId() == 1
        ));
    }

    @Test
    void estimatedTimeIsOverAndCustomerIsLoyal() {
        List<DeliveryDetailsDto> list = new ArrayList<>();
        list.add(new DeliveryDetailsDto(1, CustomerType.LOYAL, DeliveryStatus.ORDER_PICKED_UP, DeliveryPriority.HIGH,
                ZonedDateTime.now().minusMinutes(20), 50, 5, 50, 50, 0, null));

        Mockito.when(repository.findByDeliveryStatusAndTicket(any()))
                .thenReturn(list);
        Mockito.when(priorityStrategy.getPriority(any()))
                .thenReturn(TicketPriorityType.HIGH);
        scheduler.runScheduler();

        verify(ticketDetailRepo, times(1)).save(argThat((TicketDetail o) ->
                o.getTicketPriority() == TicketPriorityType.HIGH && o.getDeliveryDetails().getId() == 1
        ));
    }
}