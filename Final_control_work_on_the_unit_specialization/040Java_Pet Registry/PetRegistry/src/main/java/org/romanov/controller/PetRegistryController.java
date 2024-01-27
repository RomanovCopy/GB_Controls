package org.romanov.controller;

import java.io.IOException;
import java.util.*;
import java.lang.reflect.Method;

import org.romanov.Interfaces.ICreator;
import org.romanov.model.Animal;
import org.romanov.model.PetRegistryModel;
import org.romanov.utilities.PetRegistryIO;
import org.romanov.view.PetRegistryView;

public class PetRegistryController {

    private PetRegistryModel model;
    private PetRegistryView view;

    private ArrayList<String>requestNewAnimal;
    private ArrayList<String>responseNewAnimal;

    /**
     * пронумерованный список имен видов
     */
    private HashMap<Integer, String>species;


    public PetRegistryController(PetRegistryModel model, PetRegistryView view) {
        this.model=model;
        this.view=view;
        species=new HashMap<>();
        for(Integer number:model.getSpecies().keySet()){
            species.put(number,model.getSpecies().get(number).getAnimalInfo());
        }
        view.displayMenu();
        view.displayMessage("Выберите номер пункта из меню: ");
        processUserInput();
    }

    /**
     * обработка вводимых команд
     */
    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем лишний перевод строки

            switch (choice) {
                case 1:
                    addNewAnimal();
                    break;
                case 2:
                    displayAnimalCommands();
                    break;
                case 3:
                    trainNewCommand();
                    break;
                case 4:
                    displayAnimalsByBirthDate();
                    break;
                case 5:
                    displayTotalAnimalCount();
                    break;
                case 0:
                    view.displayMessage("Программа завершена.");
                    System.exit(0);
                default:
                    view.displayMessage("Неверный выбор. Пожалуйста, введите корректный номер действия.");
            }
        }
    }

    /**
     * новое животное
     */
    private void addNewAnimal() {

        Animal animal=model.createAnimal()
    }

    /**
     * вывод доступных команд животного
     */
    private void displayAnimalCommands() {
        // Ваш код для отображения команд животного
    }

    /**
     * обучение новой команде
     */
    private void trainNewCommand() {
        // Ваш код для обучения новой команде животного
    }

    /**
     * вывод животных отсортированных по дате рождения
     */
    private void displayAnimalsByBirthDate() {
        model.sortAnimalsByBirthDate(); // Сортируем по дате рождения перед отображением
        view.displayAnimals(model.getAnimals());
    }

    /**
     * вывод общего числа животных
     */
    private void displayTotalAnimalCount() {
        view.displayMessage("Общее количество животных в реестре: " + model.getAnimals().size());
    }

    /**
     * сохранение на диск
     * @throws IOException
     */
    private void saveRegistry() throws IOException {
        PetRegistryIO<Animal> petRegistryIO = new PetRegistryIO<>("pet_registry.dat");
        petRegistryIO.saveRegistry(model.getAnimals());
    }

    /**
     * загрузка с диска
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void loadRegistry() throws IOException, ClassNotFoundException {
        PetRegistryIO<Animal> petRegistryIO = new PetRegistryIO<>("pet_registry.dat");
        List<Animal> loadedAnimals = petRegistryIO.loadRegistry();
        if (loadedAnimals != null) {
            model.getAnimals().addAll(loadedAnimals);
        }
    }

    /**
     * вывод в консоль всех доступных видов животных
     */
    private void printAvailableAnimalSpecies(){
        Class <?>clazz= ICreator.class;
        Method[]methods= clazz.getDeclaredMethods();
        int index=1;
        for (Method method : methods) {
            String methodName = method.getName();
            String modifiedMethodName = methodName.replace("Creator", "");
            modifiedMethodName = Character.toUpperCase(modifiedMethodName.charAt(0))
                    + modifiedMethodName.substring(1);
            System.out.println(index + "  " + modifiedMethodName);
            index++;
        }
    }

}
