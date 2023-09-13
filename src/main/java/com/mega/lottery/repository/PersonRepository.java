package com.mega.lottery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mega.lottery.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	public Optional<Person> findByCpf(@Param("cpf") String cpf);
	
}
