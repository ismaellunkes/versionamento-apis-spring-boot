package br.com.lunkessw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lunkessw.data.vo.v1.PersonVO;
import br.com.lunkessw.data.vo.v2.PersonVOV2;
import br.com.lunkessw.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonServices personServices;
	
	@GetMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVOV2> findAllV2() {
		return personServices.findAllV2();
	}
	
	@PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 create(@RequestBody PersonVOV2 PersonVOV2) {
		return personServices.createV2(PersonVOV2);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findByid(@PathVariable(value = "id") Long id) {
		return personServices.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return personServices.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return personServices.create(PersonVO);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return personServices.update(PersonVO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}

}
