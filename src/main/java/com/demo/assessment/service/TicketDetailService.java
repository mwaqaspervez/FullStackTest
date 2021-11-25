package com.demo.assessment.service;

import com.demo.assessment.model.DeliveryDetails;
import com.demo.assessment.repository.DeliveryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryDetailService {

    @Autowired
    private DeliveryDetailRepository orderDetailRepo;

    public List<DeliveryDetails> getOrderDetails(int page, int size) {
        Page<DeliveryDetails> orderPage =
                orderDetailRepo.findAll(PageRequest.of(page, size));

        if (orderPage.isEmpty()) {
            return new ArrayList<>();
        }

        return orderPage.toList();
    }
}
