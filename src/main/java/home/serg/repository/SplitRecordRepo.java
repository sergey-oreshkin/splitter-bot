package home.serg.repository;

import home.serg.model.SplitRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRecordRepo extends CrudRepository<SplitRecord, Integer> {
}
