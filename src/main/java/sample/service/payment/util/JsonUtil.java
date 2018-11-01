package sample.service.payment.util;

import com.google.gson.Gson;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import sample.service.payment.model.Data;

@Component
public class JsonUtil {

  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  public List<Data> parseSampleJson() {
    LOG.info("[parseSampleJson] Trying to parse sample json");
    try {
      File file = ResourceUtils.getFile("classpath:payments.json");
      String content = new String(Files.readAllBytes(file.toPath()));
      DataResource data = new Gson().fromJson(content, DataResource.class);
      LOG.info("[parseSampleJson] Successfully parsed sample json (payments.size: {})", data);
      return data.getData();
    } catch (Exception e) {
      LOG.error("[parseSampleJson] Something went wrong during parsing sample json, error: {}", e);
      return null;
    }
  }
}
