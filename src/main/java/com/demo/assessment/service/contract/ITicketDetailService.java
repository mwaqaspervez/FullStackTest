package com.demo.assessment.service.contract;

import com.demo.assessment.model.TicketDetailResponse;

public interface ITicketDetailService {
    TicketDetailResponse getTicketDetails(int page, int size);
}
