package com.demo.assessment.repository;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.DeliveryDetailsDto;
import com.demo.assessment.model.types.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetails, Integer> {

    @Query(value = "select new com.demo.assessment.model.DeliveryDetailsDto( dd.id, dd.customerType, dd.deliveryStatus, " +
            "dd.deliveryPriority, dd.expectedDeliveryTime, dd.currentDistance, dd.riderRating, dd.timeToPrepare, dd.timeToReach, " +
            "td.id, td.ticketPriorityType) " +
            "from DeliveryDetails dd " +
            "left join TicketDetail td " +
            "on dd.id = td.deliveryDetails " +
            "where td.id is NULL and " +
            "dd.deliveryStatus IN (:deliveryStatus)")
    List<DeliveryDetailsDto> findByDeliveryStatusAndTicket(List<DeliveryStatus> deliveryStatus);
}
