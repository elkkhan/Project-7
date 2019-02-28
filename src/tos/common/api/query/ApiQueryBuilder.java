package tos.common.api.query;

import java.net.URISyntaxException;
import java.util.Collection;
import org.apache.commons.collections4.MultiMap;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;
import tos.common.api.client.ApiClient;
import tos.common.api.exceptions.QueryBuilderException;

public class ApiQueryBuilder {

    private final ApiQuery apiQuery;

    public ApiQueryBuilder(@NotNull String api_id, @NotNull String api_key,
        @NotNull String qSearch) {
        this.apiQuery = new ApiQuery(api_id, api_key, qSearch);
    }


    public ApiQueryBuilder setFrom(@NotNull String from) {
        this.apiQuery.FROM = from;
        return this;
    }

    public ApiQueryBuilder setTo(@NotNull String to) {
        this.apiQuery.TO = to;
        return this;
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

    public ApiQueryBuilder setCalories(String min, String max) {
        if (min != null && max != null) {
            this.apiQuery.CALORIES = min + "-" + max;
        } else if (min == null && max != null) {
            this.apiQuery.CALORIES = max;
        } else {
            this.apiQuery.CALORIES = min + "+";
        }
        return this;
    }

    public ApiQueryBuilder setTime(String min, String max) {
        if (min != null && max != null) {
            this.apiQuery.TIME = min + "-" + max;
        } else if (min == null && max != null) {
            this.apiQuery.TIME = max;
        } else {
            this.apiQuery.TIME = min + "+";
        }
        return this;
    }

    public ApiQueryBuilder addExculde(@NotNull String excluded) {
        this.apiQuery.EXCLUDED.add(excluded);
        return this;
    }

    public ApiQuery build() throws QueryBuilderException {
        try {
            URIBuilder builder = new URIBuilder(ApiClient.BASE_PATH);
            builder.addParameter("q", this.apiQuery.QUERY);
            builder.addParameter("app_id", this.apiQuery.APP_ID);
            builder.addParameter("app_key", this.apiQuery.APP_KEY);
            MultiMap<String, String> parameterValueMapping = this.apiQuery.mapParameters();
            for (String parameterName : parameterValueMapping.keySet()) {
                Collection parameterValues = (Collection) parameterValueMapping.get(parameterName);
                for (Object parameterValue : parameterValues) {
                    String _parameterValue = (String) parameterValue;
                    if (parameterValue != null) {
                        builder.addParameter(parameterName, _parameterValue);
                    }
                }
            }
            this.apiQuery.encodedQuery = builder.build().toString();
        } catch (URISyntaxException e) {
            throw new QueryBuilderException(e.getMessage());
        }
        return this.apiQuery;
    }
}
