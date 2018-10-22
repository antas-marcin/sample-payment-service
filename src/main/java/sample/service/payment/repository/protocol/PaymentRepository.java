package sample.service.payment.repository.protocol;

import java.util.List;
import java.util.Optional;
import sample.service.payment.model.Payment;

public interface PaymentRepository {

  List<Payment> findAll();

  Optional<Payment> find(String id);

  Optional<Payment> save(Payment payment);

  Optional<Payment> update(String id, Payment payment);

  Optional<Payment> delete(String id);

}
