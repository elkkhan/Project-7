package tos.common.api.parser;

import com.google.gson.JsonArray;
import tos.common.api.entities.Diet;

final class ApiDietLabelParser {

  static Diet[] parse(JsonArray jsonDietLabels) {
    int size = jsonDietLabels.size();
    Diet[] dietLabels = new Diet[size];
    for (int i = 0; i < size; i++) {
      dietLabels[i] = Diet.getByLabel(jsonDietLabels.get(i).getAsString());
    }
    return dietLabels;
  }
}
