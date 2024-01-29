package org.romanov.factory;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;

import java.util.HashMap;

public class Creator {
    private HashMap<Integer, ICreator> species;

    public Creator() {
        species = new HashMap<>();
        // Добавление видов животных в фабрику
        addSpecies( 1, new CamelCreator());
        addSpecies( 2, new CatCreator());
        addSpecies( 3, new DogCreator());
        addSpecies(4, new DonkeyCreator());
        addSpecies(5, new HorseCreator());
    }

    // Добавление вида животного в фабрику
    public void addSpecies(int key, ICreator creator) {
        species.put(key, creator);
    }

    /**
     * доступные для создания виды
     * @return
     */
    public HashMap<Integer, ICreator> getSpecies(){
        return species;
    }

    // Создание животного по ключу
    public Animal createAnimal(int key) {
        ICreator creator = species.get(key);
        Animal animal=null;
        if (creator != null) {
            animal = creator.createAnimal();
        } else {
            System.out.println("Вид животного с ключом " + key + " не найден");
        }
        return animal;
    }
}
