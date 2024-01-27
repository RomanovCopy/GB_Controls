package org.romanov.utilities;

import org.romanov.model.Animal;

import java.io.*;
import java.util.List;

public class PetRegistryIO <T extends Animal>{

    private String filePath;

    public PetRegistryIO(String filePath) {
        this.filePath = filePath;
    }

    /**
     * сохранение данных реестра на диск
     * @param animals коллекция животных
     * @throws IOException
     */
    public void saveRegistry(List<T> animals) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(animals);
            System.out.println("Реестр успешно сохранен на диск.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении реестра на диск: " + e.getMessage());
            throw e; // Перебрасываем исключение в вызывающий метод
        }
    }

    /**
     * считывание реестра с диска
     * @return коллекция животных
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<T> loadRegistry() throws IOException, ClassNotFoundException {
        List<T> animals = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            animals = (List<T>) ois.readObject();
            System.out.println("Реестр успешно загружен с диска.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке реестра с диска: " + e.getMessage());
            throw e; // Перебрасываем исключение в вызывающий метод
        }
        return animals;
    }
}
