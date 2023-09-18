package com.mega.lottery.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.repository.PersonRepository;
import com.mega.lottery.service.PersonService;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	@GetMapping
	public ResponseEntity<List<PersonDTO>> getAll() {
		
		logger.info("PersonController - Iniciando Metodo getAll()" );
		
		return personService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> getById(@PathVariable Long id) {
		
		logger.info("PersonController - Iniciando Metodo getById() - Id = {}", id );
		
		Optional<Person> personOptional = personRepository.findById(id);
		return personService.getPerson(personOptional);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<PersonDTO> getByCpf(@PathVariable String cpf){
		
		logger.info("PersonController - Iniciando Metodo getByCpf() - CPF = {}", cpf );
		
		Optional<Person> personOptional = personRepository.findByCpf(cpf);
		return personService.getPerson(personOptional);
	}
	
	@PostMapping
	public ResponseEntity<Person> post(@RequestBody Person person){
		
		logger.info("PersonController - Iniciando Metodo post() - Person = {}", person );
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(personRepository.save(person));
	}
	
	@PutMapping
	public ResponseEntity<Person> put(@RequestBody Person person) {
		
		logger.info("PersonController - Iniciando Metodo put() - Person = {}", person );
		
		return personRepository.findById(person.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(personRepository.save(person)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		logger.info("PersonController - Iniciando Metodo delete() - Id = {}", id );
		
		Optional<Person> person = personRepository.findById(id);
		
		if(person.isEmpty()) {
			logger.warn("Pessoa não encontrada!");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		personRepository.deleteById(id);
		logger.info("Deletado com sucesso!");
	}
	
}
