package com.mega.lottery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.repository.TicketRepository;

@Service
public class TicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<TicketDTO> getTicket(Optional<Ticket> ticketOptional) {
       
    	logger.info("TicketService - Iniciando Metodo getTicket() - Ticket = {}", ticketOptional);
    	
        TicketDTO ticketDTO = new TicketDTO();
        PersonDTO personDTO = new PersonDTO();
        
        if (ticketOptional.isPresent()) {
        	
        	logger.info("Ticket encontrado!");
        	
            Ticket ticket = ticketOptional.get();
            ticketDTO.setNumeroBilhete(ticket.getNumeroBilhete());
            ticketDTO.setStatus(ticket.getStatus());
            personDTO.setNome(ticket.getPerson().getNome());
            personDTO.setEmail(ticket.getPerson().getEmail());
            ticketDTO.setPerson(personDTO);
            
            return ResponseEntity.ok(ticketDTO);
        } else {
        	logger.warn("Ticket não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
	public ResponseEntity<List<TicketDTO>> getAllTicket(){
		
		logger.info("TicketService - Iniciando Metodo getAllTicket()");
		
		List<Ticket> ticketLista = ticketRepository.findAll();
		List<TicketDTO> ticketDTOLista = new ArrayList<>();
		
		for(Ticket ticket : ticketLista) {
			TicketDTO ticketDTO = new TicketDTO();
			PersonDTO personDTO = new PersonDTO();
			ticketDTO.setNumeroBilhete(ticket.getNumeroBilhete());
			ticketDTO.setStatus(ticket.getStatus());
			personDTO.setNome(ticket.getPerson().getNome());
			personDTO.setEmail(ticket.getPerson().getEmail());
			ticketDTO.setPerson(personDTO);
			
			ticketDTOLista.add(ticketDTO);
		}
		logger.info("Lista TicketDTO: {}", ticketDTOLista);
		return ResponseEntity.ok(ticketDTOLista);
		
	}
}

