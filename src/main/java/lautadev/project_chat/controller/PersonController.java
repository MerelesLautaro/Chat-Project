package lautadev.project_chat.controller;

import lautadev.project_chat.dto.PersonDTO;
import lautadev.project_chat.model.Person;
import lautadev.project_chat.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @PostMapping("/save")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody Person person){
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @GetMapping("/get")
    public ResponseEntity<List<PersonDTO>> getPeople(){
        return ResponseEntity.ok(personService.getPeople());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PersonDTO> findPerson(@PathVariable Long id){
        Optional<PersonDTO> person = personService.findPerson(id);
        return person.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<PersonDTO> editPerson(@PathVariable Long id, @RequestBody Person person){
        return ResponseEntity.ok(personService.editPerson(id,person));
    }
}
