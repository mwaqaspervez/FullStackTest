package com.demo.assessment.model;

import com.demo.assessment.model.entities.TicketDetail;

import java.util.List;

public class TicketDetailResponse {

    private List<TicketDetail> data;
    private int page;
    private long totalRecords;

    public TicketDetailResponse(List<TicketDetail> data, int page, long totalRecords) {
        this.data = data;
        this.page = page;
        this.totalRecords = totalRecords;
    }

    public List<TicketDetail> getData() {
        return data;
    }

    public void setData(List<TicketDetail> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
