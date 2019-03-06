package tos.common.api.entities;

public class Recipe {

  private final String uri;
  private final String label;
  private final String imageUrl;
  private final String source;
  private final String shareAs;
  private final Double yield;
  private final Diet[] dietLabels;
  private final Health[] healthLabels;
  private final String[] cautions;
  private final String[] ingredientLines;
  private final Ingredient[] ingredients;
  private final Double calories;
  private final Double totalWeight;
  private final Double totalTime;
  private final NutrientInfo[] totalNutrients;

  public Recipe(
      String uri,
      String label,
      String imageUrl,
      String source,
      String shareAs,
      Double yield,
      Diet[] dietLabels,
      Health[] healthLabels,
      String[] cautions,
      String[] ingredientLines,
      Ingredient[] ingredients,
      Double calories,
      Double totalWeight,
      Double totalTime,
      NutrientInfo[] totalNutrients) {
    this.uri = uri;
    this.label = label;
    this.imageUrl = imageUrl;
    this.source = source;
    this.shareAs = shareAs;
    this.yield = yield;
    this.dietLabels = dietLabels;
    this.healthLabels = healthLabels;
    this.cautions = cautions;
    this.ingredientLines = ingredientLines;
    this.ingredients = ingredients;
    this.calories = calories;
    this.totalWeight = totalWeight;
    this.totalTime = totalTime;
    this.totalNutrients = totalNutrients;
  }

  public String getUri() {
    return uri;
  }

  public String getLabel() {
    return label;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getSource() {
    return source;
  }

  public String getShareAs() {
    return shareAs;
  }

  public Double getYield() {
    return yield;
  }

  public Diet[] getDietLabels() {
    return dietLabels;
  }

  public Health[] getHealthLabels() {
    return healthLabels;
  }

  public String[] getCautions() {
    return cautions;
  }

  public String[] getIngredientLines() {
    return ingredientLines;
  }

  public Ingredient[] getIngredients() {
    return ingredients;
  }

  public Double getCalories() {
    return calories;
  }

  public Double getTotalWeight() {
    return totalWeight;
  }

  public Double getTotalTime() {
    return totalTime;
  }

  public NutrientInfo[] getTotalNutrients() {
    return totalNutrients;
  }
}