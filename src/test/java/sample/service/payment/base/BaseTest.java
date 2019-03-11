package sample.service.payment.base;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import io.crnk.client.CrnkClient;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import sample.service.payment.Application;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("ApplicationTests")
public abstract class BaseTest {

  @Value("${local.server.port}")
  protected int port;

  protected String jsonApiSchema;

  protected CrnkClient client;

  @Autowired

  private static String loadFile(String filename) throws Exception {
    InputStream inputStream = BaseTest.class.getClassLoader().getResourceAsStream(filename);
    return new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
  }

  @Before
  public final void before() {
    RestAssured.port = port;
    loadJsonApiSchema();

    client = new CrnkClient("http://localhost:" + port);
  }

  private void loadJsonApiSchema() {
    try {
      jsonApiSchema = loadFile("json-api-schema.json");
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  protected void testFindOne(String url) {
    ValidatableResponse response = RestAssured.given().contentType("application/json").when().get(url).then()
            .statusCode(OK.value());
    response.assertThat().body(matchesJsonSchema(jsonApiSchema));
  }

  protected void testFindOne_NotFound(String url) {
    RestAssured.given().contentType("application/json").when().get(url).then().statusCode(NOT_FOUND.value());
  }

  protected void testFindMany(String url) {
    ValidatableResponse response = RestAssured.given().contentType("application/json").when().get(url).then()
            .statusCode(OK.value());
    response.assertThat().body(matchesJsonSchema(jsonApiSchema));
  }

  protected void testDelete(String url) {
    RestAssured.given().contentType("application/json").when().delete(url).then().statusCode(NO_CONTENT.value());
  }
}
