package org.romanov.model;

import org.romanov.Interfaces.ICreator;
import org.romanov.utilities.PetRegistryIO;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class PetRegistryModel {

    /**
     * коллекция доступных видов
     */
    public ArrayList<String>getSpecies{
        return species;
    }
    private ArrayList<String> species;

    /**
     * коллекция животных занесенных в реестр
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    private ArrayList<Animal>animals;

    private String filePath;


    /**
     * конструктор класса
     */
    public PetRegistryModel() {
        animals=loadAnimals();

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

    private HashMap<Integer, String> availableAnimalSpecies(){
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

    /**
     * загрузка с диска коллекции животных
     * @return ArrayList<Animal>/>
     */
    public ArrayList<Animal> loadAnimals() {
        if (new File(filePath).isFile()) {
            RegistryLoader<Animal> registryLoader = new RegistryLoader<>(filePath);
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
        RegistryLoader<Animal> registryLoader = new RegistryLoader<>(filePath);
        try{
            return saveAnimals(animals);
        }catch (IOException e){
            System.out.println("Не удалось сохранить данные.");
            e.printStackTrace();
            return false;
        }
    }
}
