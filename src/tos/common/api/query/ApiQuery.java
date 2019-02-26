package tos.common.api.client.query;

import java.util.List;
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
    String FROM;
    String TO;
    String INGR;
    String DIET;
    List<String> HEALTH;
    String CALORIES_FROM;
    String CALORIES_TO;
    String TIME_FROM;
    String TIME_TO;
    List<String> EXCLUDED;

    String encodedQuery;
    MultiMap<String, String> parameterValues = new MultiValueMap<>();

    ApiQuery(@NotNull String api_id, @NotNull String api_key, @NotNull String qSearch) {
        this.APP_ID = api_id;
        this.APP_KEY = api_key;
        this.QUERY = qSearch;
    }

//    private MultiValueMap<String, String> getParameterMapping() throws QueryBuilderException {
//
//    }

    private String validateFrom() throws QueryBuilderException {
        if (FROM == null) {
            return null;
        }
        int fromValue;
        try {
            fromValue = Integer.parseInt(FROM);
        } catch (NumberFormatException e) {
            throw new QueryBuilderException("FROM parameter is not a number: " + FROM);
        }
        if (fromValue < 0) {
            throw new QueryBuilderException("FROM parameter is negative: " + FROM);
        }
        return FROM;
    }

    private String validateTo() throws QueryBuilderException {
        if (TO == null) {
            return null;
        }
        int toValue;
        try {
            toValue = Integer.parseInt(TO);
        } catch (NumberFormatException e) {
            throw new QueryBuilderException("TO parameter is not a number: " + TO);
        }
        if (toValue < 0) {
            throw new QueryBuilderException("TO parameter is negative: " + TO);
        }
        if (FROM != null && toValue < Integer.valueOf(FROM)) {
            throw new QueryBuilderException(
                "TO parameter value is less than FROM parameter value: " + TO + "<" + FROM);
        }
        return TO;
    }

    private String validateIngr() throws QueryBuilderException {
        if (INGR == null) {
            return null;
        }
        int ingrValue;
        try {
            ingrValue = Integer.parseInt(INGR);
        } catch (NumberFormatException e) {
            throw new QueryBuilderException("INGR parameter is not a number: " + INGR);
        }
        if (ingrValue < 0) {
            throw new QueryBuilderException("INGR value is negative: " + INGR);
        }
        return INGR;
    }

    private String validateDiet() throws QueryBuilderException {
        if (DIET == null) {
            return null;
        }
        for (Diet diet : Diet.values()) {
            if (diet.getaApiParameter().equals(DIET)) {
                return DIET;
            }
        }
        throw new QueryBuilderException("DIET parameter value is not a valid diet: " + DIET);
    }

    private List<String> validateHealth() throws QueryBuilderException {
        if (HEALTH == null) {
            return null;
        }
        for (String healthLabel : HEALTH) {
            boolean valid = false;
            for (Health healthEnum : Health.values()) {
                if (healthLabel.equals(healthEnum.getaApiParameter())) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                throw new QueryBuilderException(
                    "HEALTH parameter is not a valid health label: " + healthLabel);
            }
        }
        return HEALTH;
    }

    private String validateCalories() throws QueryBuilderException {
        if (CALORIES_TO == null && CALORIES_FROM == null) {
            return null;
        }
        StringBuilder calories = new StringBuilder();
        Integer caloriesFrom = null;
        Integer caloriesTo = null;
        if (CALORIES_FROM != null) {
            try {
                caloriesFrom = Integer.parseInt(FROM);
            } catch (NumberFormatException e) {
                throw new QueryBuilderException(
                    "CALORIES parameter is not a number: " + CALORIES_FROM);
            }
        }
        if (CALORIES_TO != null) {
            try {
                caloriesTo = Integer.parseInt(CALORIES_TO);
            } catch (NumberFormatException e) {
                throw new QueryBuilderException(
                    "CALORIES parameter is not a number: " + CALORIES_TO);
            }
        }

        if (caloriesFrom != null) {
            calories.append(CALORIES_FROM);
            if (caloriesTo != null) {
                calories.append("-");
            }
        }
        if (caloriesTo != null) {
            calories.append(CALORIES_TO);
        }
        return calories.toString();
    }

    private String validateTime() {
        
    }

    @Override
    public String toString() {
        return encodedQuery;
    }
}
