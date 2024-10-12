package lautadev.project_chat.service;

import lautadev.project_chat.dto.PersonDTO;
import lautadev.project_chat.model.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    public PersonDTO savePerson(Person person);
    public List<PersonDTO> getPeople();
    public Optional<PersonDTO> findPerson(Long id);
    public void deletePerson(Long id);
    public PersonDTO editPerson(Long id, Person person);
}
