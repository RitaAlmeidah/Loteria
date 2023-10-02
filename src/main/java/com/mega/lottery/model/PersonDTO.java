package com.mega.lottery.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PersonDTO {
	
	private String nome;
	
	private String email;
	
	@JsonIgnoreProperties("person")
	private List<TicketDTO> ticket;

	public PersonDTO(String nome, String email, List<TicketDTO> ticket) {
		this.nome = nome;
		this.email = email;
		this.ticket = ticket;
	}

	
	public PersonDTO() {
	}

	@Override
	public String toString() {
		return "PersonDTO{" + "nome='" + nome + '\'' + ", email='" + email + '\'' + ", ticket=" + ticket + '}';
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<TicketDTO> getTicket() {
		return ticket;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTicket(List<TicketDTO> ticket) {
		this.ticket = ticket;
	}

	
}
