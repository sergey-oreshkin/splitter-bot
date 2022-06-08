package home.serg.repisitory;

import home.serg.entity.Chat;
import home.serg.entity.UsersChat;
import home.serg.entity.UsersChatId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsersChatDao extends CrudRepository<UsersChat, UsersChatId> {

    Set<UsersChat> findAllByChat(Chat chat);
}
