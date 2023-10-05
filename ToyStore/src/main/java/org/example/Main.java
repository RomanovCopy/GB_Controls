package org.example;

import org.example.base.Lot;
import org.example.store.Store;
import org.example.store.products.Toy;

public class Main {
    public static void main(String[] args) {
        //создание магазина и заполнение его товаром
        Store store=new Store();
        store.addLot(new Toy(store.createId(), "Игрушка 1", 1500.0, "Описание игрушки",
                Toy.Category.ACTION_FIGURES, Toy.AgeRestriction.AGE_6_8));
        store.addLot(new Toy(store.createId(), "Игрушка 2", 3500.0, "Описание игрушки",
                Toy.Category.BOARD_GAMES, Toy.AgeRestriction.AGE_9_PLUS));
        store.addLot(new Toy(store.createId(), "Игрушка 3", 1000.0, "Описание игрушки",
                Toy.Category.DOLLS, Toy.AgeRestriction.AGE_3_5));
        store.addLot(new Toy(store.createId(), "Игрушка 4", 3000.0, "Описание игрушки",
                Toy.Category.PUZZLES, Toy.AgeRestriction.AGE_9_PLUS));
        store.addLot(new Toy(store.createId(), "Игрушка 5", 15000.0, "Описание игрушки",
                Toy.Category.VEHICLES, Toy.AgeRestriction.AGE_6_8));
        //вывод списка товаров
        store.printProducts();
        //сохранение коллекции товаров на диск
        store.saveProducts();
        //очистка коллекции товаров
        store.clearProducts();
        //загрузка списка товаров с диска
        store.readProducrts();
        //вывод списка товаров
        store.printProducts();

    }
}