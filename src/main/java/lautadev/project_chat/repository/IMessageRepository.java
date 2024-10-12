package lautadev.project_chat.repository;

import lautadev.project_chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository  extends JpaRepository<Message,Long> {
}
