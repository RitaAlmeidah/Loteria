package com.mega.lottery.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.util.UtilFaker;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	UtilFaker util = new UtilFaker();

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Nested
	@DisplayName("Cenarios do metodo que busca todas as pessoas, e retorna em uma lista - getAll()")
	class getAll {
		
		@Test
		@DisplayName(value = "Ao buscar a lista de pessoas - deveretornarStatus200()")
		void deveretornarStatus200() {
			ResponseEntity<List<PersonDTO>> resposta = testRestTemplate.exchange("/person", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<PersonDTO>>() {
					});
			assertEquals(HttpStatus.OK, resposta.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que busca pessoa por id - getById()")
	class getById {
		
		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarStatus200()")
		void deveRetornarStatus200() {
		
			HttpEntity<Person> resposta = CadastrarPessoa();
		
			ResponseEntity<Person> resposta1 = testRestTemplate.exchange("/person/{id}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Person>() {
					}, resposta.getBody().getId());
			assertEquals(HttpStatus.OK, resposta1.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
		
		@Test
		@DisplayName(value = "Pessoa nao encontrada - deveRetornarStatus404()")
		void deveRetornarStatus404() {
		
			ResponseEntity<Person> resposta2 = testRestTemplate.exchange("/person/{id}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Person>() {
					}, 10000);
			assertEquals(HttpStatus.NOT_FOUND, resposta2.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que busca pessoa por CPF - getByCpf()")
	class getByCpf {

		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarStatus200()")
		void deveRetornarStatus200() {

			HttpEntity<Person> resposta = CadastrarPessoa();

			ResponseEntity<Person> resposta1 = testRestTemplate.exchange("/person/cpf/{cpf}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Person>() {
					}, resposta.getBody().getCpf());
			assertEquals(HttpStatus.OK, resposta1.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}

		@Test
		@DisplayName(value = "Pessoa nao encontrada - deveRetornarStatus404()")
		void deveRetornarStatus404() {

			ResponseEntity<Person> resposta2 = testRestTemplate.exchange("/person/cpf/{cpf}", HttpMethod.GET, null,
					new ParameterizedTypeReference<Person>() {
					}, 0);
			assertEquals(HttpStatus.NOT_FOUND, resposta2.getStatusCode(),
					"Status Code da lista de pessoas deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que cadastra pessoa - post()")
	class post {

		@Test
		@DisplayName(value = "Ao cadastrar uma pessoa - deveRetornarStatus201()")
		void deveRetornarStatus201() {
			HttpEntity<Person> corpoRequisicao = new HttpEntity<Person>(util.fakerPersonPost());
			ResponseEntity<Person> resposta = testRestTemplate.exchange("/person", HttpMethod.POST, corpoRequisicao,
					new ParameterizedTypeReference<Person>() {
					});
			assertEquals(HttpStatus.CREATED, resposta.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que atualiza o cadastro de uma pessoa - put()")
	class put {

		@Test
		@DisplayName(value = "Ao atualizar o cadastro de uma pessoa - deveRetornarStatus200()")
		void deveRetornarStatus200() {

			HttpEntity<Person> resposta = CadastrarPessoa();

			HttpEntity<Person> corpoRequisicao2 = new HttpEntity<Person>(util.fakerPersonPut());
			corpoRequisicao2.getBody().setId(resposta.getBody().getId());
			ResponseEntity<Person> resposta1 = testRestTemplate.exchange("/person", HttpMethod.PUT, corpoRequisicao2,
					new ParameterizedTypeReference<Person>() {
					}, resposta.getBody().getId());
			assertEquals(HttpStatus.OK, resposta1.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}
	
	@Nested
	@DisplayName("Cenarios do metodo que deleta pessoa por id - delete()")
	class delete {

		@Test
		@DisplayName(value = "Pessoa encontrada - deveRetornarStatus204()")
		void deveRetornarStatus204() {

			HttpEntity<Person> resposta = CadastrarPessoa();

			ResponseEntity<Void> resposta2 = testRestTemplate.exchange("/person/{id}", HttpMethod.DELETE, null,
					new ParameterizedTypeReference<Void>() {
					}, resposta.getBody().getId());
			assertEquals(HttpStatus.NO_CONTENT, resposta2.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}

		@Test
		@DisplayName(value = "Pessoa nao encontrada - deveRetornarStatus404()")
		void deveRetornarStatus404() {

			ResponseEntity<Void> resposta3 = testRestTemplate.exchange("/person/{id}", HttpMethod.DELETE, null,
					new ParameterizedTypeReference<Void>() {
					}, 0);
			assertEquals(HttpStatus.NOT_FOUND, resposta3.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		}
	}
	
	public ResponseEntity<Person> CadastrarPessoa() {

		HttpEntity<Person> requisicao = new HttpEntity<Person>(util.fakerPersonPost());
		ResponseEntity<Person> resposta = testRestTemplate.exchange("/person", HttpMethod.POST, requisicao,
				new ParameterizedTypeReference<Person>() {
				});
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode(), "Status Code da lista de pessoas deve ser igual");
		return resposta;
	}
}
