package com.demo.assessment.repository;

import com.demo.assessment.model.entities.TicketPriority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketPriorityRepository extends JpaRepository<TicketPriority, Integer> {

}
