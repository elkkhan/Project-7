package tos.common.api.entities;

public enum HealthLabels {
    alcohol_free("Alcohol-free", "alcohol_free", "No alcohol used or contained"),
    celery_free("Celery-free", "celery_free", "does not contain celery or derivatives"),
    crustacean_free("Crustacean-free", "crustacean_free",
        "does not contain crustaceans (shrimp, lobster etc.) or derivatives"),
    dairy_free("Dairy", "dairy_free", "No dairy,no lactose"),
    egg_free("Eggs", "egg_free", "No eggs or products containing eggs"),
    fish_free("Fish", "fish_free", "No fish or fish derivatives"),
    gluten_free("Gluten", "gluten_free", "No ingredients containing gluten"),
    kidney_friendly("Kidney friendly", "kidney_friendly",
        "per serving – phosphorus less than 250 mg AND potassium less than 500 mg AND sodium: less than 500 mg"),
    kosher("Kosher", "kosher",
        "contains only ingredients allowed by the kosher diet. However it does not guarantee kosher preparation of the ingredients themselves"),
    low_potassium("Low potassium", "low_potassium", "Less than 150mg per serving"),
    lupine_free("Lupine-free", "lupine_free", "does not contain lupine or derivatives"),
    mustard_free("Mustard-free", "mustard_free", "does not contain mustard or derivatives"),
    No_oil_added("No oil added", "No_oil-added",
        "No oil added except to what is contained in the basic ingredients"),
    low_sugar("No-sugar", "low_sugar",
        "No simple sugars – glucose, dextrose, galactose, fructose, sucrose, lactose, maltose"),
    paleo("Paleo", "paleo",
        "Excludes what are perceived to be agricultural products, grains, legumes, dairy products, potatoes, refined salt, refined sugar, and processed oils"),
    peanut_free("Peanuts", "peanut_free", "No peanuts or products containing peanuts"),
    pescatarian("Pescatarian", "pescatarian",
        "Does not contain meat or meat based products, can contain dairy and fish"),
    pork_free("Pork-free", "pork_free", "does not contain pork or derivatives"),
    red_meat_free("Red meat-free", "red_meat-free",
        "does not contain beef, lamb, pork, duck, goose, game, horse, and other types of red meat or products containing red meat."),
    sesame_free("Sesame-free", "sesame_free", "does not contain sesame seed or derivatives"),
    shellfish_free("Shellfish", "shellfish_free", "No shellfish or shellfish derivatives"),
    soy_free("Soy", "soy_free", "No soy or products containing soy"),
    sugar_conscious("Sugar-conscious", "sugar_conscious", "Less than 4g of sugar per serving"),
    tree_nut_free("Tree Nuts", "tree_nut-free", "No tree nuts or products containing tree nuts"),
    vegan("Vegan", "vegan", "No meat, poultry, fish, dairy, eggs or honey"),
    vegetarian("Vegetarian", "vegetarian", "No meat, poultry, or fish"),
    wheat_free("Wheat-free", "wheat_free", "No wheat, can have gluten though");

    private final String label;
    private final String apiParameter;
    private final String definition;

    HealthLabels(String label, String apiParameter, String definition) {
        this.label = label;
        this.apiParameter = apiParameter;
        this.definition = definition;
    }

    public String getaApiParameter() {
        return apiParameter;
    }

    public String getLabel() {
        return label;
    }

    public String getDefinition() {
        return definition;
    }
}