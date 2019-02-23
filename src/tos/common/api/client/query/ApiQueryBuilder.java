/* TODO: 2019-02-24
 *   - DOCUMENTATION
 *   - Check all set options for validity in build()
 * */

package tos.common.api.client.query;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tos.common.api.exceptions.QueryBuilderException;

public class ApiQueryBuilder {

    private final ApiQuery apiQuery;

    ApiQueryBuilder(ApiQuery apiQuery) {
        this.apiQuery = apiQuery;
    }

    public ApiQueryBuilder setIngr(@NotNull Integer ingr) {
        this.apiQuery.INGR = ingr;
        return this;
    }

    public ApiQueryBuilder setDiet(@NotNull String dietParameter) {
        this.apiQuery.DIET = dietParameter;
        return this;
    }

    public ApiQueryBuilder setHealth(@NotNull String healthParameter) {
        this.apiQuery.HEALTH = healthParameter;
        return this;
    }

    public ApiQueryBuilder setCalories(@Nullable Integer MIN, @Nullable Integer MAX)
        throws QueryBuilderException {
        if (MIN == null && MAX == null) {
            throw new QueryBuilderException("Unexpected NULL value");
        }
        if (MAX == null) {
            this.apiQuery.CALORIES = MIN + "+";
        } else if (MIN == null) {
            this.apiQuery.CALORIES = MAX + "";
        } else {
            this.apiQuery.CALORIES = MIN + "-" + MAX;
        }
        return this;
    }

    public ApiQueryBuilder setTime(@Nullable Integer MIN, @Nullable Integer MAX)
        throws QueryBuilderException {
        if (MIN == null && MAX == null) {
            throw new QueryBuilderException("Unexpected NULL value");
        }
        if (MAX == null) {
            this.apiQuery.TIME = MIN + "+";
        } else if (MIN == null) {
            this.apiQuery.TIME = MAX + "";
        } else {
            this.apiQuery.TIME = MIN + "-" + MAX;
        }
        return this;
    }

    public ApiQueryBuilder exclude(@NotNull String excluded) {
        this.apiQuery.EXCLUDED = excluded;
        return this;
    }

    public ApiQuery build() {
        return this.apiQuery;
    }


}
