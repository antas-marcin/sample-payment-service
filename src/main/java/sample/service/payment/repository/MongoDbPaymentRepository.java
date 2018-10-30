package sample.service.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sample.service.payment.model.Payment;

interface MongoDbPaymentRepository extends MongoRepository<Payment, String> {

}
