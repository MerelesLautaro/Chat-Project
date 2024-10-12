package lautadev.project_chat.service;

import lautadev.project_chat.dto.MessageDTO;
import lautadev.project_chat.model.Message;

import java.util.List;
import java.util.Optional;

public interface IMessageService {
    public MessageDTO saveMessage(Message message);
    public List<MessageDTO> getMessages();
    public Optional<MessageDTO> findMessage(Long id);
    public void deleteMessage(Long id);
    public MessageDTO editMessage(Long id,Message message);

}
