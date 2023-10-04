package org.example.store.actions;

import org.example.base.Lot;
import org.example.wrappers.LotteryLot;
import org.example.interfaces.ILottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Lottery implements ILottery {

    private PriorityQueue<LotteryLot>lotsToBePlayed;

    public Lottery() {
        lotsToBePlayed=new PriorityQueue<>();
    }

    @Override
    public void createLottery(ArrayList<Lot> lots){
        Scanner scanner=new Scanner(System.in);
        int weight=0;
        for(Lot lot:lots){
            System.out.println("Для отмены : -1");
            System.out.println("\n"+lot);
            System.out.print("Вес лота : ");
            if(scanner.hasNextInt())
                weight=scanner.nextInt();
            if(weight<0){
                scanner.close();
                return null;
            }
            addLotToLottery(lot, weight);
            System.out.println("Добавлено");
        }
        scanner.close();
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
