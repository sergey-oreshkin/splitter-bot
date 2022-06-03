package home.serg.repository;

import home.serg.model.UserChat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepo extends CrudRepository<UserChat, Integer> {
}
