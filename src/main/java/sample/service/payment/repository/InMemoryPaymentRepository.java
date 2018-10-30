package sample.service.payment.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.protocol.PaymentRepository;
import sample.service.payment.util.JsonUtil;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JsonUtil jsonUtil;

  @Value("${load.sample.data}")
  private Boolean loadSampleData;

  private Map<String, Payment> payments;

  public List<Payment> findAll() {
    return new ArrayList<>(payments.values());
  }

  public Optional<Payment> find(String id) {
    return Optional.ofNullable(payments.get(id));
  }

  public Optional<Payment> save(Payment p) {
    payments.put(p.getId(), p);
    return Optional.of(p);
  }

  public Optional<Payment> update(String id, Payment p) {
    if (payments.containsKey(id)) {
      payments.put(p.getId(), p);
      return Optional.of(p);
    } else {
      return Optional.empty();
    }
  }

  public Optional<Payment> delete(String id) {
    return Optional.ofNullable(payments.remove(id));
  }

  @PostConstruct
  private void initializeData() {
    if (loadSampleData != null && loadSampleData) {
      LOG.info("Parsing sample data, storing data in memory");
      payments = jsonUtil.parseSampleJson().getData().stream().collect(Collectors.toMap(Payment::getId, p -> p));
    } else {
      payments = new HashMap<>();
    }
  }
}
