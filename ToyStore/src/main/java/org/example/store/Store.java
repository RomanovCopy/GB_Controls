package org.example.store;


import org.example.base.Lot;
import org.example.store.actions.Lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Store {

    /**
     * коллеция лотов, ассортимент магазина
     */
    private List<Lot> products;

    public Store() {
        products=new ArrayList<>();
    }

    /**
     * получение полной коллекции всех лотов в ассортименте
     * @return коллекция лотов(ArrayList)
     */
    public List<Lot> getProducts() {
        return products;
    }

    /**
     * добавление лота в ассортимент магазина
     * @param lot добавляемый лот
     */
    public void addLot(Lot lot){
        products.add(lot)
    }

    /**
     * удаление лота из ассортимента магазина
     * @param lot удаляемый лот
     */
    public void removeLot(Lot lot){
        if(products.contains(lot))
            products.remove(lot);
    }

    /**
     * Создание новой акции
     * @param action тип акции
     * @return экземпляр акции заданного типа
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T createAction(Class<T> action) throws IllegalAccessException,
            InstantiationException {
        return action.newInstance();
    }
}
