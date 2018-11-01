package sample.service.payment.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import sample.service.payment.model.Data;

public interface DataMongoRepository extends MongoRepository<Data, String> {

  List<Data> findByType(Data.DataType type);

}
