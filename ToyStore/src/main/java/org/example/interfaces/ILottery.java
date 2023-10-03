package org.example.interfaces;

public interface ILottery {
    /**
     * Метод для добавления игрушки в лотерею с указанием ее веса
     * @param toy Игрушка для добавления
     * @param weight Вес игрушки
     */
    void addToyToLottery(IToy toy, int weight);

    /**
     * Метод для удаления игрушки из лотереи
     * @param toy Игрушка для удаления
     */
    void removeToyFromLottery(IToy toy);

    /**
     * Метод для проведения розыгрыша и получения случайной игрушки
     * @return Случайная игрушка из лотереи
     */
    IToy drawToy();
}
