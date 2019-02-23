package tos.common.api.client.query;

import org.jetbrains.annotations.NotNull;

public class ApiQuery {

    private final String QUERY;
    private final String APP_ID;
    private final String APP_KEY;
    Integer FROM;
    Integer TO;
    Integer INGR;
    String DIET;
    String HEALTH;
    String CALORIES;
    String TIME;
    String EXCLUDED;

    private ApiQuery(@NotNull String api_id, @NotNull String api_key, @NotNull String qSearch) {
        this.APP_ID = api_id;
        this.APP_KEY = api_key;
        this.QUERY = qSearch;
    }

    public static ApiQueryBuilder builder(@NotNull String api_id, @NotNull String api_key,
        @NotNull String qSearch) {
        return new ApiQueryBuilder(new ApiQuery(api_id, api_key, qSearch));
    }


}
