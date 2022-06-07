package home.serg.repisitory;

import home.serg.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatDao extends CrudRepository<Chat, Integer> {
}
