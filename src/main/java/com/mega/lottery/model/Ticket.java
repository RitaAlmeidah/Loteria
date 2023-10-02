package com.mega.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tb_ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Digits(integer = 10, fraction = 0, message = "O número do bilhete deve ter exatamente 10 dígitos.")
	private Long numeroBilhete;
	
	@NotBlank(message = "O atributo status e obrigatorio!")
	private String status;
	
	@ManyToOne
	@JsonIgnoreProperties("ticket")
	private Person person;

	@Override
	public String toString() {
		return "Ticket{" + "id=" + id + ", numeroBilhete=" + numeroBilhete + ", status='" + status + '\'';
	}

	public Ticket(Long id,
			@Digits(integer = 10, fraction = 0, message = "O número do bilhete deve ter exatamente 10 dígitos.") Long numeroBilhete,
			@NotBlank(message = "O atributo status e obrigatorio!") String status, Person person) {
		this.id = id;
		this.numeroBilhete = numeroBilhete;
		this.status = status;
		this.person = person;
	}

	public Ticket() {
	}

	public Long getId() {
		return id;
	}

	public Long getNumeroBilhete() {
		return numeroBilhete;
	}

	public String getStatus() {
		return status;
	}

	public Person getPerson() {
		return person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumeroBilhete(Long numeroBilhete) {
		this.numeroBilhete = numeroBilhete;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
	
	