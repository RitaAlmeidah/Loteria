package com.mega.lottery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.util.UtilFaker;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketServiceTest {
	UtilFaker util = new UtilFaker();
	
	@Autowired
	private TicketService ticketService;
	
	@Nested
	@DisplayName("Cenarios do metodo getAllTicket()")
	class getAllTicket{
		
		@Test
		@DisplayName(value = "Retorna uma lista de tickets vazia - deveRetornarVazio()")
		void deveRetornarVazio() {
			
			List<TicketDTO> ticketDTOList1 = new ArrayList<>();
			assertEquals(0, ticketDTOList1.size(), "A lista de TicketDTO deve estar vazia!");
		}
		
		@Test
		@DisplayName(value = "Retorna uma lista de tickets preenchida ou vazia - naoDeveRetornarNula()")
		void naoDeveRetornarNula() {
			
			List<TicketDTO> ticketDTOList2 = new ArrayList<>();
			ticketDTOList2.add(util.fakerTicketDTO());
			assertNotEquals(null, ticketDTOList2, "A lista de ticketDTO deve ser diferente de nula!");
		}
		
		@Test
		@DisplayName(value = "Retorna uma lista com todos os tickets - deveMostrarTodosTickets()")
		void deveMostrarTodosTickets() {
			
			List<TicketDTO> ticketDTOList3 = new ArrayList<>();
			ticketDTOList3.add(util.fakerTicketDTO());
			ticketDTOList3.add(util.fakerTicketDTO());
			assertFalse(ticketDTOList3.isEmpty(), "A lista não deve estar vazia!");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que consulta um ticket por id ou numero do bilhete  - getTicket()")
	class getTicket {
		
		Optional<Ticket> ticket = Optional.of(util.fakerTicket());
		ResponseEntity<TicketDTO> response = ticketService.getTicket(ticket);
		
		@Test
		@DisplayName(value = "Ticket não encontrado - deveRetornar404()")
		void deveRetornar404() {
			Optional<Ticket> emptyTicket = Optional.empty();
			ResponseEntity<TicketDTO> response1 = ticketService.getTicket(emptyTicket);
			assertEquals(HttpStatus.NOT_FOUND, response1.getStatusCode());
		}
		
		@Test
		@DisplayName(value = "Ticket não deve ser nulo - deveRetornarTicketPreenchido()")
		void deveRetornarTicketPreenchido() {
			assertNotEquals(null, response.getBody());
		}
		
		@Test
		@DisplayName(value = "Ticket encontrado - deveRetornarNumeroBilhetePreenchido()")
		void deveRetornarNumeroBilhetePreenchido() {
		    Long numeroBilhete = response.getBody() != null ? response.getBody().getNumeroBilhete() : null;
		    assertNotNull(numeroBilhete, "O número do bilhete não deve ser nulo!");
		    assertTrue(numeroBilhete != null && numeroBilhete != 0L, "O número do bilhete deve ser diferente de zero!");
		}
	
		@Test
		@DisplayName(value = "Ticket encontrado - deveRetornarStatusBilhetePreenchido()")
		void deveRetornarStatusBilhetePreenchido() {
			
			boolean statusVazio = response.getBody() == null || response.getBody().getStatus() == null || response.getBody().getStatus().isEmpty();
			assertFalse(statusVazio, "O nome não deve estar vazio!");
		}
		
		
	}
	
}
