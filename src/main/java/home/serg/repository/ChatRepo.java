package home.serg.repository;

import home.serg.model.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends CrudRepository<Chat, Integer> {
}
