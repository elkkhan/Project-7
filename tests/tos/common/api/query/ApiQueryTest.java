package tos.common.api.query;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import tos.common.api.client.ApiClient;

public class ApiQueryTest {

    private final ApiClient apiClient = new ApiClient();

    @Test
    public void simpleQueryBuildTest() {
        try {
            String search = "chicken";
            ApiQuery chickenQuery = apiClient.createQuery(search).setFrom("5")
                .setCalories("500", null).build();
            String response = apiClient.executeQuery(chickenQuery);
            assertFalse(response.toLowerCase().contains("error"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
