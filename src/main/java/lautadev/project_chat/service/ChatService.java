package lautadev.project_chat.service;

import lautadev.project_chat.dto.ChatDTO;
import lautadev.project_chat.model.Chat;
import lautadev.project_chat.repository.IChatRepository;
import lautadev.project_chat.throwable.EntityNotFoundException;
import lautadev.project_chat.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService {

    @Autowired
    private IChatRepository chatRepository;

    @Override
    public ChatDTO saveChat(Chat chat) {
        if(chat != null){
            chatRepository.save(chat);
        }

        return ChatDTO.fromChat(chat);
    }

    @Override
    public List<ChatDTO> getChats() {
        List<Chat> chats  = chatRepository.findAll();
        List<ChatDTO> chatDTOS = new ArrayList<>();

        for(Chat chat:chats){
            chatDTOS.add(ChatDTO.fromChat(chat));
        }

        return chatDTOS;
    }

    @Override
    public Optional<ChatDTO> findChat(Long id) {
        Chat chat = chatRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return Optional.ofNullable(ChatDTO.fromChat(chat));
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public ChatDTO editChat(Long id, Chat chat) {
        Chat chatEdit = chatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(chat,chatEdit);

        return this.saveChat(chatEdit);
    }
}
