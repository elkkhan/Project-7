package tos.common.api.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import tos.common.api.entities.Recipe;
import tos.common.api.exceptions.ParserException;

public class ApiRecipeParser {

  // @formatter:off
  private static final String HITS_KEY = "hits";
  private static final String RECIPE_KEY = "recipe";
  private static final String LABEL = "label";
  private static final String IMAGE_URL = "image";
  private static final String RECIPE_URL = "url";
  private static final String YIELD = "yield";
  private static final String DIET_LABELS = "dietLabels";
  private static final String HEALTH_LABELS = "healthLabels";
  private static final String INGREDIENTS = "ingredients";
  private static final String CALORIES = "calories";
  private static final String TOTAL_WEIGHT = "totalWeight";
  // @formatter:on

  /** Private empty constructor so that the class can't be instantiated. parse() is a static */
  private ApiRecipeParser() {}

  /**
   * Parses a JSON response from the API into {@link Recipe} objects
   *
   * @param jsonString JSON string to parse
   * @return Parsed {@link Recipe} object
   */
  @NotNull
  public static List<Recipe> parse(@NotNull final String jsonString) throws ParserException {
    try {
      JsonParser parser = new JsonParser();
      JsonObject wholeJson = parser.parse(jsonString).getAsJsonObject();
      JsonArray hits = wholeJson.getAsJsonArray(HITS_KEY);
      List<JsonObject> jsonRecipes = new ArrayList<>();
      for (JsonElement hit : hits) {
        jsonRecipes.add(hit.getAsJsonObject().getAsJsonObject(RECIPE_KEY));
      }
      List<Recipe> parsedRecipes = new ArrayList<>();
      for (JsonObject jsonRecipe : jsonRecipes) {
        parsedRecipes.add(parseRecipe(jsonRecipe));
      }
      return parsedRecipes;
    } catch (JsonParseException | IllegalStateException e) {
      throw new ParserException(e.getMessage());
    }
  }

  /**
   * Parses a single JSON recipe object into a {@link Recipe} object
   *
   * @param jsonRecipe a JSON "recipe" object found in JSON "hits" array
   * @return a parsed {@link Recipe} object
   */
  @NotNull
  private static Recipe parseRecipe(@NotNull final JsonObject jsonRecipe) {
    Recipe.Builder recipeBuilder =
        Recipe.builder()
            .setUri(jsonRecipe.get(RECIPE_URL).getAsString())
            .setLabel(jsonRecipe.get(LABEL).getAsString())
            .setImageUrl(jsonRecipe.get(IMAGE_URL).getAsString())
            .setYield(jsonRecipe.get(YIELD).getAsDouble())
            .setCalories(jsonRecipe.get(CALORIES).getAsDouble())
            .setTotalWeight(jsonRecipe.get(TOTAL_WEIGHT).getAsDouble())
            .setHealthLabels(ApiHealthLabelParser.parse(jsonRecipe.getAsJsonArray(HEALTH_LABELS)))
            .setDietLabels(ApiDietLabelParser.parse(jsonRecipe.getAsJsonArray(DIET_LABELS)))
            .setIngredients(ApiIngredientParser.parse(jsonRecipe.getAsJsonArray(INGREDIENTS)));
    return recipeBuilder.build();
  }
}
