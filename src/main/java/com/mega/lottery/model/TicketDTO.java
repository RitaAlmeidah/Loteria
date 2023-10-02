package com.mega.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TicketDTO {
	
	private Long numeroBilhete;
	
	private String status;
	
	@JsonIgnoreProperties("ticket")
	private PersonDTO person;

	@Override
	public String toString() {
		return "TicketDTO{" + "numeroBilhete=" + numeroBilhete + ", status='" + status + '\'' + ", person=" + person
				+ '}';
	}

	public TicketDTO(Long numeroBilhete, String status, PersonDTO person) {
		this.numeroBilhete = numeroBilhete;
		this.status = status;
		this.person = person;
	}

	public TicketDTO() {
	}

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
