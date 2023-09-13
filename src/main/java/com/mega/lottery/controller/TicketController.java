package com.mega.lottery.controller;

import java.util.ArrayList;
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

import com.mega.lottery.model.Person;
import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.repository.PersonRepository;
import com.mega.lottery.repository.TicketRepository;
import com.mega.lottery.service.SorteioService;
import com.mega.lottery.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private SorteioService sorteioService;
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping
	public ResponseEntity<List<TicketDTO>> getAll(){
		return ticketService.getAllTicket();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> getById(@PathVariable Long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		return ticketService.getTicket(ticketOptional);
	}
	
	@GetMapping("/numeroBilhete/{numeroBilhete}")
	public ResponseEntity<TicketDTO> getNumeroBilhete(@PathVariable Long numeroBilhete){
		Optional<Ticket> ticketOptional = ticketRepository.findAllByNumeroBilhete(numeroBilhete);
		return ticketService.getTicket(ticketOptional);
	}
	
	@PostMapping
	public ResponseEntity<List<Ticket>> post(@Valid @RequestBody List<Ticket> ticket){
		List<Ticket> lista = new ArrayList<>();
		
		for(Ticket bilhete : ticket) {
			Long id = bilhete.getPerson().getId();
			Optional<Person> person = personRepository.findById(id);
			
			if(person.isPresent()) {
				bilhete.getPerson().setId(person.get().getId());
				bilhete.setNumeroBilhete(sorteioService.sortearSequencia());
				lista.add(bilhete);
			}else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ticketRepository.saveAll(lista));
		
	
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
