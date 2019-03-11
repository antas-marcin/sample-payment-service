package sample.service.payment.service;

import io.crnk.core.exception.ForbiddenException;
import io.crnk.core.exception.ResourceNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.PaymentMongoRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

  @InjectMocks
  private PaymentService paymentService;

  @Mock
  private PaymentMongoRepository repository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldGetAllPayments() {
    //given
    List<Payment> givenPayments = Arrays.asList(getPayment("id1", 1), getPayment("id2", 2));
    Mockito.when(repository.findAll()).thenReturn(givenPayments);

    //when
    List<Payment> payments = paymentService.getPayments();

    //then
    assertThat(payments).isNotNull();
    assertThat(payments.size()).isEqualTo(2);
  }

  @Test
  public void shouldGetOnePayment() {
    //given
    String id = "some-id";
    Integer version = 11;
    Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.of(getPayment(id, version)));

    //when
    Payment payment = paymentService.getPayment(id);

    //then
    assertThat(payment).isNotNull();
    assertThat(payment.getId()).isEqualTo(id);
    assertThat(payment.getVersion()).isEqualTo(version);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void shouldNotGetOnePayment() {
    //given
    Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

    //when
    paymentService.getPayment("id");
  }

  @Test
  public void shouldSaveNewPayment() {
    //given
    Payment newPayment = getPayment(null, 0);
    Mockito.when(repository.save(Mockito.eq(newPayment))).thenReturn(getPayment("id", 0));

    //when
    Payment savedPayment = paymentService.savePayment(newPayment);

    //then
    Mockito.verify(repository).save(Mockito.eq(newPayment));
    assertThat(savedPayment).isNotNull();
  }

  @Test
  public void shouldUpdateExistingPayment() {
    //given
    String id = "id";
    Payment payment = getPayment(id, 0);
    Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.of(payment));
    Mockito.when(repository.save(Mockito.eq(payment))).thenReturn(getPayment(id, 1));

    //when
    Payment updatedPayment = paymentService.updatePayment(payment);

    //then
    Mockito.verify(repository).findById(Mockito.eq(id));
    Mockito.verify(repository).save(Mockito.eq(payment));
    assertThat(updatedPayment).isNotNull();
    assertThat(updatedPayment.getId()).isEqualTo(id);
    assertThat(updatedPayment.getVersion()).isEqualTo(1);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void shouldNotUpdateNotExistingPayment() {
    //given
    String id = "id";
    Payment payment = getPayment(id, 0);
    Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.empty());

    //when
    paymentService.updatePayment(payment);
  }

  @Test(expected = ForbiddenException.class)
  public void shouldNotSavePaymentWithClientGeneratedId() {
    //given
    Payment newPayment = getPayment("client-generated0id", 0);

    //when
    paymentService.savePayment(newPayment);
  }

  @Test
  public void shouldDeleteExistingPayment() {
    //given
    String id = "some-id";
    Payment payment = getPayment(id, 0);
    Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.of(payment));

    //when
    paymentService.deletePayment(id);

    //then
    Mockito.verify(repository).findById(Mockito.eq(id));
    Mockito.verify(repository).deleteById(Mockito.eq(id));
  }

  @Test(expected = ResourceNotFoundException.class)
  public void shouldThrowErrorWhenDeletingNotExistingPayment() {
    //given
    String id = "some-id";
    Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.empty());

    //when
    paymentService.deletePayment(id);
  }

  private Payment getPayment(String id, Integer version) {
    Payment p = new Payment();
    p.setId(id);
    p.setVersion(version);
    return p;
  }
}
