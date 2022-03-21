package com.demo.assessment.service;

import com.demo.assessment.model.TicketDetailResponse;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.repository.TicketDetailRepository;
import com.demo.assessment.service.contract.ITicketDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketDetailService implements ITicketDetailService {
    private final TicketDetailRepository ticketDetailRepo;

    public TicketDetailService(TicketDetailRepository ticketDetailRepo) {
        this.ticketDetailRepo = ticketDetailRepo;
    }

    /**
     * Returns the tickets defined by the page and size of the page
     *
     * @param page the page to return
     * @param size The page size to return
     * @return {@link TicketDetailResponse} object containing the list of tickets
     * and the ticket information.
     */
    public TicketDetailResponse getTicketDetails(int page, int size) {
        Page<TicketDetail> orderPage =
                ticketDetailRepo.findAll(PageRequest.of(page, size,
                        Sort.by("ticketPriorityType").descending()));

        if (orderPage.isEmpty()) {
            return new TicketDetailResponse(new ArrayList<>(), 0, 0);
        }
        return new TicketDetailResponse(orderPage.toList(), orderPage.getPageable().getPageNumber(), orderPage.getTotalElements());
    }
}
