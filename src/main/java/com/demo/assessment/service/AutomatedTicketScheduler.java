package com.demo.assessment.service;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.repository.DeliveryDetailRepository;
import com.demo.assessment.repository.TicketDetailRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class AutomatedTicketScheduler {
    private final DeliveryDetailRepository orderDetailRepo;
    private final TicketDetailRepository ticketDetailRepo;

    public AutomatedTicketScheduler(DeliveryDetailRepository orderDetailRepo,
                                    TicketDetailRepository ticketDetailRepo) {
        this.orderDetailRepo = orderDetailRepo;
        this.ticketDetailRepo = ticketDetailRepo;
    }

    /**
     * Runs a scheduler that queries the not delivered order
     * and checks if the estimated time has passed.
     * Creates a ticket if delivery time is passed.
     */
    @Scheduled(fixedDelay = 30000L)
    void runScheduler() {
        List<DeliveryDetails> inProgressOrders =
                orderDetailRepo.findByDeliveryStatusAndTicket(DeliveryStatus.getInProgressStatus());

        if (inProgressOrders == null || inProgressOrders.isEmpty()) {
            return;
        }

        inProgressOrders.forEach(order -> {

            ZonedDateTime estimatedTime = ZonedDateTime.now()
                    .plusSeconds(order.getTimeToPrepare())
                    .plusSeconds(order.getTimeToReach());
            if (estimatedTime.isAfter(order.getExpectedDeliveryTime())) {
                // Raise a ticket.
                TicketDetail ticketDetail = new TicketDetail();
                ticketDetail.setDeliveryDetails(order);


                DeliveryPriority priority;
                switch (order.getCustomerType()) {
                    case VIP:
                        priority = DeliveryPriority.HIGHEST;
                        break;
                    case LOYAL:
                        priority = DeliveryPriority.HIGH;
                        break;
                    case NEW:
                        priority = DeliveryPriority.MEDIUM;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                ticketDetail.setDeliveryPriority(priority);

                ticketDetailRepo.save(ticketDetail);
            }
        });
    }
}
