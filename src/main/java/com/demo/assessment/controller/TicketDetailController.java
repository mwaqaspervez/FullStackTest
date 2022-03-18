package com.demo.assessment.controller;

import com.demo.assessment.config.BadArgumentException;
import com.demo.assessment.model.TicketDetailResponse;
import com.demo.assessment.service.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1")
public class TicketDetailController {

    @Autowired
    private TicketDetailService ticketDetailService;

    @GetMapping("/tickets")
    public ResponseEntity<TicketDetailResponse> getTickets(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "pageSize", defaultValue = "50", required = false) Integer pageSize
    ) throws BadArgumentException {
        if (pageSize > 1000) {
            throw new BadArgumentException("Page size should be less or equal then 1000");
        }
        if (page < 0) {
            throw new BadArgumentException("Invalid page number");
        }

        TicketDetailResponse response = ticketDetailService.getTicketDetails(page, pageSize);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
