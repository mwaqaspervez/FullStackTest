package com.demo.truckitin.service;

import com.demo.truckitin.model.QueryResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class QueryResolverServiceTest {

    private final QueryResolverService service = new QueryResolverService();


    @Test
    void resolve_success_case1() {
        QueryResponse response = service.resolve("5 + 4 / ( 6 * 3)");

        assertNotNull(response);
        assertEquals("5463*/+", response.getPostfix());
        assertEquals(5, (int) response.getResult());
    }

    @Test
    void resolve_success_case2() {
        QueryResponse response = service.resolve("2 * (6+5) * 2");

        assertNotNull(response);
        assertEquals("265+*2*", response.getPostfix());
        assertEquals(44, response.getResult());
    }

    @Test
    void resolve_fail() {
        assertThrows(EmptyStackException.class, () ->
                service.resolve("5 + 4  6 * 3)"));
    }
}