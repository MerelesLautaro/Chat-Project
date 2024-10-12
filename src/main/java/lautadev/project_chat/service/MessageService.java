package lautadev.project_chat.service;

import lautadev.project_chat.dto.MessageDTO;
import lautadev.project_chat.model.Message;
import lautadev.project_chat.model.Person;
import lautadev.project_chat.repository.IMessageRepository;
import lautadev.project_chat.repository.IPersonRepository;
import lautadev.project_chat.throwable.EntityNotFoundException;
import lautadev.project_chat.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository  messageRepository;

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public MessageDTO saveMessage(Message message) {
        if (message != null) {
            if (message.getSender() != null && message.getSender().getId() != null) {
                Person sender = personRepository.findById(message.getSender().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Sender not found"));
                message.setSender(sender);
            }
            messageRepository.save(message);
        }

        return MessageDTO.fromMessage(message);
    }

    @Override
    public List<MessageDTO> getMessages() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for(Message message: messages){
            messageDTOS.add(MessageDTO.fromMessage(message));
        }

        return messageDTOS;
    }

    @Override
    public Optional<MessageDTO> findMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(("Entity Not Found")));
        return Optional.ofNullable(MessageDTO.fromMessage(message));
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public MessageDTO editMessage(Long id, Message message) {
        Message messageEdit = messageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(message,messageEdit);

        return this.saveMessage(messageEdit);
    }
}
