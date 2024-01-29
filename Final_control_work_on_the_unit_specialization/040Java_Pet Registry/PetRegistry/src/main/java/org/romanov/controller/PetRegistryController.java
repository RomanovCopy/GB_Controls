package org.romanov.controller;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.romanov.model.PetRegistryModel;
import org.romanov.view.PetRegistryView;

public class PetRegistryController implements Closeable {

    private PetRegistryModel model;


    public PetRegistryController() {
        model=new PetRegistryModel();
        model.displayMessage("Выберите номер пункта из меню: ");
        selectAction();
    }

    /**
     * обработка вводимых команд
     */
    public void selectAction() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            model.displayMenu();
            int choice = model.inputInt();
            switch (choice) {
                case 1:
                    model.createAnimal();
                    break;
                case 2:
                    displayAnimalCommands();
                    break;
                case 3:
                    trainNewCommand();
                    break;
                case 4:
                    model.displayAllAnimals();
                    break;
                case 5:
                    displayAnimalsByBirthDate();
                    break;
                case 6:
                    model.displayTotalAnimalCount();
                    break;
                case 0:
                    close();
                default:
                    model.displayMessage("Неверный выбор. Пожалуйста, введите корректный номер действия.");
            }
        }
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
        model.displayAllAnimals();
    }


    @Override
    public void close(){
        model.displayMessage("Программа завершена.");
        System.exit(0);
    }
}
