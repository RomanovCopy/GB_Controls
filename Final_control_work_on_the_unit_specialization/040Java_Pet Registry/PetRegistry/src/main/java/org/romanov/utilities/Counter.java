package org.romanov.utilities;

import org.romanov.model.Animal;
import org.romanov.model.PetRegistryModel;

import java.lang.AutoCloseable;

public class Counter implements AutoCloseable{
    private int count;
    private boolean used;
    private boolean closed;

    private PetRegistryModel model;

    public Counter(PetRegistryModel model){
        this.model=model;
        count=model.getAnimals().size();
        used=false;
        closed=false;
    }

    public void add() { count++; generateId();}
    public int getId(){return generateId();}
    public int getCount() {return count;}
    public boolean getClosed(){return closed;}
    public void markAsUsed() { used = true; closed=true;}

    /**
     * генерация уникального Id
     * @return
     */
    private int generateId(){
        int current=1;
        boolean ident=true;
        while (ident && !model.getAnimals().isEmpty()){
            for(Animal animal: model.getAnimals()){
                if(animal.getId()==current){ current++; break; }
                else { ident=false; }
            }
        }
        return current;
    }



    @Override
    public void close() throws ResourceCloseException {
        if (!used || !closed) {
            throw new ResourceCloseException("Ресурс не использовался в блоке try-with-resources.");
        }
    }

}
