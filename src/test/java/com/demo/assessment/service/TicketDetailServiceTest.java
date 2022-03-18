package com.demo.assessment.service;

import com.demo.assessment.model.TicketDetailResponse;
import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.model.types.CustomerType;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.repository.TicketDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
class TicketDetailServiceTest {

    private TicketDetailService ticketDetailService;
    @Mock
    private TicketDetailRepository ticketDetailRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        ticketDetailService = new TicketDetailService(ticketDetailRepository);
    }

    @Test
    void getTicketDetails_emptyRecords() {
        Mockito.when(ticketDetailRepository.findAll((Pageable) any()))
                .thenReturn(Page.empty());
        TicketDetailResponse response = ticketDetailService.getTicketDetails(0, 50);

        Assertions.assertEquals(0, response.getData().size());
        Assertions.assertEquals(0, response.getTotalRecords());
        Assertions.assertEquals(0, response.getPage());
    }

    @Test
    void getTicketDetails_ticketRecords() {
        Page<TicketDetail> pageable = new PageImpl<>(List.of(new TicketDetail(
                1, DeliveryPriority.HIGH, new DeliveryDetails(
                1, CustomerType.LOYAL, DeliveryStatus.ORDER_PREPARING,
                ZonedDateTime.now().plus(5, ChronoUnit.MINUTES),
                5, 5, 5, 5)
        )), PageRequest.of(0, 50), 1);

        Mockito.when(ticketDetailRepository.findAll((Pageable) any()))
                .thenReturn(pageable);

        TicketDetailResponse response = ticketDetailService.getTicketDetails(0, 50);

        Assertions.assertEquals(1, response.getData().size());
        Assertions.assertEquals(1, response.getTotalRecords());
        Assertions.assertEquals(0, response.getPage());
    }
}