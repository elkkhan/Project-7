package tos.gui.model;


public class RecipesModel {

  private String yield, label;
  private double calories;

  public RecipesModel() {
  }

  public RecipesModel(String yield, String label, double calories) {
    this.yield = yield;
    this.label = label;
    this.calories = calories;
  }

  public String getYield() {
    return yield;
  }

  public void setYield(String yield) {
    this.yield = yield;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public double getCalories() {
    return calories;
  }

  public void setCalories(double calories) {
    this.calories = calories;
  }
}
