package com.demo.assessment.controller;

import com.demo.assessment.service.TicketDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
class TicketDetailControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private TicketDetailController ticketDetailController;

    @Mock
    private TicketDetailService ticketDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(ticketDetailController)
                .build();
    }

    @Test
    void getTickets() throws Exception {

        Mockito.when(ticketDetailService.getTicketDetails(anyInt(), anyInt()))
                .thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTicketsWithPage() throws Exception {

        Mockito.when(ticketDetailService.getTicketDetails(2, 50))
                .thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tickets")
                        .param("page", "2")
                        .param("pageSize", "50")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
