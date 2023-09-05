package com.mega.lottery.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo cpf é obrigatório!")
	@Size (min = 3, message = "O atributo nome deve conter no mínimo 03 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo cpf é obrigatório!")
	@Size(min = 11, max = 11, message = "O atributo cpf deve ser igual a 11 caracteres")
	private Integer cpf;
	
	@NotBlank(message = "O atributo email é obrigatório!")
	private String email;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("person")
	private List<Ticket> ticket;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Integer getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}


