package lautadev.project_chat.service;

import lautadev.project_chat.dto.ChatDTO;
import lautadev.project_chat.model.Chat;

import java.util.List;
import java.util.Optional;

public interface IChatService {
    public ChatDTO saveChat(Chat chat);
    public List<ChatDTO> getChats();
    public Optional<ChatDTO> findChat(Long id);
    public void deleteChat(Long id);
    public ChatDTO editChat(Long id, Chat chat);
}
