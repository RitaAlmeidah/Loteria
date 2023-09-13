package com.mega.lottery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<TicketDTO> getTicket(Optional<Ticket> ticketOptional) {
       
        TicketDTO ticketDTO = new TicketDTO();
        PersonDTO personDTO = new PersonDTO();
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            ticketDTO.setNumeroBilhete(ticket.getNumeroBilhete());
            ticketDTO.setStatus(ticket.getStatus());
            personDTO.setNome(ticket.getPerson().getNome());
            personDTO.setEmail(ticket.getPerson().getEmail());
            ticketDTO.setPerson(personDTO);
            return ResponseEntity.ok(ticketDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
	public ResponseEntity<List<TicketDTO>> getAllTicket(){
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
		return ResponseEntity.ok(ticketDTOLista);
	}
}

