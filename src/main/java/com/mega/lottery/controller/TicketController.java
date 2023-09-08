package com.mega.lottery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mega.lottery.model.Ticket;
import com.mega.lottery.repository.TicketRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Ticket>> getAll(){
		return ResponseEntity.ok(ticketRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getById(@PathVariable Long id) {
		return ticketRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/numeroBilhete/{numeroBilhete}")
	public ResponseEntity<List<Ticket>> getNumeroBilhete(@PathVariable Integer numeroBilhete){
		return ResponseEntity.ok(ticketRepository.findAllByNumeroBilhete(numeroBilhete));
	}
	
	@PostMapping
	public ResponseEntity<Ticket> post(@Valid @RequestBody Ticket ticket){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ticketRepository.save(ticket));
	}
	
	@PutMapping
	public ResponseEntity<Ticket> put(@Valid @RequestBody Ticket ticket) {
		return ticketRepository.findById(ticket.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(ticketRepository.save(ticket)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		
		if(ticket.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		ticketRepository.deleteById(id);
	}

}
