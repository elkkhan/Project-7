package tos.common.api.parser;

import com.google.gson.JsonArray;
import tos.common.api.entities.Health;

final class ApiHealthLabelParser {

  private ApiHealthLabelParser() {
  }

  /**
   * Parses a "health" JSON array from the API response into an array of {@link Health} enum
   * objects.
   *
   * @param jsonHealthLabels the JSON array to be parsed
   * @return an array oh {@link Health} enum objects
   */
  static Health[] parse(JsonArray jsonHealthLabels) {
    int size = jsonHealthLabels.size();
    Health[] healthLabels = new Health[size];
    for (int i = 0; i < size; i++) {
      healthLabels[i] = Health.getByLabel(jsonHealthLabels.get(i).getAsString());
    }
    return healthLabels;
  }
}
