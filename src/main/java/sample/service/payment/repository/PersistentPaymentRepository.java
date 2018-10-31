package sample.service.payment.repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.protocol.PaymentRepository;

@Repository
@Primary
public class PersistentPaymentRepository implements PaymentRepository {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private MongoDbPaymentRepository repository;

  @Override
  public List<Payment> findAll() {
    LOG.info("[findAll] Fetching data from MongoDB");
    return repository.findAll();
  }

  @Override
  public Optional<Payment> find(String id) {
    LOG.info("[find] Finding data with id: %s in MongoDB", id);
    return repository.findById(id);
  }

  @Override
  public Optional<Payment> save(Payment payment) {
    LOG.info("[save] Saving payment data with id: %s in MongoDB", payment.getId());
    Payment p = repository.save(payment);
    return Optional.ofNullable(p);
  }

  @Override
  public Optional<Payment> update(String id, Payment payment) {
    LOG.info("[update] Updating payment data with id: %s in MongoDB", id);
    repository.deleteById(id);
    Payment p = repository.save(payment);
    return Optional.ofNullable(p);
  }

  @Override
  public Optional<Payment> delete(String id) {
    LOG.info("[delete] Deleting payment data with id: %s in MongoDB", id);
    repository.deleteById(id);
    return Optional.empty();
  }
}
