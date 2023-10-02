package com.mega.lottery.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mega.lottery.model.Person;
import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.util.UtilFaker;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketControllerTest {
	
	UtilFaker util = new UtilFaker();
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Nested
	@DisplayName("Cenarios do metodo que busca todos os tickets, e retorna em uma lista - getAll()")
	class getAll {
		
		@Test
		@DisplayName(value = "Ao busca a lista de tickets - deveretornarStatus200()")
		void deveretornarStatus200() {
			ResponseEntity<List<TicketDTO>> resposta = testRestTemplate.exchange("/ticket", HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<TicketDTO>>() {
					});
			assertEquals(HttpStatus.OK, resposta.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que busca ticket por id - getById()")
	class getById {
		
		@Test
		@DisplayName(value = "Ticket encontrado - deveRetornarStatus200()")
		void deveRetornarStatus200() {
			ResponseEntity<List<Ticket>> resposta = CadastrarTicket();
			Long id = resposta.getBody().get(0).getId();
			ResponseEntity<TicketDTO> resposta1 = testRestTemplate.exchange("/ticket/{id}", HttpMethod.GET, null, 
					new ParameterizedTypeReference<TicketDTO>() {
					}, id);
			assertEquals(HttpStatus.OK, resposta1.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		}
		
		@Test
		@DisplayName(value ="Ticket nao encontrado - deve deveRetornarStatus404()")
		void deveRetornarStatus404() {
			ResponseEntity<Ticket> resposta2 = testRestTemplate.exchange("/ticket/{id}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Ticket>() {
			}, 10000);
			assertEquals(HttpStatus.NOT_FOUND, resposta2.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		}
	}
		
	@Nested
	@DisplayName("Cenarios do metodo que busca ticket por numero do bilhete - getNumeroBilhete()")
	class getNumeroBilhete {

		@Test
		@DisplayName(value ="Ticket encontrado - deveRetornarStatus200()")
		void deveRetornarStatus200() {
			
			ResponseEntity<List<Ticket>> resposta = CadastrarTicket();
			Long numeroBilhete = resposta.getBody().get(0).getNumeroBilhete();
			
			ResponseEntity<Ticket> resposta1 = testRestTemplate.exchange("/ticket/numeroBilhete/{numeroBilhete}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Ticket>() {
			}, numeroBilhete);
			assertEquals(HttpStatus.OK, resposta1.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		}
	
		@Test
		@DisplayName(value = "Ticket nao encontrado - deveRetornarStatus404()")
		void deveRetornarStatus404() {
			
			ResponseEntity<Ticket> resposta2 = testRestTemplate.exchange("/ticket/numeroBilhete/{numeroBilhete}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Ticket>() {
			}, 0);
			assertEquals(HttpStatus.NOT_FOUND, resposta2.getStatusCode(),
					"Status Code da lista de tickets deve ser igual");
		}
	
		@Nested
		@DisplayName("Cenarios do metodo que cadastra um ticket ou mais - post()")
		class post {
			
			@Test
			@DisplayName(value = "Ticket cadastrado - deveRetornarStatus201()")
			void deveRetornarStatus201() {
				HttpEntity<List<Ticket>> requisicao = new HttpEntity<List<Ticket>>(util.fakerTicketPost());
				ResponseEntity<List<Ticket>> resposta = testRestTemplate.exchange("/ticket", HttpMethod.POST, requisicao,
						new ParameterizedTypeReference<List<Ticket>>() {
				});
				assertEquals(HttpStatus.CREATED, resposta.getStatusCode(), "Status Code da lista de tickets deve ser igual");
			}
			
			@Test
			@DisplayName(value = "123")
			void deveRetornarStatus404() {
				List<Ticket> listTicket = util.fakerTicketPost();
				listTicket.get(0).getPerson().setId(10000L);
				HttpEntity<List<Ticket>> corpoRequisicao = new HttpEntity<List<Ticket>>(listTicket);
				
				ResponseEntity<List<Ticket>> resposta = testRestTemplate.exchange("/ticket", HttpMethod.POST, corpoRequisicao,
						new ParameterizedTypeReference<List<Ticket>>() {
						});
				assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
			}
		}
	}

	public ResponseEntity<List<Ticket>> CadastrarTicket() {
		
		HttpEntity<List<Ticket>> requisicao = new HttpEntity<List<Ticket>>(util.fakerTicketPost());
		ResponseEntity<List<Ticket>> resposta = testRestTemplate.exchange("/ticket", HttpMethod.POST, requisicao,
				new ParameterizedTypeReference<List<Ticket>>() {
		});
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		return resposta;
		
	}
	
	
	
	@Nested
	@DisplayName("Cenarios do metodo que atualiza o cadastro de um ticket - put()")
	class put {

		@Test
		@DisplayName(value = "Ao atualizar o ticket - deveRetornarStatus200()")
		void deveRetornarStatus200() {
			ResponseEntity<List<Ticket>> ticketCadastrado = CadastrarTicket();
			
			HttpEntity<Ticket> requisicao = new HttpEntity<Ticket>(util.fakerTicketPut());
			requisicao.getBody().setId(ticketCadastrado.getBody().get(1).getId());
			
			ResponseEntity<Ticket> resposta = testRestTemplate.exchange("/ticket", HttpMethod.PUT, requisicao,
					new ParameterizedTypeReference<Ticket>() {
			});
			assertEquals(HttpStatus.OK, resposta.getStatusCode(), "Status Code da lista de tickets deve ser igual");
		}
	}
	
	
	@Nested
	@DisplayName("Cenarios do metodo que deleta ticket por id - delete()")
	class delete {

		@Test
		@DisplayName(value = "Ticket encontrado - deveRetornarStatus204()")
		void deveRetornarStatus204() {
			ResponseEntity<List<Ticket>> resposta = CadastrarTicket();
			Long id = resposta.getBody().get(0).getId();
			ResponseEntity<Void> resposta2 = testRestTemplate.exchange("/ticket/{id}", HttpMethod.DELETE, null,
					new ParameterizedTypeReference<Void>() {
					}, id);
			assertEquals(HttpStatus.NO_CONTENT, resposta2.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}

		@Test
		@DisplayName(value = "Ticket nao encontrado - deveRetornarStatus404()")
		void deveRetornarStatus404() {

			ResponseEntity<Void> resposta3 = testRestTemplate.exchange("/ticket/{id}", HttpMethod.DELETE, null,
					new ParameterizedTypeReference<Void>() {
					}, 0);
			assertEquals(HttpStatus.NOT_FOUND, resposta3.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}

}
