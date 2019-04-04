package tos.common.api.parser;

import com.google.gson.JsonArray;
import tos.common.api.entities.Health;

final class ApiHealthLabelParser {

  private ApiHealthLabelParser() {
  }

  static Health[] parse(JsonArray jsonHealthLabels) {
    int size = jsonHealthLabels.size();
    Health[] healthLabels = new Health[size];
    for (int i = 0; i < size; i++) {
      healthLabels[i] = Health.getByLabel(jsonHealthLabels.get(i).getAsString());
    }
    return healthLabels;
  }
}
