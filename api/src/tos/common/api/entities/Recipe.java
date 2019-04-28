package tos.common.api.entities;

import java.util.Arrays;

public class Recipe {

  private String uri;
  private String label;
  private String imageUrl;
  private String source;
  private Double yield;
  private Diet[] dietLabels;
  private Health[] healthLabels;
  private Ingredient[] ingredients;
  private Double calories;
  private Double totalWeight;
  private Double totalTime;
  private NutrientInfo[] totalNutrients;

  private Recipe() {}

  public static Builder builder() {
    return new Builder();
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

  public Double getYield() {
    return yield;
  }

  public Diet[] getDietLabels() {
    return dietLabels;
  }

  public Health[] getHealthLabels() {
    return healthLabels;
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

  @Override
  public String toString() {
    return "Recipe{"
        + "uri='"
        + uri
        + '\''
        + ", label='"
        + label
        + '\''
        + ", imageUrl='"
        + imageUrl
        + '\''
        + ", source='"
        + source
        + '\''
        + ", yield="
        + yield
        + ", dietLabels="
        + Arrays.toString(dietLabels)
        + ", healthLabels="
        + Arrays.toString(healthLabels)
        + ", ingredients="
        + Arrays.toString(ingredients)
        + ", calories="
        + calories
        + ", totalWeight="
        + totalWeight
        + ", totalTime="
        + totalTime
        + ", totalNutrients="
        + Arrays.toString(totalNutrients)
        + '}';
  }

  public static class Builder {

    private Recipe recipe = new Recipe();

    private Builder() {}

    public Builder setUri(String uri) {
      this.recipe.uri = uri;
      return this;
    }

    public Builder setLabel(String label) {
      this.recipe.label = label;
      return this;
    }

    public Builder setImageUrl(String imageUrl) {
      this.recipe.imageUrl = imageUrl;
      return this;
    }

    public Builder setSource(String source) {
      this.recipe.source = source;
      return this;
    }

    public Builder setYield(Double yield) {
      this.recipe.yield = yield;
      return this;
    }

    public Builder setDietLabels(Diet[] dietLabels) {
      this.recipe.dietLabels = dietLabels;
      return this;
    }

    public Builder setHealthLabels(Health[] healthLabels) {
      this.recipe.healthLabels = healthLabels;
      return this;
    }

    public Builder setIngredients(Ingredient[] ingredients) {
      this.recipe.ingredients = ingredients;
      return this;
    }

    public Builder setCalories(Double calories) {
      this.recipe.calories = calories;
      return this;
    }

    public Builder setTotalWeight(Double totalWeight) {
      this.recipe.totalWeight = totalWeight;
      return this;
    }

    public Builder setTotalTime(Double totalTime) {
      this.recipe.totalTime = totalTime;
      return this;
    }

    @Deprecated
    public Builder setNutrientInfo(NutrientInfo[] totalNutrients) {
      this.recipe.totalNutrients = totalNutrients;
      return this;
    }

    public Recipe build() {
      return this.recipe;
    }
  }
}
