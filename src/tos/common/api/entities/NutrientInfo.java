package tos.common.api.entities;

public class NutrientInfo {

  private final String label;
  private final Float quantity;
  private final String unit;

  public NutrientInfo(String label, Float quantity, String unit) {
    this.label = label;
    this.quantity = quantity;
    this.unit = unit;
  }

  public String getLabel() {
    return label;
  }

  public Float getQuantity() {
    return quantity;
  }

  public String getUnit() {
    return unit;
  }
}
