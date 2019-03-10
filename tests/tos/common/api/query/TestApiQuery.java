package tos.common.api.query;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import tos.common.api.client.ApiClient;

class TestApiQuery {

  private final ApiClient apiClient = new ApiClient();

  @Test
  void constructQueryTest() {
    try {
      ApiQuery.Builder apiQueryBuilder = apiClient.createQuery("chicken");
      ApiQuery apiQuery = apiQueryBuilder.build();
      String response = apiClient.executeQuery(apiQuery);
      JsonElement element = new JsonParser().parse(response);
      assertFalse(apiQuery.toString().isEmpty());
      assertFalse(response.isEmpty());
      assertNotNull(element);
    } catch (Exception e) {
      fail();
    }
  }

}
