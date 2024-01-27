package org.romanov.view;

import org.romanov.model.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PetRegistryView {

    private List<String>menu;

    public PetRegistryView() {
        menu = Arrays.asList(
                "1   Новое животное",
                "2   Доступные команды",
                "3   Добавить новую команду",
                "4   Все животные",
                "5   Сортировка по дате рождения",
                "6   Всего животных",
                "-------------------------------",
                "0   Выход"
        );
    }

    /**
     * вывод меню в консоль
     */
    public void displayMenu() {
        System.out.println("Основное меню.");
        for(String item:menu){
            System.out.println(item);
        }
    }

    /**
     * вывод доступных команд для заданного животного
     * @param animal животное
     */
    public void displayAnimalCommands(Animal animal) {
        if(animal.getExecutableCommands()==null ||
        animal.getExecutableCommands().isEmpty()){
            System.out.println("Доступных команд нет\n");
            return;
        }
        System.out.println("Список доступных команд\n");
        int index=1;
        for(String name:animal.getExecutableCommands().keySet()){
            System.out.println(index+"\t"+name+"\t"+animal.getExecutableCommands().
                    get(name)+"\t");
            index++;
        }
        System.out.println("\n");
    }

    /**
     * вывод сообщения
     * @param message текст сообщения(String)
     */
    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println("\n");
    }


    /**
     * вывод в консоль словаря HashMap<Integer, String>
     * @param map выводимый словарь
     */
    public void displayingNumberedList(HashMap<Integer, String>map){
        if(map!=null && !map.isEmpty()){
            for(int item: map){
                System.out.println(item+"   "+map.get(item));
            }
        }
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
        System.out.println("\n");
    }
}
