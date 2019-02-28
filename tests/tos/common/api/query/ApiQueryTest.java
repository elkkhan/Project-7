package tos.common.api.query;

import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import tos.common.api.client.ApiClient;

class ApiQueryTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    @SuppressWarnings("unused")
    void simpleQueryBuildTest() {
        try {
            String search = "chicken wings";
            ApiQuery chickenQuery = apiClient.createQuery(search).setFrom("5")
                .setCalories("500", null).build();
            String response = apiClient.executeQuery(chickenQuery);
            JSONObject responseJson = new JSONObject(response);
        } catch (Exception e) {
            fail("Not a JSON response");
        }
    }
}
