package tos.common.api.entities;

public class Recipe {

    private final String name;
    private final String imageUrl;
    private final String source;
    private final String sourceUrl;
    private final Integer yield;
    private final Float calories;
    private final Float totalWeight;
    private final Ingredient[] ingredients;
    private final NutrientInfo[] totalNutrients;
    private final NutrientInfo[] totalDaily;
    private final Diet[] dietLabels;
    private final Health[] healthLabels;

    public Recipe(String name, String imageUrl, String source, String sourceUrl,
        Integer yield, Float calories, Float totalWeight,
        Ingredient[] ingredients, NutrientInfo[] totalNutrients, NutrientInfo[] totalDaily,
        Diet[] dietLabels, Health[] healthLabels) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.source = source;
        this.sourceUrl = sourceUrl;
        this.yield = yield;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.ingredients = ingredients;
        this.totalNutrients = totalNutrients;
        this.totalDaily = totalDaily;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Integer getYield() {
        return yield;
    }

    public Float getCalories() {
        return calories;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public NutrientInfo[] getTotalNutrients() {
        return totalNutrients;
    }

    public NutrientInfo[] getTotalDaily() {
        return totalDaily;
    }

    public Diet[] getDietLabels() {
        return dietLabels;
    }

    public Health[] getHealthLabels() {
        return healthLabels;
    }
}
