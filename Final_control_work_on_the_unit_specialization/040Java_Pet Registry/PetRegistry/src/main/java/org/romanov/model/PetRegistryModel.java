package org.romanov.model;

import org.romanov.Interfaces.ICreator;
import org.romanov.factory.Creator;
import org.romanov.utilities.Counter;
import org.romanov.utilities.PetRegistryIO;
import org.romanov.utilities.ResourceCloseException;
import org.romanov.view.PetRegistryView;

import javax.swing.text.View;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PetRegistryModel {

    private PetRegistryView view;


    private HashMap<Integer, String>menu;

    /**
     * коллекция животных занесенных в реестр
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    private ArrayList<Animal>animals;


    public String getFilePath(){
        return filePath;
    }
    private String filePath;


    private Creator creator;


    public HashMap<Integer, ICreator> getSpeciesCreators(){
        return creator.getSpecies();
    }


    /**
     * выды для отображения
     * @return
     */
    public HashMap<Integer, String>getSpecies(){
        return species;
    }
    private HashMap<Integer,String>species;


    /**
     * выбранное животное
     * @param currentAnimal
     */
    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }
    public Animal getCurrentAnimal() {
        return currentAnimal;
    }
    private Animal currentAnimal;
    private Animal newAnimal;



    /**
     * конструктор класса
     */
    public PetRegistryModel() {
        view=new PetRegistryView();
        filePath="pet_regisrtry.dat";
        animals=loadAnimals();
        creator=new Creator();
        createMenu();
        createSpecies();
        newAnimal=null;
    }

    /**
     * формирование меню
     */
    private void createMenu(){
        menu=new HashMap<>();
        menu.put(1, "Новое животное");
        menu.put(2, "Доступные команды");
        menu.put(3, "Добавить новую команду");
        menu.put(4, "Все животные");
        menu.put(5, "Сортировка по дате рождения");
        menu.put(6, "Всего животных");
        menu.put(7, "Сохранить реестр на диск");
        menu.put(0, "Выход");
    }

    /**
     * формирование видов
     */
    private void createSpecies(){
        species=new HashMap<>();
        for(Integer number:getSpeciesCreators().keySet()){
            species.put(number,getSpeciesCreators().get(number).getAnimalInfo());
        }
    }



    /**
     * добавление животного в реестр
     * @param animal добавляемое животное
     * @return результат выполнения( True - успешно, False - ошибка )
     */
    public boolean addAnimal(Animal animal){
        if(animal instanceof Animal && animals!=null){
            try{
                Counter counter=new Counter(this);
                animal.setId(counter.getId());
                counter.add();
                counter.markAsUsed();
                counter.close();
            }catch (ResourceCloseException e){
                System.out.println("Ошибка при обращении к внутреннему счетчику.");
                return false;
            }
            displayMessage("Добавлено новое животное: ");
            displayAnimal(animal);
            displayMessage("Сохранить? 1 - Да\n0 - Нет");
            int select=inputInt();
            if(select==1){
                animals.add(animal);
            }
            return true;
        }
        return false;
    }

    /**
     * создание экземпляра вида животного
     * @param key индекс вида
     * @return Animal
     */
    public Animal createAnimal(){

        Animal animal = selectSpecies();
        if(animal!=null){
            displayMessage("Теперь, некоторые уточнения: ");
            displayMessage("Дата рождения: ");
            animal.setDateOfBirth(inputDate());
            animal = selectName(animal);
            animal=selectGender(animal);
            animal=selectWeight(animal);
            animal=selectColor(animal);
            if(addAnimal(animal)){
                displayMessage("Животное успешно сохранено.");
            }else {
                displayMessage("Сохранение не выполнено.");
            }
        }
        return animal;
    }

    /**
     * вывод доступных команд животного
     */
    public Animal displayAnimalCommands() {
        displayAllAnimals();
        displayMessage("Введите Id интересующего вас животного:");
        int id=inputInt();
        Animal animal=null;
        for(var item:animals){
            if(item.getId()==id){
                animal=item;
                break;
            }
        }
        if(animal!=null){
            if(animal.getExecutableCommands().isEmpty()){
                displayMessage("Список команд пуст.");
            }else {
                displayMessage("Доступные команды:");
                for(String name: animal.getExecutableCommands().keySet()){
                    displayMessage(name + "  " + animal.getExecutableCommands().get(name));
                }
            }
        }else {
            displayMessage("Животного с таким Id не найдено.");
        }
        return animal;
    }

    /**
     * обучение новой команде
     */
    public void trainNewCommand() {
        Animal animal = displayAnimalCommands();
        String nameCommand=null;
        String command=null;
        if(animal!=null){
            displayMessage("Наименование новой команды:");
            nameCommand = inputString();
            displayMessage("Описание новой команды:");
            command = inputString();
            if(nameCommand!=null&&nameCommand.length()>3 && command!=null && command.length()>3){
                animal.addExecutableCommand(nameCommand, command);
                displayMessage("Команда добавлена.");
            }
        }
    }




    /**
     * выбор вида животного
     */
    private Animal selectSpecies(){
        displayMessage("Выберите вид животного");
        displaySpecies();
        Animal animal = creator.createAnimal(inputInt());
        displayMessage("Выбрано - " + animal.getSpecies());
        return animal;
    }

    /**
     * выбор имени
     */
    private Animal selectName(Animal animal){
        String name=null;
        while (name==null || name.length()<3){
            displayMessage("Выберите имя животного(более 2 символов)");
            name=inputString();
        }
        displayMessage("Выбрано - " + name);
        animal.setName(name);
        return animal;
    }

    /**
     * выбор гендерной принадлежности
     */
    private Animal selectGender(Animal animal){
        String gender=null;
        while(gender==null){
            displayMessage("Принадлежность к гендеру\n 1 - Male(самец)\n2 - Female(самка)");
            int n =inputInt();
            gender=n==1?"Male":n==2?"Female":null;
        }
        animal.setGender(gender);
        return animal;
    }

    /**
     * вес животного
     * @return
     */
    private Animal selectWeight(Animal animal){
        Double weight = 0.0;
        while(weight==0){
            displayMessage("Вес животного (в формате dd,dd): ");
            weight=inputDouble();
        }
        animal.setWeight(weight);
        return animal;
    }

    /**
     * выбор окраса
     */
    private Animal selectColor(Animal animal){
        String color=null;
        while(color==null || color.length()<3){
            displayMessage("Окрас животного: ");
            color=inputString();
        }
        animal.setColor(color);
        return animal;
    }


    /**
     * сортировка по дате рождения
     */
    public void sortAnimalsByBirthDate() {
        Collections.sort(animals, Comparator.comparing(Animal::getDateOfBirth));
    }

    private boolean ValidateNewAnimal(Animal animal) {
        boolean validate1 = animal.getColor() != null;
        boolean validate2 = animal.getDateOfBirth() != null;
        boolean validate3 = animal.getName() != null;
        boolean validate4 = animal.getGender() != null;
        boolean validate5 = animal.getSpecies() != null;
        boolean validate6 = animal.getWeight() > 0;
        return validate1 && validate2 && validate3 && validate4 && validate5 && validate6;
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
            registryLoader.saveRegistry(animals);
            return true;
        }catch (IOException e){
            System.out.println("Не удалось сохранить данные.");
            e.printStackTrace();
            return false;
        }
    }



    /**
     * ввод int в консоль
     * @return
     */
    public int inputInt(){
        Scanner scanner=new Scanner(System.in);
        int choice=-1;
        if(scanner.hasNextInt()){
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /**
     * ввод Double в консоль
     * @return
     */
    public Double inputDouble(){
        Scanner scanner=new Scanner(System.in);
        Double choice=0.0;
        if(scanner.hasNextDouble()){
            choice=scanner.nextDouble();
            scanner.nextLine();
        }
        return choice;
    }

    /**
     * ввод String в консоль
     * @return
     */
    public String inputString(){
        Scanner scanner=new Scanner(System.in);
        String line=null;
        if(scanner.hasNextLine()){
            line= scanner.nextLine().trim();
        }
        return line;
    }

    /**
     * ввод даты в формате строки yyyy-MM-dd и преобразование ее в LocalDate
     * @return
     */
    public LocalDate inputDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        while (date==null){
            System.out.print("Введите дату в формате год-месяц-день (например, 2024-01-28): ");
            String dateString = scanner.nextLine();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                date = LocalDate.parse(dateString, dateFormatter);
                System.out.println("Вы ввели дату: " + date);
            } catch (Exception e) {
                System.out.println("Ошибка при парсинге даты. Убедитесь, что введенная дата соответствует формату.");
            }
        }
        return date;
    }


    /**
     * вывод меню
     */
    public void displayMenu( ){
        view.displayMenu(menu);
    }

    /**
     * вывод видов
     */
    public void displaySpecies(){
        view.displayMenu(species);
    }

    /**
     * вывод сообщения
     * @param line
     */
    public void displayMessage(String line){
        view.displayMessage(line);
    }

    /**
     * вывод отдельного животного
     * @param animal
     */
    public void displayAnimal(Animal animal){
        view.displayAnimal(animal);
    }

    /**
     * вывод всех животных
     * @param animals
     */
    public void displayAllAnimals(){
        view.displayAnimals(animals);
    }

    /**
     * вывод общего колличества животных
     */
    public void displayTotalAnimalCount(){
        try{
            Counter counter=new Counter(this);
            displayMessage("Животных в реестре: "+ counter.getCount());
            counter.markAsUsed();
            counter.close();
        }catch (ResourceCloseException e){
            System.out.println("Ошибка при обращении к внутреннему счетчику.");
        }
    }


}
