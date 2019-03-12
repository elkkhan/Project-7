package tos.common.api.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tos.common.api.client.ApiClient;
import tos.common.api.exceptions.ConnectionException;
import tos.common.api.exceptions.QueryBuilderException;
import tos.common.api.query.ApiQuery;

class TestRecipeParser {

  private final ApiClient apiClient = new ApiClient();

  @ParameterizedTest
  @ValueSource(strings = {"chicken", "pork", "pizza", "donuts"})
  void notNull(String searchFor) {
    ApiQuery query;
    try {
      query = apiClient.createQuery(searchFor).build();
      assertNotNull(apiClient.executeQuery(query));
    } catch (QueryBuilderException | ConnectionException e) {
      e.printStackTrace();
    }
  }
}
