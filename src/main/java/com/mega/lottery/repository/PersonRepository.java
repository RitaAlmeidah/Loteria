package com.mega.lottery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mega.lottery.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	public List <Person> findAllByCpf(@Param("cpf") String cpf);
	
}
