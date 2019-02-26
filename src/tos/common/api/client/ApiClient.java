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
import tos.common.api.query.ApiQueryBuilder;

public class ApiClient {

    public static final String BASE_PATH = "https://api.edamam.com/search?";
    private static ConnectionManager connectionManager;
    private final static String APPLICATION_ID = "a975f036";
    private final static String APPLICATION_KEYS = "ec3265c79b41917d82e0379765cdb5ca";

    public ApiClient() {
        connectionManager = new ConnectionManager();
    }

    /**
     * @return base path for the API requests
     */
    public static String getBasePath() {
        return BASE_PATH;
    }

    /**
     * Initializes an ApiQueryBuilder with app_id, app_keys and search query for "q" parameter
     *
     * @param searchQuery Search text, value for "q" parameter
     * @return a constructed ApiQueryBuilder
     */
    public ApiQueryBuilder createQuery(String searchQuery) {
        return new ApiQueryBuilder(APPLICATION_ID, APPLICATION_KEYS, searchQuery);
    }

    @Deprecated
//this is just for testing, actual executeQuery should parse the response into a Recipe object
    public String executeQuery(ApiQuery query) throws ConnectionException {
        return connectionManager.executeGetRequest(query.toString()).getResponseContent();
    }
}
