package sample.service.payment;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import sample.service.payment.base.BaseTest;

public class ApplicationIntegrationTests extends BaseTest {

  @Test
  public void testFindOne() {
    testFindOne("/api/v1/payments/4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43");
  }

  @Test
  public void testFindOne_NotFound() {
    testFindOne_NotFound("/api/v1/payments/0");
  }

  @Test
  public void testFindMany() {
    testFindMany("/api/v1/payments");
  }

  @Test
  public void testDelete() {
    testDelete("/api/v1/payments/4ee3a8d8-ca7b-4290-a52c-dd5b6165ec43");
  }

  @Test
  public void testAccessHome() {
    Response response = RestAssured.given().when().get("/api/v1/payments");
    response.then().assertThat().statusCode(200);
    String body = response.getBody().print();
    Assert.assertTrue(body, body.contains("/api/v1/payments"));
  }
}
