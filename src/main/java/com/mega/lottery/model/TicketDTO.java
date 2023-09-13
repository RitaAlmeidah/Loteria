package com.mega.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TicketDTO {
	
	private Long numeroBilhete;
	
	private String status;
	
	@JsonIgnoreProperties("ticket")
	private PersonDTO person;

	public Long getNumeroBilhete() {
		return numeroBilhete;
	}

	public String getStatus() {
		return status;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setNumeroBilhete(Long numeroBilhete) {
		this.numeroBilhete = numeroBilhete;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

}
