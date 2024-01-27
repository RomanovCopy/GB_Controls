package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.Donkey;

public class DonkeyCreator implements ICreator {
    @Override
    public Animal createAnimal() {
        return new Donkey();
    }

    @Override
    public String getAnimalInfo() {
        return "Donkey";
    }

}
