/* TODO: 2019-03-10
 *   Test everything
 *   Document everything
 * */
package tos.common.api.client;

import java.util.List;
import tos.common.api.connection.ConnectionManager;
import tos.common.api.connection.ConnectionResponse;
import tos.common.api.entities.Recipe;
import tos.common.api.exceptions.ConnectionException;
import tos.common.api.parser.ApiRecipeParser;
import tos.common.api.query.ApiQuery;
import tos.common.api.query.ApiQuery.Builder;

public class ApiClient {

  private static final String BASE_PATH = "https://api.edamam.com/search?";
  private static final String APPLICATION_ID = "a975f036";
  private static final String APPLICATION_KEYS = "ec3265c79b41917d82e0379765cdb5ca";
  private static final ConnectionManager connectionManager = new ConnectionManager();

  /**
   * @return base path for the API requests
   */
  public static String getBasePath() {
    return BASE_PATH;
  }

  /**
   * Initializes a {@link Builder} with app_id, app_keys and search query for "q" parameter
   *
   * @param searchQuery Search text, value for "q" parameter
   * @return a constructed {@link Builder}
   */
  public Builder createQuery(String searchQuery) {
    return new Builder(APPLICATION_ID, APPLICATION_KEYS, searchQuery);
  }

  /**
   * Executes the given {@link ApiQuery} query against the API Parses the received JSON response
   * into a {@link Recipe} object and returns it
   *
   * @param query {@link ApiQuery} to execute
   * @return parsed {@link Recipe} object
   * @throws ConnectionException if the connection to the API was not successful
   */
  public List<Recipe> executeQuery(ApiQuery query) throws ConnectionException {
    String queryString = query.toString();
    ConnectionResponse connectionResponse = connectionManager.executeGetRequest(queryString);
    String queryResponse = connectionResponse.getResponseContent();
    return ApiRecipeParser.parse(queryResponse);
  }
}