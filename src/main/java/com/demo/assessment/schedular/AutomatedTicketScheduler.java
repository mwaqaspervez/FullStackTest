package com.demo.assessment.schedular;

import com.demo.assessment.model.entities.DeliveryDetails;
import com.demo.assessment.model.entities.TicketDetail;
import com.demo.assessment.model.DeliveryDetailsDto;
import com.demo.assessment.model.types.DeliveryPriority;
import com.demo.assessment.model.types.DeliveryStatus;
import com.demo.assessment.repository.DeliveryDetailRepository;
import com.demo.assessment.repository.TicketDetailRepository;
import com.demo.assessment.strategy.PriorityStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class AutomatedTicketScheduler {
    private final DeliveryDetailRepository orderDetailRepo;
    private final TicketDetailRepository ticketDetailRepo;
    private final PriorityStrategy priorityStrategy;
    private final Logger LOGGER = LoggerFactory.getLogger(AutomatedTicketScheduler.class.getName());

    public AutomatedTicketScheduler(DeliveryDetailRepository orderDetailRepo,
                                    TicketDetailRepository ticketDetailRepo,
                                    PriorityStrategy priorityStrategy) {
        this.orderDetailRepo = orderDetailRepo;
        this.ticketDetailRepo = ticketDetailRepo;
        this.priorityStrategy = priorityStrategy;
    }

    /**
     * Runs a scheduler that queries the not delivered order
     * and checks if the estimated time has passed.
     * Creates a ticket if delivery time is passed.
     */
    @Scheduled(cron = "${ticket.cron}")
    public void runScheduler() {
        LOGGER.info("Starting automated ticket scheduler");
        List<DeliveryDetailsDto> inProgressOrders =
                orderDetailRepo.findByDeliveryStatusAndTicket(DeliveryStatus.getInProgressStatus());

        if (inProgressOrders == null || inProgressOrders.isEmpty()) {
            return;
        }

        LOGGER.info("Found {} in progress order. Processing", inProgressOrders.size());
        inProgressOrders.forEach(order -> {
            DeliveryDetails deliveryDetails = order.getDeliveryDetails();
            if (deliveryDetails.getExpectedDeliveryTime().isBefore(ZonedDateTime.now())) {
                deliveryDetails.setDeliveryPriority(DeliveryPriority.HIGHEST);
                orderDetailRepo.save(deliveryDetails);
            }

            ZonedDateTime estimatedTime = ZonedDateTime.now()
                    .plusSeconds(deliveryDetails.getTimeToPrepare())
                    .plusSeconds(deliveryDetails.getTimeToReach());

            if (estimatedTime.isAfter(deliveryDetails.getExpectedDeliveryTime())) {
                // Raise a ticket.
                LOGGER.info("Raising a ticket for customer type {}", deliveryDetails.getCustomerType());
                TicketDetail ticketDetail = new TicketDetail();
                ticketDetail.setDeliveryDetails(deliveryDetails);
                ticketDetail.setTicketPriority(priorityStrategy.getPriority(deliveryDetails.getCustomerType()));
                ticketDetailRepo.save(ticketDetail);
            }
        });
    }
}
