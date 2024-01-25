package org.romanov.model;

import org.romanov.utilities.PackageScanner;

import java.io.IOException;
import java.util.*;

public class PetRegistryModel {

    /**
     * множество доступных видов
     *
     * @return
     */
    private HashSet<String> species;
    /**
     * коллекция животных занесенных в реестр
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    private ArrayList<Animal>animals;


    /**
     * конструктор класса
     */
    public PetRegistryModel() {
        try {
            species = findSpecies();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        animals=new ArrayList<>();
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
     * получение названия вида по ключу(если ключ не существует - null)
     * @param index ключ вида
     * @return выбранный вид
     */
    public String getCurrentSpecies(Integer index){
        return availableAnimalSpecies(species).get(index);
    }

    /**
     * поиск доступных видов
     *
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private HashSet<String> findSpecies() throws ClassNotFoundException, IOException {
        try {
            PackageScanner packageScanner = new PackageScanner();
            HashSet<String> set = new HashSet<>();
            if (packageScanner.getClasses() != null && !packageScanner.getClasses().isEmpty()) {
                for (Class<?> clazz : packageScanner.getClasses()) {
                    if (clazz != null) {
                        var array = clazz.getName().split("\\.");
                        String name = array[array.length - 1];
                        set.add(name);
                    }
                }
            }
            return set;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * сортировка по дате рождения
     */
    public void sortAnimalsByBirthDate() {
        Collections.sort(animals, Comparator.comparing(Animal::getDateOfBirth));
    }

    /**
     * преобразуем множество в отсортированный и пронумерованный словарь видов
     * ( применяется для отображения в консоли  )
     * @param set исходное множество видов
     * @return отсортирова
     * */
    public HashMap<Integer, String> availableAnimalSpecies(HashSet<String> set){
        HashMap<Integer, String>map=new HashMap<>();
        // Преобразуем HashSet в TreeSet для сортировки элементов
        TreeSet<String> sortedSet = new TreeSet<>(set);
        // Используем итератор для пронумерованного вывода
        Iterator<String> iterator = sortedSet.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            map.put(index, iterator.next());
            index++;
        }
        return map;
    }

}
