package com.demo.assessment.repository;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.types.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetails, Integer> {

    @Query(value = "select new DeliveryDetails(dd.id, dd.customerType, dd.deliveryStatus, dd.expectedDeliveryTime," +
            "dd.currentDistance, dd.riderRating, dd.timeToPrepare, dd.timeToReach) " +
            "from DeliveryDetails dd " +
            "left join TicketDetail td " +
            "on dd.id = td.deliveryDetails " +
            "where td.id is NULL and " +
            "dd.deliveryStatus IN (:deliveryStatus)")
    List<DeliveryDetails> findByDeliveryStatusAndTicket(List<DeliveryStatus> deliveryStatus);
}
