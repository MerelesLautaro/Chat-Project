package lautadev.project_chat.repository;

import lautadev.project_chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository  extends JpaRepository<Chat,Long> {
}
