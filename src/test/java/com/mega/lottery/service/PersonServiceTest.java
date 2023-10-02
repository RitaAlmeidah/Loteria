package com.mega.lottery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import com.mega.lottery.model.Person;
import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.util.UtilFaker;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

	UtilFaker util = new UtilFaker();
	
	@Autowired
	private PersonService personService;

	@Nested
	@DisplayName("Cenarios do metodo getAllPersons()")
	class getAllPersons {

		@Test
		@DisplayName(value = "Retorna uma lista de pessoas vazia - deveRetornarVazio()")
		void deveRetornarVazio() {
		
			 List<PersonDTO> personDTOList1 = new ArrayList<>();
			 assertEquals(0, personDTOList1.size(), "A lista de PersonDTO deve estar vazia!");
		}
		
		@Test
		@DisplayName(value = "Retorna uma lista de pessoas preenchida ou vazia - naoDeveRetornarNula()")
		void naoDeveRetornarNula() {
		
			 List<PersonDTO> personDTOList2 = new ArrayList<>();
			 personDTOList2.add(util.fakerPersonDTO());
			 assertNotEquals(null, personDTOList2, "A lista de PersonDTO deve ser diferente de nula!");
		}
		
		@Test
		@DisplayName(value = "Retorna uma lista com todas as pessoas - deveMostrarTodosUsuarios()")
		void deveMostrarTodosUsuarios() {
		
			 List<PersonDTO> personDTOList3 = new ArrayList<>();
			 personDTOList3.add(util.fakerPersonDTO());
			 personDTOList3.add(util.fakerPersonDTO());
			 assertFalse(personDTOList3.isEmpty(), "A lista não deve estar vazia!");
		}
	}

	@Nested
	@DisplayName("Cenarios do metodo que consulta uma pessoa por id ou CPF  - getPerson()")
	class getPerson {
		
		Optional<Person> person = Optional.of(util.fakerPerson());
		ResponseEntity<PersonDTO> response = personService.getPerson(person);

		@Test
		@DisplayName(value = "Pessoa não encontrada - deveRetornar404()")
		void deveRetornar404() {
			Optional<Person> emptyPerson = Optional.empty();
			ResponseEntity<PersonDTO> response1 = personService.getPerson(emptyPerson);
			assertEquals(HttpStatus.NOT_FOUND, response1.getStatusCode());
		}

		@Test
		@DisplayName(value = "Pessoa não deve ser nula - deveRetornarPessoaPreenchida()")
		void deveRetornarPessoaPreenchida() {
			assertNotEquals(null, response.getBody());
		}
		
		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarNomePreenchido()")
		void deveRetornarNomePreenchido() {
			boolean nomeVazio = response.getBody() == null ||  response.getBody().getNome() == null || response.getBody().getNome().isEmpty();
		    assertFalse(nomeVazio, "O nome não deve estar vazio!");
		}
		
		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarEmailPreenchido()")
		void deveRetornarEmailPreenchido() {
			boolean emailVazio = response.getBody() == null ||  response.getBody().getEmail() == null || response.getBody().getEmail().isEmpty();
		    assertFalse(emailVazio, "O email não deve estar vazio!");
		}
		
		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarListaTickets()")
		void deveRetornarListaTickets() {
			boolean listaTicketVazio = response.getBody() == null ||  response.getBody().getTicket() == null;
		    assertFalse(listaTicketVazio, "Lista de tickets não deve estar nula!");
		}
	}
}
