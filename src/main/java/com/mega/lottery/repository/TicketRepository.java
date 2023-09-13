package com.mega.lottery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mega.lottery.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	public Optional<Ticket> findAllByNumeroBilhete(Long numeroBilhete);
}
