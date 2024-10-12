package lautadev.project_chat.dto;

import lautadev.project_chat.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String content;
    private PersonDTO sender;
    private LocalDateTime timestamp;
    private Long idChat;

    public static MessageDTO fromMessage(Message message){
        if(message == null){
            return null;
        }

        return new MessageDTO(
                message.getId(),
                message.getContent(),
                PersonDTO.fromPerson(message.getSender()),
                message.getTimestamp(),
                message.getChat().getId()
        );
    }
}
