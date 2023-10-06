package org.example.wrappers;

import org.example.base.Lot;

import java.io.Serializable;

public class LotteryLot implements Comparable<LotteryLot>, Serializable {
    protected int weight;
    protected Lot lot;


    public LotteryLot( Lot lot, int weight) {
        this.weight = weight;
        this.lot = lot;
    }


    public Lot getLot() {
        return lot;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(LotteryLot o) {
        return Integer.compareUnsigned(o.getWeight(), this.getWeight());
    }
}
