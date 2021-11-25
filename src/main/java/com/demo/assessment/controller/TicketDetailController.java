package com.demo.assessment.controller;

import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.service.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/v1")
public class TicketDetailController {

    @Autowired
    private TicketDetailService ticketDetailService;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDetail>> getTickets(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "pageSize", defaultValue = "50", required = false) Integer pageSize
    ) {
        List<TicketDetail> deliveryList = ticketDetailService.getTicketDetails(page, pageSize);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deliveryList);
    }
}
