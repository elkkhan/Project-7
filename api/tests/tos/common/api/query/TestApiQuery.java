package tos.common.api.query;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tos.common.api.exceptions.QueryBuilderException;
import tos.common.api.query.ApiQuery.Builder;

class TestApiQuery {

  private ApiQuery.Builder builder;

  @BeforeEach
  void constructQueryBuilder() {
    String APP_ID = "TEST_APP_ID";
    String APP_KEY = "TEST_APP_KEY";
    String QUERY = "TEST_SEARCH_QUERY";
    this.builder = new Builder(APP_ID, APP_KEY, QUERY);
  }

  @ParameterizedTest
  @CsvSource({"5,-1", "0,str", "10,1str"})
  void testSetFromAndIngr(String valid, String invalid) {
    assertDoesNotThrow(() -> builder.setFrom(valid).setIngr(valid).build());
    assertThrows(
        QueryBuilderException.class, () -> builder.setFrom(invalid).setIngr(invalid).build());
  }

  @ParameterizedTest
  @CsvSource({"5,0", "1,-5", "2,str", "1000,1str"})
  void testSetTo(String valid, String invalid) {
    assertDoesNotThrow(() -> builder.setTo(valid).build());
    assertThrows(
        QueryBuilderException.class,
        () -> builder.setTo(invalid).build(),
        "TO parameter value is invalid: " + invalid);
  }

  @ParameterizedTest
  @CsvSource({"1,2,0,0", "2,3,5,4", "4,5,5,5", "100,1002,5,-5", "5,10,0,-2"})
  void testSetToSetFrom(String fromValid, String toValid, String fromInvalid, String toInvalid) {
    assertDoesNotThrow(() -> builder.setFrom(fromValid).setTo(toValid).build());
    assertThrows(
        QueryBuilderException.class,
        () -> builder.setFrom(fromInvalid).setTo(toInvalid).build(),
        "TO parameter value is invalid: " + toInvalid);
  }

  @ParameterizedTest
  @CsvSource({"high-protein,High-Protein", "balanced,BALANCE", "low-sodium,low_sodium"})
  void testSetDiet(String valid, String invalid) {
    assertDoesNotThrow(() -> builder.setDiet(valid).build());
    assertThrows(
        QueryBuilderException.class,
        () -> builder.setDiet(invalid).build(),
        "DIET parameter is invalid: " + invalid);
  }

  @ParameterizedTest
  @CsvSource({"celery-free,CELERY_FREE", "fish-free,fish_free", "low-potassium,Low-Potassium"})
  void testAddHealth(String valid, String invalid) {
    assertDoesNotThrow(() -> builder.addHealth(valid).build());
    assertThrows(
        QueryBuilderException.class,
        () -> builder.addHealth(invalid).build(),
        "HEALTH parameter is invalid: " + invalid);
  }

  @ParameterizedTest
  @CsvSource({"1,5,-5,5", "125, ,-5, ", " ,5,0,10"})
  void testSetCaloriesAndTime(
      String validMin, String validMax, String invalidMin, String invalidMax) {
    assertDoesNotThrow(
        () -> builder.setCalories(validMin, validMax).setTime(validMin, validMax).build());
    assertThrows(
        QueryBuilderException.class,
        () -> builder.setCalories(invalidMin, invalidMax).setTime(invalidMin, invalidMax).build(),
        "CALORIES parameter is invalid: " + builder.getApiQuery().getCALORIES());
  }

  @ParameterizedTest
  @ValueSource(strings = {"excludeTest", "sample", "000"})
  void testAddExclude(String input) throws QueryBuilderException {
    assertTrue(builder.addExclude(input).build().toString().contains(input));
  }

  @Test
  void testBuildQuery() throws QueryBuilderException, URISyntaxException {
    ApiQuery query =
        builder
            .setFrom("5")
            .setTo("10")
            .setDiet("balanced")
            .addHealth("low-potassium")
            .setIngr("10")
            .setCalories("500", null)
            .setTime(null, "40")
            .addExclude("pepper")
            .build();

    List<NameValuePair> params =
        URLEncodedUtils.parse(new URI(query.toString()), Charset.forName("UTF-8"));
    String expectedNameValueMapping =
        "[q=TEST_SEARCH_QUERY, app_id=TEST_APP_ID, app_key=TEST_APP_KEY, excluded=pepper, ingr=10, health=low-potassium, from=5, to=10, diet=balanced, calories=500+, time=40]";
    assertEquals(expectedNameValueMapping, params.toString());
  }
}
