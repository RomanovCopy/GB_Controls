package org.example;

import org.example.base.Lot;
import org.example.store.Store;
import org.example.store.actions.Lottery;
import org.example.store.products.Toy;
import org.example.wrappers.LotteryLot;

import java.util.Random;

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
        store.saveProducts("StoreProducts.dat");
        //очистка коллекции товаров
        store.clearProducts();
        //загрузка списка товаров с диска
        store.readProducrts("StoreProducts.dat");
        //вывод списка товаров
        store.printProducts();
        //создание лотереи
        Lottery lottery=new Lottery();
        //добавление товаров в розыгрыш с рандомными весами
        Random random=new Random();
        int weight=0;
        for(int i=0;i<store.getProducts().size();i++){
            weight=random.nextInt(100);
            lottery.addLotToLottery(store.getProducts().get(i),weight);
        }
        //добавляе лотерею в коллекцию лотерей
        store.addLottery(lottery);
        //сохраняем коллекцию лотерей на диск
        store.saveLottery("StoreLotteries.dat");
        //очищаем коллекцию лотерей
        store.clearLotteries();
        //загружаем коллекцию лотерей с диска
        store.readLottery("StoreLotteries.dat");
        //получаем последнюю лотерею для проведения розыгрыша
        lottery=store.getLotteries().get(store.getLotteries().size()-1);
        //выводим список товаров в лотерее
        System.out.println("Товары доступные в лотерее:");
        System.out.println(lottery.toString());

        //проводим розыгрыш первого приза
        System.out.println("Первый приз :");
        Toy toy_1=(Toy) lottery.drawLot();
        System.out.println(toy_1.toString());
        //проводим розыгрыш второго приза
        System.out.println("Второй приз :");
        Toy toy_2=(Toy) lottery.drawLot();
        System.out.println(toy_2.toString());
        //проводим розыгрыш третьего приза
        System.out.println("Третий приз :");
        Toy toy_3=(Toy) lottery.drawLot();
        System.out.println(toy_3.toString());
        //проводим розыгрыш четвертого приза
        System.out.println("Четвертый приз :");
        Toy toy_4=(Toy) lottery.drawLot();
        System.out.println(toy_4.toString());
        //проводим розыгрыш пятого приза
        System.out.println("Пятый приз :");
        Toy toy_5=(Toy) lottery.drawLot();
        System.out.println(toy_5.toString());


    }
}