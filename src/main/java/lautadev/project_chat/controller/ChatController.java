package lautadev.project_chat.controller;

import lautadev.project_chat.dto.ChatDTO;
import lautadev.project_chat.model.Chat;
import lautadev.project_chat.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IChatService chatService;

    @PostMapping("/save")
    public ResponseEntity<ChatDTO> saveChat(@RequestBody Chat chat){
        return ResponseEntity.ok(chatService.saveChat(chat));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ChatDTO>> getChats(){
        return ResponseEntity.ok(chatService.getChats());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ChatDTO> findChat(@PathVariable Long id){
        Optional<ChatDTO> chat = chatService.findChat(id);
        return chat.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChat(@PathVariable Long id){
        chatService.deleteChat(id);
        return ResponseEntity.ok("Chat deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ChatDTO> editChat(@PathVariable Long id, @RequestBody Chat chat){
        return ResponseEntity.ok(chatService.editChat(id,chat));
    }
}
