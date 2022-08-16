package br.com.lunkessw.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lunkessw.data.vo.v1.PersonVO;
import br.com.lunkessw.data.vo.v2.PersonVOV2;
import br.com.lunkessw.exceptions.ResourceNotFoundException;
import br.com.lunkessw.mapper.DozerMapper;
import br.com.lunkessw.mapper.custom.PersonMapper;
import br.com.lunkessw.model.Person;
import br.com.lunkessw.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	List<Person> persons = new ArrayList<>();

	public PersonVO findById(Long id) {
		logger.info("Finding by person!");
		var entity =  personRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);		
	}
	
	public List<PersonVOV2> findAllV2() {
		logger.info("Finding all people!");
		return mapper.convertEntitysToVos(personRepository.findAll());		
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating person: " + person.toString());
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating person: " + person.toString());
		var entity = mapper.convertVoToEntity(person);
		var vo =  mapper.convertEntityToVo(personRepository.save(entity));
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating person: " + person.toString());
		var entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		logger.info("deleting person.");
		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		personRepository.delete(entity);
	}

}
