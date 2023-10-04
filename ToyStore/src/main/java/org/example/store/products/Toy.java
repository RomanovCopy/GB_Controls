package org.example.store.products;

import org.example.base.Lot;


public class Toy extends Lot {

    /**
     * категория игрушки
     */
    enum Category {
        DOLLS,
        ACTION_FIGURES,
        PUZZLES,
        BOARD_GAMES,
        VEHICLES
    }
    /**
     * возрастные ограничения игрушки
     */
    enum AgeRestriction {
        AGE_0_2,
        AGE_3_5,
        AGE_6_8,
        AGE_9_PLUS
    }
    private Category category;
    private AgeRestriction ageRestriction;


    public Toy(String name, Double price, String description, Category category, AgeRestriction ageRestriction) {
        super(name,price,description);
        this.category = category;
        this.ageRestriction = ageRestriction;
    }

    /**
     * получить категорию игрушки
     * @return категория
     */
    public Category getCategory() {
        return category;
    }

    /**
     * получить возрастное ограничение игрушки
     * @return возрастное ограничение
     */
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }
}
