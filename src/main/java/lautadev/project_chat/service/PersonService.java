package lautadev.project_chat.service;

import lautadev.project_chat.dto.PersonDTO;
import lautadev.project_chat.model.Person;
import lautadev.project_chat.repository.IPersonRepository;
import lautadev.project_chat.throwable.EntityNotFoundException;
import lautadev.project_chat.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public PersonDTO savePerson(Person person) {

        if(person != null) {
            personRepository.save(person);
        }

        return PersonDTO.fromPerson(person);
    }

    @Override
    public List<PersonDTO> getPeople() {
        List<Person> people = personRepository.findAll();
        List<PersonDTO> personDTOS = new ArrayList<>();

        for(Person person:people){
            personDTOS.add(PersonDTO.fromPerson(person));
        }

        return personDTOS;
    }

    @Override
    public Optional<PersonDTO> findPerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Entity Not Found")));
        return Optional.ofNullable(PersonDTO.fromPerson(person));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDTO editPerson(Long id, Person person) {
        Person personEdit = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not found"));

        NullAwareBeanUtils.copyNonNullProperties(person,personEdit);

        return this.savePerson(personEdit);
    }
}
