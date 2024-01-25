package org.romanov.view;

import org.romanov.model.Animal;

import java.util.List;

public class PetRegistryView {

    public PetRegistryView() {
    }

    /**
     * вывод меню в консоль
     */
    public void displayMenu() {

    }

    /**
     * вывод доступных команд для заданного животного
     * @param animal животное
     */
    public void displayAnimalCommands(Animal animal) {
        // Ваш код для вывода команд животного на консоль
    }

    /**
     * вывод сообщения
     * @param message текст сообщения(String)
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * табулированный вывод всего списка животных
     * @param animals список животных
     */
    public void displayAnimals(List<Animal> animals) {
        System.out.println("Список животных\n");
        // Вывод заголовков
        System.out.printf("%-15s %-15s %-15s %-5s%n", "Name", "Species", "Gender", "Date Of Birth");
        // Вывод элементов коллекции
        for (Animal animal : animals) {
            System.out.printf("%-15s %-15s %-15s %-5d%n",
                    animal.getName(), animal.getSpecies(), animal.getGender(), animal.getDateOfBirth());
        }
    }
}
