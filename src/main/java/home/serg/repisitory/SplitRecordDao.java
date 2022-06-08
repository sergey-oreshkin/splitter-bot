package home.serg.repisitory;

import home.serg.entity.SplitRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRecordDao extends CrudRepository<SplitRecord, Integer> {
}
