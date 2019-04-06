package tos.common.api.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import tos.common.api.entities.Diet;
import tos.common.api.entities.Health;
import tos.common.api.entities.Ingredient;
import tos.common.api.entities.Recipe;

class TestRecipeParser {

  private final Recipe SAMPLE_CHICKEN;
  private final Recipe SAMPLE_BACON;


  TestRecipeParser() {
    SAMPLE_CHICKEN = parseSingleRecipeFromSampleJson("sample_chicken_curry.json");
    SAMPLE_BACON = parseSingleRecipeFromSampleJson("sample_bacon.json");
  }

  private Recipe parseSingleRecipeFromSampleJson(String jsonFileName) {
    try {
      Path jsonFilePath = new File(getClass().getClassLoader().getResource(jsonFileName).getPath())
          .toPath();
      String content = new String(Files.readAllBytes(jsonFilePath));
      return ApiRecipeParser.parse(content).get(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Test
  void testHealthLabelParsing() {
    List<Health> labels = Arrays
        .asList(SAMPLE_BACON.getHealthLabels());
    assertTrue(labels.contains(Health.sugar_conscious));
    assertTrue(labels.contains(Health.peanut_free));
    assertTrue(labels.contains(Health.tree_nut_free));
    assertTrue(labels.contains(Health.alcohol_free));
    assertFalse(labels.contains(Health.dairy_free));
  }

  @Test
  void testDietLabelParsing() {
    List<Diet> labels = Arrays
        .asList(SAMPLE_CHICKEN.getDietLabels());
    assertTrue(labels.contains(Diet.low_carb));
    assertFalse(labels.contains(Diet.high_fiber));
  }

  @Test
  void TestIngredientParsing() {
    List<Ingredient> labels = Arrays
        .asList(SAMPLE_BACON.getIngredients());
    assertEquals(labels.get(0).getText(), "10 fresh whole chicken wings");
    assertEquals(labels.get(2).getWeight(), 1.62f);
    assertEquals(labels.get(1).getWeight(), 290f);
    assertEquals(labels.get(2).getText(), "Freshly ground black pepper");
  }

  @Test
  void TestEventualRecipeParsing() {
    assertEquals(SAMPLE_BACON.getImageUrl(),
        "https://www.edamam.com/web-img/dc3/dc3d5a0d86e3f4dc2de0dee0f660a0bd.jpg");
    assertEquals(SAMPLE_BACON.getCalories(), 1690.8662);
    assertEquals(SAMPLE_BACON.getYield(), 10);
    assertEquals(SAMPLE_CHICKEN.getLabel(), "Chicken Curry Recipe");
  }
}
