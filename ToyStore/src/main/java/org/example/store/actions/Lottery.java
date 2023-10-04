package org.example.store.actions;

import org.example.base.Lot;
import org.example.wrappers.LotteryLot;
import org.example.interfaces.ILottery;

import java.util.PriorityQueue;

public class Lottery implements ILottery {

    private PriorityQueue<LotteryLot>lotsToBePlayed;

    public Lottery() {
        lotsToBePlayed=new PriorityQueue<>();
    }


    @Override
    public void addLotToLottery(Lot lot, int weight) {
        lotsToBePlayed.add(new LotteryLot(lot,weight));
    }

    @Override
    public void removeLotFromLottery(Lot lot) {
        LotteryLot l=null;
        for(LotteryLot ll:lotsToBePlayed){
            if(l.equals(ll.getLot()))
                l=ll;
        }
        if (l!=null)
            lotsToBePlayed.remove(l);
    }

    @Override
    public Lot drawLot() {
        if (!lotsToBePlayed.isEmpty())
            return lotsToBePlayed.poll().getLot();
        else
            return null;
    }
}
