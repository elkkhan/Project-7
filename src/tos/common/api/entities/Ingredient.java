package tos.common.api.entities;

class Ingredient {

    private final String text;
    private final Float weight;

    Ingredient(String text, Float weight) {
        this.text = text;
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public Float getWeight() {
        return weight;
    }


}
