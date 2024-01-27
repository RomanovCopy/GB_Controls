package org.romanov.model;

import org.romanov.Interfaces.ICreator;
import org.romanov.factory.Creator;
import org.romanov.utilities.PetRegistryIO;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class PetRegistryModel {


    /**
     * коллекция животных занесенных в реестр
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    private ArrayList<Animal>animals;

    private String filePath;
    private Creator creator;

    public HashMap<Integer, ICreator>getSpecies(){
        return creator.getSpecies();
    }


    /**
     * конструктор класса
     */
    public PetRegistryModel() {
        animals=loadAnimals();
        creator=new Creator();
    }


    /**
     * добавление животного в реестр
     * @param animal добавляемое животное
     * @return результат выполнения( True - успешно, False - ошибка )
     */
    public boolean addAnimal(Animal animal){
        if(animal instanceof Animal && animals!=null){
            animals.add(animal);
            return true;
        }
        return false;
    }

    /**
     * создание экземпляра вида животного
     * @param key индекс вида
     * @return Animal
     */
    public Animal createAnimal(int key){
        Animal animal = creator.createAnimal(key);
        if(animal==null){
            System.out.println("Животное не создано.");
        }
        return animal;
    }




    /**
     * сортировка по дате рождения
     */
    public void sortAnimalsByBirthDate() {
        Collections.sort(animals, Comparator.comparing(Animal::getDateOfBirth));
    }


    /**
     * загрузка с диска коллекции животных
     * @return ArrayList<Animal>/>
     */
    public ArrayList<Animal> loadAnimals() {
        if (new File(filePath).isFile()) {
            PetRegistryIO<Animal> registryLoader = new PetRegistryIO<>(filePath);
            try {
                List<Animal> loadedAnimals = registryLoader.loadRegistry();
                return new ArrayList<>(loadedAnimals);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Не удалось загрузить данные.");
                e.printStackTrace();
            }
        } return new ArrayList<>();
    }

    /**
     * сохранение на диск коллекции животных
     * @param animals
     * @return результат выполнения: true - успешно, false - ошибка
     */
    public boolean saveAnimals(ArrayList<Animal>animals){
        PetRegistryIO<Animal> registryLoader = new PetRegistryIO<>(filePath);
        try{
            return saveAnimals(animals);
        }catch (IOException e){
            System.out.println("Не удалось сохранить данные.");
            e.printStackTrace();
            return false;
        }
    }
}
