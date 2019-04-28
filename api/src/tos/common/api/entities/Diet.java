package tos.common.api.entities;

public enum Diet {
  balanced("Balanced", "balanced", "Protein/Fat/Carb values in 15/35/50 ratio"),
  high_fiber("High-Fiber", "high-fiber", "More than 5g fiber per serving"),
  high_protein("High-Protein", "high-protein", "More than 50% of total calories from proteins"),
  low_carb("Low-Carb", "low-carb", "Less than 20% of total calories from carbs"),
  low_fat("Low-Fat", "low-fat", "Less than 15% of total calories from fat"),
  low_sodium("Low-Sodium", "low-sodium", "Less than 140mg Na per serving");

  private static Diet[] ALL_VALUES = Diet.values();
  private final String label;
  private final String apiParameter;
  private final String definition;

  /**
   * @param label Human-presentable value
   * @param apiParameter parameter name accepted by the API
   * @param definition diet description
   */
  Diet(String label, String apiParameter, String definition) {
    this.label = label;
    this.apiParameter = apiParameter;
    this.definition = definition;
  }

  /**
   * Returns a {@link Diet} enum matching the given label
   *
   * @param label label string to match against
   * @return matching enum object
   */
  public static Diet getByLabel(String label) {
    for (Diet diet : ALL_VALUES) {
      if (diet.label.equals(label) || diet.apiParameter.equalsIgnoreCase(label)) {
        return diet;
      }
    }
    return null;
  }

  /**
   * Returns a {@link Diet} enum matching the given api parameter
   *
   * @param apiParameter api parameter string to match against
   * @return matching enum object
   */
  public static Diet getByApiParameter(String apiParameter) {
    for (Diet diet : ALL_VALUES) {
      if (diet.apiParameter.equals(apiParameter)) {
        return diet;
      }
    }
    return null;
  }

  /**
   * Gets api parameter.
   *
   * @return the api parameter
   */
  public String getApiParameter() {
    return apiParameter;
  }

  /**
   * Gets label.
   *
   * @return the label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Gets definition.
   *
   * @return the definition
   */
  public String getDefinition() {
    return definition;
  }
}
