package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.Camel;

public class CamelCreator implements ICreator {


    @Override
    public Animal createAnimal() {
        return new Camel();
    }

    @Override
    public String getAnimalInfo() {
        return "Camel";
    }


}
