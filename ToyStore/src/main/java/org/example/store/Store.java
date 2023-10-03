package org.example.store;

import org.example.interfaces.ILottery;
import org.example.interfaces.IToy;

public class Store implements ILottery {
    @Override
    public void addToyToLottery(IToy toy, int weight) {

    }

    @Override
    public void removeToyFromLottery(IToy toy) {

    }

    @Override
    public IToy drawToy() {
        return null;
    }
}
