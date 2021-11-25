package com.demo.assessment.controller;

import com.demo.assessment.model.DeliveryDetails;
import com.demo.assessment.service.DeliveryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1/deliveries")
public class TicketDetailController {

    @Autowired
    private DeliveryDetailService deliveryDetailService;

    @GetMapping("/")
    public ResponseEntity<List<DeliveryDetails>> getOrders(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "pageSize", defaultValue = "50", required = false) Integer pageSize
    ) {

        List<DeliveryDetails> deliveryList = deliveryDetailService.getOrderDetails(page, pageSize);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deliveryList);
    }
}
