package org.example.base;


import java.io.Serializable;
import java.util.Date;

public class Lot implements Serializable {

    private final int id=idGenerate();
    private String name;
    private Double price;
    private String description;


    public Lot(String name, Double price, String description) {
        this.name=name;
        this.price=price;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }


    private int idGenerate(){
        Date currentDate = new Date();
        long timestamp = currentDate.getTime();
        return  (int) (timestamp / 1000);
    }




    @Override
    public String toString() {
        return super.toString();
    }

}
