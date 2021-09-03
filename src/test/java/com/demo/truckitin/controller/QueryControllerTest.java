package com.demo.truckitin.controller;

import com.demo.truckitin.model.QueryRequest;
import com.demo.truckitin.model.QueryResponse;
import com.demo.truckitin.service.QueryResolverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
class QueryControllerTest {

    @InjectMocks
    private QueryController queryController;

    @Mock
    private QueryResolverService queryResolverService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(queryController)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void resolveQuery() throws Exception {
        QueryRequest request = new QueryRequest();
        request.setEquation("5 + 4");

        Mockito.when(queryResolverService.resolve(any()))
                .thenReturn(new QueryResponse("54+", 9));

        MockHttpServletResponse result = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/compute")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();
        QueryResponse response = objectMapper.readValue(result.getContentAsString(), QueryResponse.class);
        assertNotNull(response);
        assertEquals("54+", response.getPostfix());
    }
}