package com.demo.assessment.controller;

import com.demo.assessment.config.SecurityConfiguration;
import com.demo.assessment.model.LoginRequest;
import com.demo.assessment.model.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class LoginControllerTest {

    private ObjectMapper mapper;
    private MockMvc mockMvc;
    private AppUser user;
    private LoginRequest loginRequestDto;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private SecurityConfiguration securityConfiguration;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Mockito.when(securityConfiguration.authenticationManagerBean())
                .thenReturn(authenticationManager);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new LoginController(securityConfiguration))
                .build();

        loginRequestDto = new LoginRequest("user@email.com", "password");
        user = new AppUser();
        user.setUsername("user@email.com");
        user.setPassword(passwordEncoder.encode("password"));
    }

    @Test
    void test_login_returnsOk() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .content(mapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_login_returnsTokenResponse() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .content(mapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    void test_login_usesRetrievedUser() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .content(mapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String token = result.getResponse().getContentAsString();
        assertNotNull(token);
    }

    @Test
    void test_loginUsingWrongPassword_throwsException() {
        LoginRequest requestDto = new LoginRequest("user@email.com", "wrong-password");
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(null);

        assertThrows(Exception.class, () ->
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/login")
                        .content(mapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)));
    }
}