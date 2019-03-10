package tos.common.api.entities;

public enum Health {
  alcohol_free(
      "Alcohol-free",
      "alcohol-free",
      "No alcohol used or contained"),
  celery_free(
      "Celery-free",
      "celery-free",
      "does not contain celery or derivatives"),
  crustacean_free("Crustacean-free",
      "crustacean-free",
      "does not contain crustaceans (shrimp, lobster etc.) or derivatives"),
  dairy_free(
      "Dairy",
      "dairy-free",
      "No dairy,no lactose"),
  egg_free(
      "Eggs",
      "egg-free",
      "No eggs or products containing eggs"),
  fish_free(
      "Fish",
      "fish-free",
      "No fish or fish derivatives"),
  gluten_free(
      "Gluten",
      "gluten-free",
      "No ingredients containing gluten"),
  kidney_friendly(
      "Kidney friendly",
      "kidney-friendly",
      "per serving – phosphorus less than 250 mg AND potassium less than 500 mg AND sodium: less than 500 mg"),
  kosher(
      "Kosher",
      "kosher",
      "contains only ingredients allowed by the kosher diet. However it does not guarantee kosher preparation of the ingredients themselves"),
  low_potassium(
      "Low potassium",
      "low-potassium",
      "Less than 150mg per serving"),
  lupine_free(
      "Lupine-free", "lupine-free",
      "does not contain lupine or derivatives"),
  mustard_free(
      "Mustard-free",
      "mustard-free",
      "does not contain mustard or derivatives"),
  No_oil_added(
      "No oil added",
      "No-oil-added",
      "No oil added except to what is contained in the basic ingredients"),
  low_sugar(
      "No-sugar",
      "low-sugar",
      "No simple sugars – glucose, dextrose, galactose, fructose, sucrose, lactose, maltose"),
  paleo(
      "Paleo",
      "paleo",
      "Excludes what are perceived to be agricultural products, "
          + "grains, legumes, dairy products, potatoes, refined salt, refined sugar, and processed oils"),
  peanut_free(
      "Peanuts",
      "peanut-free",
      "No peanuts or products containing peanuts"),
  pescatarian(
      "Pescatarian",
      "pescatarian",
      "Does not contain meat or meat based products, can contain dairy and fish"),
  pork_free(
      "Pork-free",
      "pork-free",
      "does not contain pork or derivatives"),
  red_meat_free(
      "Red meat-free",
      "red-meat-free",
      "does not contain beef, lamb, pork, duck, goose, game, horse, and other types of red meat or products containing red meat."),
  sesame_free(
      "Sesame-free",
      "sesame-free",
      "does not contain sesame seed or derivatives"),
  shellfish_free(
      "Shellfish",
      "shellfish-free",
      "No shellfish or shellfish derivatives"),
  soy_free(
      "Soy",
      "soy-free",
      "No soy or products containing soy"),
  sugar_conscious(
      "Sugar-conscious",
      "sugar-conscious",
      "Less than 4g of sugar per serving"),
  tree_nut_free(
      "Tree Nuts",
      "tree-nut-free",
      "No tree nuts or products containing tree nuts"),
  vegan(
      "Vegan",
      "vegan",
      "No meat, poultry, fish, dairy, eggs or honey"),
  vegetarian(
      "Vegetarian",
      "vegetarian",
      "No meat, poultry, or fish"),
  wheat_free(
      "Wheat-free",
      "wheat-free",
      "No wheat, can have gluten though");

  private static final Health[] ALL_VALUES = Health.values();
  private final String label;
  private final String apiParameter;
  private final String definition;

  /**
   * @param label Human-presentable value
   * @param apiParameter parameter name accepted by the API
   * @param definition diet description
   */
  Health(String label, String apiParameter, String definition) {
    this.label = label;
    this.apiParameter = apiParameter;
    this.definition = definition;
  }

  public static Health getByLabel(String label) {
    for (Health health : ALL_VALUES) {
      if (health.label.equals(label)
          || health.apiParameter.equalsIgnoreCase(label)) {
        return health;
      }
    }
    return null;
  }

  public static Health getByApiParameter(String apiParameter) {
    for (Health health : ALL_VALUES) {
      if (health.apiParameter.equals(apiParameter)) {
        return health;
      }
    }
    return null;
  }

  public String getApiParameter() {
    return apiParameter;
  }

  public String getLabel() {
    return label;
  }

  public String getDefinition() {
    return definition;
  }
}