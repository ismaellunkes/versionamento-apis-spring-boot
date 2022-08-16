package br.com.lunkessw.mapper.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lunkessw.data.vo.v2.PersonVOV2;
import br.com.lunkessw.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {

		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		vo.setBirthDay(new Date());
		return vo;

	}

	public Person convertVoToEntity(PersonVOV2 vo) {

		Person person = new Person();
		person.setId(vo.getId());
		person.setFirstName(vo.getFirstName());
		person.setLastName(vo.getLastName());
		person.setAddress(vo.getAddress());
		person.setGender(vo.getGender());
		// person.setBirthDay(new Date());
		return person;

	}

	public List<PersonVOV2> convertEntitysToVos(List<Person> persons) {

		List<PersonVOV2> listOfPersonsVOV2 = new ArrayList<PersonVOV2>();

		for (Person person : persons) {
			PersonVOV2 vo = new PersonVOV2();
			vo.setId(person.getId());
			vo.setFirstName(person.getFirstName());
			vo.setLastName(person.getLastName());
			vo.setAddress(person.getAddress());
			vo.setGender(person.getGender());
			vo.setBirthDay(new Date());
			listOfPersonsVOV2.add(vo);
		}

		return listOfPersonsVOV2;
	}

}
