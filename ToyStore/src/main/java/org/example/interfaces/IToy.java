package org.example.interfaces;

public interface IToy {

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


    Category getToyCategory();
    void setToyCategory(Category category);

    AgeRestriction getToyAgeRestriction();
    void setToyAgeRestriction(AgeRestriction ageRestriction);
}
