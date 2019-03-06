// TODO: 2019-03-03: FROM parameter should default to 0.
package tos.common.api.query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.jetbrains.annotations.NotNull;
import tos.common.api.entities.Diet;
import tos.common.api.entities.Health;
import tos.common.api.exceptions.QueryBuilderException;

public class ApiQuery {

  final String QUERY;
  final String APP_ID;
  final String APP_KEY;
  //@formatter:off
    String FROM           = null;
    String TO             = null;
    String INGR           = null;
    String DIET           = null;
    String CALORIES       = null;
    String TIME           = null;
    List<String> HEALTH   = new ArrayList<>();
    List<String> EXCLUDED = new ArrayList<>();
    //@formatter:on
    String encodedQuery;

  ApiQuery(@NotNull String api_id, @NotNull String api_key, @NotNull String qSearch) {
    this.APP_ID = api_id;
    this.APP_KEY = api_key;
    this.QUERY = qSearch;
  }

  MultiMap<String, String> mapParameters() throws QueryBuilderException {
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
}
