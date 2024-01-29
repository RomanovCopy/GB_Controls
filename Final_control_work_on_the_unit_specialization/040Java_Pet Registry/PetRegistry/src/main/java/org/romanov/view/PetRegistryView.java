package org.romanov.view;

import org.romanov.model.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PetRegistryView {


    public PetRegistryView() {
    }

    /**
     * вывод нумерованного списка в консоль
     */
    public void displayMenu(HashMap<Integer, String>menu) {
        for(Integer key:menu.keySet()){
            System.out.println(key+"  "+menu.get(key));
        }
    }


    /**
     * вывод сообщения
     * @param message текст сообщения(String)
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayAnimal(Animal animal) {
        // Вывод заголовков
        System.out.println("-".repeat(124));
        System.out.printf("%-5s ! %-15s ! %-15s ! %-15s ! %-20s ! %-15s ! %-15s !%n",
                "Id", "Name", "Species", "Gender", "Date Of Birth", "Weight", "Color");
        System.out.println("-".repeat(124));
        // Вывод данных о животном
        System.out.printf("%-5d ! %-15s ! %-15s ! %-15s ! %-20s ! %-15.2f ! %-15s !%n",
                animal.getId(), animal.getName(), animal.getSpecies(), animal.getGender(),
                animal.getDateOfBirth(), animal.getWeight(), animal.getColor());
    }




    /**
     * табулированный вывод всего списка животных
     * @param animals список животных
     */
    public void displayAnimals(List<Animal> animals) {
        int index =1;
        System.out.println("Список животных\n");
        // Вывод заголовков
        System.out.println("-".repeat(128));
        System.out.printf("%-5s ! %-5s ! %-15s ! %-15s ! %-15s ! %-20s ! %-15s ! %-15s !%n",
                "№", "Id", "Name", "Species", "Gender", "Date Of Birth", "Weight", "Color", "Id");
        System.out.println("-".repeat(128));
        // Вывод элементов коллекции
        for (Animal animal : animals) {
            System.out.printf("%-5d ! %-5d ! %-15s ! %-15s ! %-15s ! %-20s ! %-15.2f ! %-15s !%n",
                    index, animal.getId(), animal.getName(), animal.getSpecies(), animal.getGender(),
                    animal.getDateOfBirth(), animal.getWeight(), animal.getColor());
            index++;
        }
        System.out.println("\n");
    }
}
