package home.serg.repisitory;

import home.serg.entity.Split;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitDao extends CrudRepository<Split, Integer> {
}
