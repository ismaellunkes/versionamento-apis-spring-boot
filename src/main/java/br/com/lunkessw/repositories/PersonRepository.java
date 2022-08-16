package br.com.lunkessw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lunkessw.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{ }
