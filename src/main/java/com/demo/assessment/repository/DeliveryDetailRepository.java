package com.demo.assessment.repository;

import com.demo.assessment.model.DeliveryDetails;
import com.demo.assessment.model.types.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<Integer, DeliveryDetails> {

    Optional<List<DeliveryDetails>> findByDeliveryStatusIn(List<DeliveryStatus> deliveryStatus);
}
