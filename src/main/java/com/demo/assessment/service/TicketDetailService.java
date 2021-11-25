package com.demo.assessment.service;

import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.repository.TicketDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDetailService {
    private final TicketDetailRepository ticketDetailRepo;

    public TicketDetailService(TicketDetailRepository ticketDetailRepo) {
        this.ticketDetailRepo = ticketDetailRepo;
    }

    /**
     * Returns the tickets defined by the page and size of the page
     * @param page the page to return
     * @param size The page size to return
     * @return {@link List<TicketDetail>} object containing the ticket
     * list containing the ticket information.
     */
    public List<TicketDetail> getTicketDetails(int page, int size) {
        Page<TicketDetail> orderPage =
                ticketDetailRepo.findAll(PageRequest.of(page, size));

        if (orderPage.isEmpty()) {
            return new ArrayList<>();
        }
        return orderPage.toList();
    }
}
