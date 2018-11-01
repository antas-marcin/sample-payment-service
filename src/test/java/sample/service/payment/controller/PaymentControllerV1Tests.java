package sample.service.payment.controller;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sample.service.payment.exception.PaymentNotFoundException;
import sample.service.payment.exception.PaymentNotSavedException;
import sample.service.payment.model.Data;
import sample.service.payment.model.NewData;
import sample.service.payment.service.PaymentService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerV1Tests {

  private static final String PAYMENTS_URL = "/api/v1/payments";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PaymentService paymentService;

  private Gson gson = new Gson();

  @Test
  public void getPaymentShouldReturnAllPayments() throws Exception {

    List<Data> mockedData = getMockedData(getMockedPayment("id", "orgId"), getMockedPayment("id2", "orgId2"), getMockedPayment("id3", "orgId2"));
    given(this.paymentService.getPayments()).willReturn(mockedData);


    this.mockMvc
            .perform(get(PAYMENTS_URL))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(gson.toJson(mockedData)));
  }

  @Test
  public void getPaymentWithIdShouldReturnPayment() throws Exception {

    Data mockedPayment = getMockedPayment("id", "orgId");
    given(this.paymentService.getPayment("id")).willReturn(getMockedPayment("id", "orig"));

    this.mockMvc
            .perform(get(PAYMENTS_URL + "/id"))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().json(gson.toJson(mockedPayment)));
  }

  @Test
  public void postPaymentWithPaymentShouldReturnNotFoundWhenPaymentWasNotSaved() throws Exception {

    Data mockedPayment = getMockedPayment("id", "orgId");
    given(this.paymentService.savePayment(Mockito.any(NewData.class))).willThrow(new PaymentNotSavedException("id"));

    this.mockMvc
            .perform(post(PAYMENTS_URL).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(mockedPayment)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(content().string(""));
  }

  @Test
  public void putPaymentWithIdShouldReturnDeletedPayment() throws Exception {

    Data mockedPayment = getMockedPayment("some-id", "orgId");
    given(this.paymentService.updatePayment(Mockito.anyString(), Mockito.any(Data.class))).willReturn(mockedPayment);

    this.mockMvc
            .perform(put(PAYMENTS_URL + "/some-id").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(mockedPayment)))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().json(gson.toJson(mockedPayment)));
  }

  @Test
  public void putPaymentWithIdShouldReturnNotFoundWhenPaymentCouldntBeUpdatedInDB() throws Exception {

    Data mockedPayment = getMockedPayment("some-id", "orgId");
    given(this.paymentService.updatePayment(Mockito.anyString(), Mockito.any(Data.class))).willThrow(new PaymentNotFoundException("some-id"));

    this.mockMvc
            .perform(put("/api/v1/payment/some-id").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(mockedPayment)))
            .andDo(print()).andExpect(status().is4xxClientError())
            .andExpect(content().string(""));
  }

  @Test
  public void deletePaymentWithIdShouldReturnDeletedPayment() throws Exception {

    Data mockedPayment = getMockedPayment("id", "orgId");
    Mockito.doNothing().when(this.paymentService).deletePayment(Mockito.eq("id"));

    this.mockMvc
            .perform(delete(PAYMENTS_URL + "/id"))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(""));
  }

  @Test
  public void deletePaymentWithIdShouldReturnNotFoundWhenNoPaymentWasFoundInDB() throws Exception {

    Mockito.doThrow(new PaymentNotFoundException("id")).when(this.paymentService).deletePayment(Mockito.anyString());

    this.mockMvc
            .perform(delete(PAYMENTS_URL + "/id"))
            .andDo(print()).andExpect(status().is4xxClientError())
            .andExpect(content().string(""));
  }

  private List<Data> getMockedData(Data ...payment) {
    return Arrays.asList(payment);
  }

  private Data getMockedPayment(String id, String organisationId) {
    return new Data("id", Data.DataType.Payment, 0, "organisationdId", null);
  }
}
