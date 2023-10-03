package org.example.store;

import org.example.base.Toy;
import org.example.interfaces.IToy;

public class Lot extends Toy implements IToy{
    private String name;
    private Double price;
    private String description;

    public Lot(String name, Double price, String description) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void setName(String name) {

    }
    public String getName() {
        return null;
    }

    public void setPrice(double price) {

    }
    public double getPrice() {
        return 0;
    }

    public void setDescription(String description) {

    }
    public String getDescription() {
        return null;
    }

    @Override
    public void setToyCategory(Category category) {
        this.category=category;
    }
    @Override
    public Category getToyCategory() {
        return null;
    }


    @Override
    public void setToyAgeRestriction(AgeRestriction ageRestriction) {

    }
    @Override
    public AgeRestriction getToyAgeRestriction() {
        return null;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
