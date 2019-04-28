package tos.common.api.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import tos.common.api.entities.Ingredient;

final class ApiIngredientParser {

  private ApiIngredientParser() {}

  static Ingredient[] parse(JsonArray jsonIngredients) {
    return new Gson().fromJson(jsonIngredients, Ingredient[].class);
  }
}
