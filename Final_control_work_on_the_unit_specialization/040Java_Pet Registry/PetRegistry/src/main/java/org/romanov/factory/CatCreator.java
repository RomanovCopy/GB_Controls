package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.Cat;

public class CatCreator implements ICreator {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }

    @Override
    public String getAnimalInfo() {
        return "Cat";
    }

}
