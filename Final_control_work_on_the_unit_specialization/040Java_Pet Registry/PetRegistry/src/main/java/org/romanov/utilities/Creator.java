package org.romanov.utilities;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.species.*;

public class Creator implements ICreator {
    @Override
    public Animal catCreate() {
        return new Cat();
    }

    @Override
    public Animal camelCreator() {
        return new Camel();
    }

    @Override
    public Animal dogCreator() {
        return new Dog();
    }

    @Override
    public Animal donKeyCreator() {
        return new Donkey();
    }

    @Override
    public Animal horseCreator() {
        return new Horse();
    }

}
