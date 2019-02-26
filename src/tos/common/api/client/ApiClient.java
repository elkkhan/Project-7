package tos.common.api.client;

import tos.common.api.client.query.ApiQuery;
import tos.common.api.connection.ConnectionManager;
import tos.common.api.exceptions.ConnectionException;

public class InternalApiClient {

    private static ConnectionManager connectionManager;
    private final String APPLICATION_ID;
    private final String APPLICATION_KEYS;
    public static final String BASE_PATH =  "https://api.edamam.com/search?";

    private InternalApiClient(String APPLICATION_ID, String APPLICATION_KEYS) {
        this.APPLICATION_ID = APPLICATION_ID;
        this.APPLICATION_KEYS = APPLICATION_KEYS;
        connectionManager = new ConnectionManager();
    }

    public static InternalApiClient create(String APPLICATION_ID, String APPLICATION_KEYS) {
        return new InternalApiClient(APPLICATION_ID, APPLICATION_KEYS);
    }

    @Deprecated public String makeSimpleQuery(ApiQuery query) throws ConnectionException {
        return connectionManager.executeGetRequest(query.toString()).getResponseContent();
    }

}
