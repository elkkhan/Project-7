// FIXME: 2019-03-03: FROM parameter should default to 0.

package tos.common.api.query;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;
import tos.common.api.client.ApiClient;
import tos.common.api.entities.Diet;
import tos.common.api.entities.Health;
import tos.common.api.exceptions.QueryBuilderException;

public class ApiQuery {

  private final String QUERY;
  private final String APP_ID;
  private final String APP_KEY;
  //@formatter:off
  private String FROM = null;
  private String TO = null;
  private String INGR = null;
  private String DIET = null;
  private String CALORIES = null;
  private String TIME = null;
  private String encodedQuery = null;
  private List<String> HEALTH = new ArrayList<>();
  private List<String> EXCLUDED = new ArrayList<>();
  //@formatter:on

  private ApiQuery(@NotNull String api_id, @NotNull String api_key, @NotNull String qSearch) {
    this.APP_ID = api_id;
    this.APP_KEY = api_key;
    this.QUERY = qSearch;
  }


  private MultiMap<String, String> mapParameters() throws QueryBuilderException {
    validateQuery();
    MultiMap<String, String> parameterValues = new MultiValueMap<>();
    parameterValues.put("from", FROM);
    parameterValues.put("to", TO);
    parameterValues.put("ingr", INGR);
    parameterValues.put("diet", DIET);
    parameterValues.put("calories", CALORIES);
    parameterValues.put("time", TIME);
    for (String healthLabel : HEALTH) {
      parameterValues.put("health", healthLabel);
    }
    for (String excludedFood : EXCLUDED) {
      parameterValues.put("excluded", excludedFood);
    }
    return parameterValues;
  }

  private void validateQuery() throws QueryBuilderException {
    if (!validateFrom()) {
      throw new QueryBuilderException("FROM parameter value is invalid: " + FROM);
    }
    if (!validateTo()) {
      throw new QueryBuilderException("TO parameter value is invalid: " + TO);
    }
    if (!validateIngr()) {
      throw new QueryBuilderException("INGR parameter is invalid: " + INGR);
    }
    if (!validateDiet()) {
      throw new QueryBuilderException("DIET parameter is invalid: " + DIET);
    }
    if (HEALTH != null) {
      for (String healthLabel : HEALTH) {
        if (!validateHealth(healthLabel)) {
          throw new QueryBuilderException("HEALTH parameter is invalid: " + healthLabel);
        }
      }
    }
    if (!validateCalories()) {
      throw new QueryBuilderException("CALORIES parameter is invalid: " + CALORIES);
    }
    if (!validateTime()) {
      throw new QueryBuilderException("TIME parameter is invalid: " + TIME);
    }
  }

  private boolean validateFrom() {
    return validateFromOrIngr(FROM);
  }

  private boolean validateTo() {
    if (TO == null) {
      return true;
    }
    int toValue;
    try {
      toValue = Integer.parseInt(TO);
      if (toValue < Integer.parseInt(FROM)) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private boolean validateIngr() {
    return validateFromOrIngr(INGR);
  }

  private boolean validateDiet() {
    if (DIET == null) {
      return true;
    }
    for (Diet dietEnum : Diet.values()) {
      if (DIET.equals(dietEnum.getApiParameter())) {
        return true;
      }
    }
    return false;
  }

  private boolean validateHealth(String healthLabel) {
    for (Health healthEnum : Health.values()) {
      if (healthLabel.equals(healthEnum.getApiParameter())) {
        return true;
      }
    }
    return false;
  }

  private boolean validateCalories() {
    return validateCaloriesOrTime(CALORIES);
  }

  private boolean validateTime() {
    return validateCaloriesOrTime(TIME);
  }

  private boolean validateCaloriesOrTime(String caloriesOrTime) {
    if (caloriesOrTime == null) {
      return true;
    }
    Pattern pattern1 = Pattern.compile("^\\d+$");//123
    Pattern pattern2 = Pattern.compile("^\\d+\\+$");//123+
    Pattern pattern3 = Pattern.compile("^\\d+-\\d+$");//123-456
    if (pattern1.matcher(caloriesOrTime).matches() || pattern2.matcher(caloriesOrTime)
        .matches()) {
      return true;
    }
    if (pattern3.matcher(caloriesOrTime).matches()) {
      int from = Integer.parseInt(caloriesOrTime.substring(0, caloriesOrTime.indexOf("-")));
      int to = Integer.parseInt(caloriesOrTime.substring(caloriesOrTime.indexOf("-")));
      return from <= to;
    }
    return true;
  }

  private boolean validateFromOrIngr(String fromOrIngr) {
    if (fromOrIngr == null) {
      return true;
    }
    int fromOrIngValue;
    try {
      fromOrIngValue = Integer.parseInt(fromOrIngr);
      if (fromOrIngValue < 1) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }


  @Override
  public String toString() {
    return encodedQuery;
  }


  public static class Builder {

    private final ApiQuery apiQuery;

    public Builder(@NotNull String api_id, @NotNull String api_key,
        @NotNull String qSearch) {
      this.apiQuery = new ApiQuery(api_id, api_key, qSearch);
    }

    public Builder setFrom(@NotNull String from) {
      this.apiQuery.FROM = from;
      return this;
    }

    public Builder setTo(@NotNull String to) {
      this.apiQuery.TO = to;
      return this;
    }

    public Builder setIngr(@NotNull String ingr) {
      this.apiQuery.INGR = ingr;
      return this;
    }

    public Builder setDiet(@NotNull String dietParameter) {
      this.apiQuery.DIET = dietParameter;
      return this;
    }

    public Builder addHealth(@NotNull String healthParameter) {
      this.apiQuery.HEALTH.add(healthParameter);
      return this;
    }

    public Builder setCalories(String min, String max) {
      if (min != null && max != null) {
        this.apiQuery.CALORIES = min + "-" + max;
      } else if (min == null && max != null) {
        this.apiQuery.CALORIES = max;
      } else {
        this.apiQuery.CALORIES = min + "+";
      }
      return this;
    }

    public Builder setTime(String min, String max) {
      if (min != null && max != null) {
        this.apiQuery.TIME = min + "-" + max;
      } else if (min == null && max != null) {
        this.apiQuery.TIME = max;
      } else {
        this.apiQuery.TIME = min + "+";
      }
      return this;
    }

    public Builder addExclude(@NotNull String excluded) {
      this.apiQuery.EXCLUDED.add(excluded);
      return this;
    }

    /**
     * Build the query. Must be called for a query to be properly built
     *
     * @return a query ready to be executed
     * @throws QueryBuilderException if the base path is malformed
     */
    public ApiQuery build() throws QueryBuilderException {
      try {
        URIBuilder uriBuilder = new URIBuilder(ApiClient.getBasePath());
        uriBuilder.addParameter("q", this.apiQuery.QUERY);
        uriBuilder.addParameter("app_id", this.apiQuery.APP_ID);
        uriBuilder.addParameter("app_key", this.apiQuery.APP_KEY);
        MultiMap<String, String> parameterValueMapping = this.apiQuery.mapParameters();
        for (String parameterName : parameterValueMapping.keySet()) {
          Collection parameterValues = (Collection) parameterValueMapping.get(parameterName);
          for (Object parameterValue : parameterValues) {
            if (parameterValue != null) {
              uriBuilder.addParameter(parameterName, (String) parameterValue);
            }
          }
        }
        this.apiQuery.encodedQuery = uriBuilder.build().toString();
      } catch (URISyntaxException e) {
        throw new QueryBuilderException(e.getMessage());
      }
      return this.apiQuery;
    }
  }
}
