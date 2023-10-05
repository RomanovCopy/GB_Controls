package org.example.interfaces;

import org.example.base.Lot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public interface ILottery extends Serializable {

    /**
     * создание лотереи из коллекции лотов
     * @param lots коллекция лотов
     */
    public void createLottery(ArrayList<Lot> lots);

    /**
     * Метод для добавления товара в лотерею с указанием ее веса
     * @param lot товар для добавления
     * @param weight Вес товара
     */
    void addLotToLottery(Lot lot, int weight);

    /**
     * Метод для удаления товара из лотереи
     * @param lot товара для удаления
     */
    void removeLotFromLottery(Lot lot);

    /**
     * Метод для проведения розыгрыша и получения случайного товара
     * @return Случайный товар из лотереи
     */
    Lot drawLot();
}
