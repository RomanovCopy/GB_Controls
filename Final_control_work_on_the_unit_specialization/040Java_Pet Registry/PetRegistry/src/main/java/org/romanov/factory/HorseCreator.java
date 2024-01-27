package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.Horse;

public class HorseCreator implements ICreator {
    @Override
    public Animal createAnimal() {
        return new Horse();
    }

    @Override
    public String getAnimalInfo() {
        return "Horse";
    }

}
