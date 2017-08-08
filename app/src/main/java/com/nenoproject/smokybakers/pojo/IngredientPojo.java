package com.nenoproject.smokybakers.pojo;

/**
 * Created by sadanandk on 8/8/2017.
 */

public class IngredientPojo {
    private String measure, ingredient;
    private float quantity;

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public IngredientPojo(String measure, String ingredient, float quantity) {

        this.measure = measure;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}
