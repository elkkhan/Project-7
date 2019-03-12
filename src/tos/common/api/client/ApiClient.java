/* TODO: 2019-02-26
 *   Parse the GET response into a Recipe object.
 *   Additional object like Ingredient and NutrientInfo also need to be parsed
 *   Take care of the case when the GET request returns null
 *   Test everything
 * */
package tos.common.api.client;

import tos.common.api.connection.ConnectionManager;
import tos.common.api.exceptions.ConnectionException;
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

  @Deprecated
//this is just for testing, actual executeQuery should parse the response into a Recipe object
  public String executeQuery(ApiQuery query) throws ConnectionException {
    return connectionManager.executeGetRequest(query.toString()).getResponseContent();
  }

  /*public Recipe executeQuery(ApiQuery query) throws ConnectionException {
    String queryString = query.toString();
    ConnectionResponse connectionResponse = connectionManager.executeGetRequest(queryString);
    String queryResponse = connectionResponse.getResponseContent();
    Recipe recipe = ApiRecipeParser.parse(queryResponse);
    return recipe;
  }*/
}
