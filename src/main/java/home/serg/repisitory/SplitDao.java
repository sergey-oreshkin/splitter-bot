package home.serg.repisitory;

import home.serg.entity.Split;
import home.serg.entity.UsersChat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SplitDao extends CrudRepository<Split, Integer> {
    List<Split> findAllByUsersChat(UsersChat usersChat);
    Optional<Split> findSplitByName(String name);
}
