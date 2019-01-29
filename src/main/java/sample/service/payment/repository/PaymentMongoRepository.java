package sample.service.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sample.service.payment.model.Payment;

@Repository
public interface PaymentMongoRepository extends MongoRepository<Payment, String> {

}
