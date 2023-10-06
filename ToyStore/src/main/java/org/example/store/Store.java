package org.example.store;


import org.example.base.Lot;
import org.example.interfaces.ILottery;
import org.example.store.actions.Lottery;
import org.example.utils.DiskIO;

import java.io.IOException;
import java.util.*;

public class Store {

    /**
     * коллеция лотов, ассортимент магазина
     */
    private List<Lot> products;
    private  List<Lottery>lotteries;

    public Store() {
        products = new ArrayList<>();
        lotteries=new ArrayList<>();
    }

    /**
     * получение полной коллекции всех лотов в ассортименте
     *
     * @return коллекция лотов(ArrayList)
     */
    public List<Lot> getProducts() {
        return products;
    }

    /**
     * получение полной коллекции всех лотов в ассортименте
     *
     * @return коллекция лотов(ArrayList)
     */
    public List<Lottery> getLotteries() {
        return lotteries;
    }

    /**
     * добавление лотереи в коллекцию лотерей
     * @param lottery лотерея
     */
    public void addLottery(Lottery lottery){
        if(lottery!=null){
            lotteries.add(lottery);
        }
    }

    /**
     * добавление лота в ассортимент магазина
     *
     * @param lot добавляемый лот
     */
    public void addLot(Lot lot) {
        products.add(lot);
    }

    /**
     * удаление лота из ассортимента магазина
     *
     * @param lot удаляемый лот
     */
    public void removeLot(Lot lot) {
        if (products.contains(lot)) products.remove(lot);
    }

    /**
     * очистка коллекции товаров
     */
    public void clearProducts(){
        if(products==null)
            products=new ArrayList<>();
        else
            products.clear();
    }

    /**
     * удаление лотереи из коллекции
     * @param lottery удаляемая лотерея
     */
    public void removeLottery(Lottery lottery){
        if(lotteries!=null && lotteries.contains(lottery)){
            lotteries.remove(lottery);
        }
    }

    /**
     * очистка коллекции лотерей
     */
    public void clearLotteries(){
        if(lotteries==null)
            lotteries=new ArrayList<>();
        else
            lotteries.clear();
    }


    /**
     * сохранение текущей коллекции товаров на диск
     */
    public void saveProducts(String pathToFile) {
        try (DiskIO<ArrayList<Lot>> disk = new DiskIO<>()) {
            disk.writeObjectToFile(products, pathToFile);
            System.out.println("Данные успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить коллекцию товаров магазина. ( " + e.getMessage() + " )");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! ( " + e.getMessage() + " )");
        }
    }


    /**
     * заполнение коллекции товаров с диска
     */
    public void readProducrts(String pathToFile) {
        try (DiskIO<ArrayList<Lot>> disk = new DiskIO<>()) {
            products = (ArrayList<Lot>) disk.readObjectFromFile(pathToFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Коллекция товаров магазина не найдена. (" + " " + e.getMessage() + " )");
        } catch (IOException e) {
            System.out.println("Не удалось считать коллекцию товаров магазина. (" + " " + e.getMessage() + " )");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! ( " + e.getMessage() + " )");
        }
    }

    /**
     * вывод в консоль коллекции товаров
     */
    public void printProducts(){
        if(products==null){
            products=new ArrayList<>();
            System.out.println("Коллекция товаров пуста.");
        }else {
            if(products.isEmpty()){
                System.out.println("Коллекция товаров пуста.");
            }else {
                for(Lot lot:products){
                    System.out.println(lot.toString());
                }
            }
        }
    }

    /**
     * сохранение коллекции лотерей на диск
     */
    public void saveLottery(String pathToFile) {
        try (DiskIO<ArrayList<Lottery>> disk = new DiskIO<>()) {
            disk.writeObjectToFile(lotteries, pathToFile);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить коллекцию лотерей магазина. ( " + e.getMessage() + " )");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! ( " + e.getMessage() + " )");
        }
    }

    /**
     * заполнение коллекции лотерей с диска
     */
    public void readLottery(String pathToFile) {
        try (DiskIO<ArrayList<Lottery>> disk = new DiskIO<>()) {
            lotteries = (ArrayList<Lottery>) disk.readObjectFromFile(pathToFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Коллекция лотерей не найдена. (" + " " + e.getMessage() + " )");
        } catch (IOException e) {
            System.out.println("Не удалось считать коллекцию лотерей. (" + " " + e.getMessage() + " )");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! ( " + e.getMessage() + " )");
        }
    }


    /**
     * Генерация уникального ID на основе текущей даты и коллекции products
     * @return уникальный Id
     */
    public int createId() {
        Date currentDate = new Date();
        long timestamp = currentDate.getTime();
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        int[] uniqueIdHolder = new int[]{(int) (timestamp + randomNumber)};
        Optional<Lot> foundProduct = products.stream().filter(product -> product.getId() == uniqueIdHolder[0]).findFirst();
        while (foundProduct.isPresent()) {
            randomNumber = random.nextInt(1000);
            uniqueIdHolder[0] = (int) (timestamp + randomNumber);
            foundProduct = products.stream().filter(product -> product.getId() == uniqueIdHolder[0]).findFirst();
        }
        return uniqueIdHolder[0];
    }}
