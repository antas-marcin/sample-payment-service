package sample.service.payment.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import sample.service.payment.model.Payment;
import sample.service.payment.repository.PaymentMongoRepository;

@Profile("ApplicationTests")
@Configuration
public class TestDataLoader {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Getter
  @Setter
  static class Data {

    private List<Payment> data;
    private Links links;
  }

  @Getter
  @Setter
  static class Links {

    private String self;
  }

  @Autowired
  private PaymentMongoRepository repository;

  @PostConstruct
  public void loadSampleData() {
    try {
      LOG.info("Populating DB with sample data");
      ClassPathResource resource = new ClassPathResource("/sample-payments.json");
      String content = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);
      ObjectMapper mapper = new ObjectMapper();
      mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

      Data data = mapper.readValue(content, Data.class);

      repository.deleteAll();
      data.getData().forEach(repository::save);
      LOG.info("Parsed sample-payments.json, DB populated with {} sample payments", data.getData().size());
    } catch (IOException e) {
      LOG.error("Something went wrong during parsing sample-payments.json file", e);
    }
  }

}
