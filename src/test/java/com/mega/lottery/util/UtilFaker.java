package com.mega.lottery.util;
import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;
import com.mega.lottery.model.Person;
import com.mega.lottery.model.PersonDTO;
import com.mega.lottery.model.Ticket;
import com.mega.lottery.model.TicketDTO;
import com.mega.lottery.service.SorteioService;


public class UtilFaker {
	
	SorteioService sorteioService = new SorteioService();
	
	public PersonDTO fakerPersonDTO(){
		Faker faker = new Faker();
		
		List<TicketDTO> listTicketDTO = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    listTicketDTO.add(gerarTicketDTO());
		}
		
		PersonDTO personDTO = new PersonDTO(faker.name().fullName(), faker.name().fullName(), listTicketDTO);
		return personDTO;
	}
	
	public Person fakerPerson(){
		Faker faker = new Faker();
		
		List<Ticket> listTicket = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    listTicket.add(gerarTicket(faker));
		}
		
		Person person = gerarPerson();
		person.setId((long) faker.number().numberBetween(1, 10000));
		person.setTicket(listTicket);
		return person;
	}
	
	public Person fakerPersonPost() {
		Person person = gerarPerson();
		return person;
	}
	
	public Person fakerPersonPut() {
		Person person = gerarPerson();
		person.setId(1L);
		return person;
	}
	
	public Person gerarPerson() {
		Faker faker = new Faker();
		List<Ticket> listTicket = new ArrayList<>();
		Person person = new Person(null, faker.name().fullName(), generateRandomSequence(11), faker.internet().emailAddress(), listTicket );
		return person;
	}
	
	
	

	public TicketDTO fakerTicketDTO() {
		Faker faker = new Faker();

		PersonDTO personDTO = new PersonDTO(faker.name().fullName(), faker.internet().emailAddress(), null);
		TicketDTO ticketDTO = new TicketDTO(sorteioService.sortearSequencia(), faker.lorem().word(), personDTO);
		return ticketDTO;
	}

	public Ticket fakerTicket() {
		Faker faker = new Faker();
		
		Person person = gerarPerson();
		person.setId((long) faker.number().numberBetween(1, 10000));
		person.setTicket(null);

		Ticket ticket = new Ticket((long) faker.number().numberBetween(1, 10000), sorteioService.sortearSequencia(), faker.lorem().word(), person);
		return ticket;
	}
	
	public List<Ticket> fakerTicketPost() {
		Faker faker = new Faker();
		List<Ticket> listTicket = new ArrayList<>();
		
		Person person = new Person(1L, faker.name().fullName(), generateRandomSequence(11), faker.internet().emailAddress() , null);
		
		for (int i = 0; i < 5; i++) {
			Ticket ticket = new Ticket(null, sorteioService.sortearSequencia(), faker.lorem().word(), person);
		    listTicket.add(ticket);
		}
		return listTicket;
	}
	
	public Ticket fakerTicketPut() {
		Faker faker = new Faker();
		
		Person person = new Person(1L, null, null, null, null);
		Ticket ticket = new Ticket(1L, 1234567891L, faker.lorem().word(), person);
		return ticket;
	}
	
	public TicketDTO gerarTicketDTO() {
		Faker faker = new Faker();
		TicketDTO ticketDTO = new TicketDTO(sorteioService.sortearSequencia(), faker.lorem().word(), null);
		return ticketDTO;
	}
	
	public Ticket gerarTicket(Faker faker) {
		Ticket ticket = new Ticket((long) faker.number().numberBetween(1, 10000), sorteioService.sortearSequencia(), faker.lorem().word(), null);
		return ticket;
	}
	
	
	
	
	
	
	public static String generateRandomSequence(int length) {

		Faker faker = new Faker();
		StringBuilder sequence = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int digit = faker.number().numberBetween(0, 10); // Gera um número aleatório entre 0 e 9
			sequence.append(digit);
		}
		return sequence.toString();
	}
}