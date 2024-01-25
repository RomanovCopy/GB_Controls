package org.romanov.model;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Animal {


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
    public void setDateOfBirth(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.dateOfBirth = LocalDateTime.parse(dateOfBirth, formatter);
    }
    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }
    protected LocalDateTime dateOfBirth;

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



    public Animal() {

    }




}
