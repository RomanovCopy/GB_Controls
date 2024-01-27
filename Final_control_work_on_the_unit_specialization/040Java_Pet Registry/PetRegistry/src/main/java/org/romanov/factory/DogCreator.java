package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.Dog;

public class DogCreator implements ICreator {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }

    @Override
    public String getAnimalInfo() {
        return "Dog";
    }

}
