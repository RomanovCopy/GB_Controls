package org.romanov.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Animal implements Serializable {


    /**
     * идентификатор животного
     * @param id
     */
    public void setId(Integer id){this.id=id;}
    public int getId(){return id;}
    protected int id;

    /**
     * кличка животного
     * @param name кличка
     */
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    protected String name;


    /**
     * дата рождения животного
     * @param dateOfBirth дата рождения в формате YY-MM-DD
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    protected LocalDate dateOfBirth;

    /**
     * вид
     * @param species
     */
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getSpecies() {
        return species;
    }
    protected String species;

    /**
     * пол животного
     * @param gender пол животного( самец, самка )
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    protected String gender;

    /**
     * окрас животного
     * @param color описание окраса
     */
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    protected String color;

    /**
     * вес животного
     * @param weight вес в килограммах
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    protected double weight;

    /**
     * добавление команды
     * @param name имя команды
     * @param description описание команды
     * @return результат добавления: true - успешно, false - ошибка
     */
    public boolean addExecutableCommand(String name, String description){
        if(executableCommands==null){
            executableCommands=new HashMap<>();
        }
        if(executableCommands.keySet().contains(name)){
            System.out.println("Такая команда уже существует.\nОтказ в добавлении.\n");
            return false;
        }
        executableCommands.put(name, description);
        return true;
    }

    /**
     * полный список команд
     * @return словарь HashMap<String,String>именами и описаниями команд/>
     */
    public HashMap<String, String>getExecutableCommands(){
        return executableCommands;
    }
    protected HashMap<String, String> executableCommands;



    public Animal() {
        executableCommands=new HashMap<>();
    }





}
