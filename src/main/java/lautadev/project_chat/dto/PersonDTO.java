package lautadev.project_chat.dto;

import lautadev.project_chat.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    public static PersonDTO fromPerson(Person  person){
        if(person == null){
            return null;
        }

        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getLastname(),
                person.getEmail()
        );
    }
}
