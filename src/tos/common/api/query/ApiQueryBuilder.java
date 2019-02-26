/* TODO: 2019-02-24
 *   - DOCUMENTATION
 *   - Check all set options for validity in build()
 * */

package tos.common.api.client.query;

import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;
import tos.common.api.client.ApiClient;

public class ApiQueryBuilder {

    private final ApiQuery apiQuery;


    public ApiQueryBuilder(@NotNull String api_id, @NotNull String api_key,
        @NotNull String qSearch) {
        this.apiQuery = new ApiQuery(api_id, api_key, qSearch);
    }

    public ApiQueryBuilder setIngr(@NotNull String ingr) {
        this.apiQuery.INGR = ingr;
        return this;
    }

    public ApiQueryBuilder setDiet(@NotNull String dietParameter) {
        this.apiQuery.DIET = dietParameter;
        return this;
    }

    public ApiQueryBuilder addHealth(@NotNull String healthParameter) {
        this.apiQuery.HEALTH.add(healthParameter);
        return this;
    }

    public ApiQueryBuilder setCalories(String MIN, String MAX) {
        if (MAX == null) {
            this.apiQuery.CALORIES_FROM = MIN;
        } else if (MIN == null) {
            this.apiQuery.CALORIES_TO = MAX;
        } else {
            this.apiQuery.CALORIES_FROM = MIN;
            this.apiQuery.CALORIES_TO = MAX;
        }
        return this;
    }

    public ApiQueryBuilder setTime(String MIN, String MAX) {
        if (MAX == null) {
            this.apiQuery.TIME_FROM = MIN;
        } else if (MIN == null) {
            this.apiQuery.TIME_TO = MAX;
        } else {
            this.apiQuery.TIME_FROM = MIN;
            this.apiQuery.TIME_TO = MAX;
        }
        return this;
    }

    public ApiQueryBuilder addExculde(@NotNull String excluded) {
        this.apiQuery.EXCLUDED.add(excluded);
        return this;
    }


    public ApiQuery build() {
        try {
            URIBuilder builder = new URIBuilder(ApiClient.BASE_PATH);
            builder.addParameter("q", this.apiQuery.QUERY);
            builder.addParameter("app_id", this.apiQuery.APP_ID);
            builder.addParameter("app_key", this.apiQuery.APP_KEY);
            this.apiQuery.encodedQuery = builder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this.apiQuery;
    }
}
