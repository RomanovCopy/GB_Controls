package org.romanov.model;

import java.util.Date;

public class Animal {
    protected String name;
    protected Date dateOfBirth;
    protected private String species;
    protected private String gender;



    public Animal(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
}
