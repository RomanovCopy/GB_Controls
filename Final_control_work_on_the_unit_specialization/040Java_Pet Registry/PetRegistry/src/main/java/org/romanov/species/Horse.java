package org.romanov.species;

import org.romanov.Interfaces.*;
import org.romanov.model.Animal;

import java.io.Serializable;

public class Horse extends Animal implements Serializable {
    public Horse() {
        setSpecies("Horse");
    }
}
