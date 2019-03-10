// TODO: 2019-03-09 think of if to return a list of recipes or not, because in @hits there are multiple recipes
package tos.common.api.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import tos.common.api.entities.Recipe;

public class ApiRecipeParser {

  //@formatter:off
  private static final String HITS_KEY         = "hits";
  private static final String RECIPE_KEY       = "recipe";
  private static final String LABEL            = "label";
  private static final String IMAGE_URL        = "image";
  private static final String RECIPE_URL       = "url";
  private static final String YIELD            = "yield";
  private static final String DIET_LABELS      = "dietLabels";
  private static final String HEALTH_LABELS    = "healthLabels";
  private static final String INGREDIENTS      = "ingredients";
  private static final String CALORIES         = "calories";
  private static final String TOTAL_WEIGHT     = "totalWeight";
  //@formatter:on


  /**
   * Parses a JSON String literal into a {@link Recipe} object
   *
   * @param jsonString JSON string to parse
   * @return Parsed Recipe object
   */
  @NotNull
  public static List<Recipe> parse(@NotNull final String jsonString) {
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
  }

  @NotNull
  private static Recipe parseRecipe(@NotNull final JsonObject jsonRecipe) {
    Recipe.Builder recipeBuilder = Recipe.builder()
        .setUri(jsonRecipe.get(RECIPE_URL).getAsString())
        .setLabel(jsonRecipe.get(LABEL).getAsString())
        .setImageUrl(jsonRecipe.get(IMAGE_URL).getAsString())
        .setYield(jsonRecipe.get(YIELD).getAsDouble())
        .setHealthLabels(
            ApiHealthLabelParser.parse(
                jsonRecipe.getAsJsonArray(HEALTH_LABELS)))
        .setDietLabels(
            ApiDietLabelParser.parse(
                jsonRecipe.getAsJsonArray(DIET_LABELS)))
        .setIndredients(
            ApiIngredientParser.parse(
                jsonRecipe.getAsJsonArray(INGREDIENTS)))
        .setCalories(jsonRecipe.get(CALORIES).getAsDouble())
        .setTotalWeight(jsonRecipe.get(TOTAL_WEIGHT).getAsDouble());
    return recipeBuilder.build();
  }


  public static void main(String[] args) throws Exception {
    Charset utf8 = StandardCharsets.UTF_8;
    String json = new String(Files.readAllBytes(Paths.get("json/sample_query.json")), utf8);
    Recipe recipe = ApiRecipeParser.parse(json).get(0);
    System.out.println(recipe);

    //HEALTH RETURNS NULL
    //Labels in docs are wrong, check against api-param with tolower()
    //
  }
}
