package home.serg.repository;

import home.serg.model.Split;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRepo extends CrudRepository<Split, Integer> {
}
