package com.mega.lottery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mega.lottery.model.Person;
import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public ResponseEntity<PersonDTO> getPerson(Optional<Person> personOptional) {
	
		PersonDTO personDTO = new PersonDTO();
		List<TicketDTO> ticketDTOLista = new ArrayList<>();
		
		if (personOptional.isPresent()) {
		    Person person = personOptional.get();
		    personDTO.setNome(person.getNome());
			personDTO.setEmail(person.getEmail());
			
			List<Ticket> listaTicket = person.getTicket();
			for(Ticket ticket : listaTicket) {
				TicketDTO ticketDTO = new TicketDTO();
				ticketDTO.setNumeroBilhete(ticket.getNumeroBilhete());
				ticketDTO.setStatus(ticket.getStatus());
				ticketDTOLista.add(ticketDTO);
			}
			personDTO.setTicket(ticketDTOLista);
		    return ResponseEntity.ok(personDTO);
		    
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	public ResponseEntity<List<PersonDTO>> getAllPersons() {
		
	    List<Person> personList = personRepository.findAll(); 
	    List<PersonDTO> personDTOList = new ArrayList<>();
	    
	    for (Person person : personList) {
	    	PersonDTO personDTO = new PersonDTO();
	        personDTO.setNome(person.getNome()); 
	        personDTO.setEmail(person.getEmail());

	        List<TicketDTO> ticketDTOLista = new ArrayList<>();
	        List<Ticket> listaTicket = person.getTicket();
			for(Ticket ticket : listaTicket) {
				TicketDTO ticketDTO = new TicketDTO();
				ticketDTO.setNumeroBilhete(ticket.getNumeroBilhete());
				ticketDTO.setStatus(ticket.getStatus());
				ticketDTOLista.add(ticketDTO);
			}
			
			personDTO.setTicket(ticketDTOLista);
	        personDTOList.add(personDTO);
	    }

	    return ResponseEntity.ok(personDTOList);
	}

}
