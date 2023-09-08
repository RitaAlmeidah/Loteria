package com.mega.lottery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mega.lottery.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	public List <Ticket> findAllByNumeroBilhete(@Param("numeroBilhete") Integer numeroBilhete);
}
