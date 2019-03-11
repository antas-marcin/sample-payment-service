package sample.service.payment.resource;

import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.service.payment.model.Payment;
import sample.service.payment.service.PaymentService;

@Component
public class PaymentResourceRepository extends ResourceRepositoryBase<Payment, String> {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PaymentService paymentService;

  public PaymentResourceRepository() {
    super(Payment.class);
  }

  @Override
  public Class<Payment> getResourceClass() {
    return Payment.class;
  }

  @Override
  public Payment findOne(String id, QuerySpec querySpec) {
    LOG.info("GET /payments/{} invoked", id);
    return paymentService.getPayment(id);
  }

  @Override
  public ResourceList<Payment> findAll(QuerySpec querySpec) {
    LOG.info("GET /payments invoked");
    return querySpec.apply(paymentService.getPayments());
  }

  @Override
  public Payment create(Payment resource) {
    LOG.info("POST /payments invoked");
    return paymentService.savePayment(resource);
  }

  @Override
  public Payment save(Payment resource) {
    LOG.info("PATCH /payments/{} invoked", resource.getId());
    return paymentService.updatePayment(resource);
  }

  @Override
  public void delete(String id) {
    LOG.info("DELETE /payments/{} invoked", id);
    paymentService.deletePayment(id);
  }
}
