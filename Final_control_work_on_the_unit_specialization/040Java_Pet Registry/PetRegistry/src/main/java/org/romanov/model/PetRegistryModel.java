package org.romanov.model;

import org.romanov.utilities.PackageScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetRegistryModel {

    /**
     * множество доступных видов
     *
     * @return
     */
    public Set<String> getSpecies() {
        return species;
    }

    private Set<String> species;


    public PetRegistryModel() {
        species = findSpecies();
    }


    /**
     * поиск доступных видов
     *
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private Set<String> findSpecies() throws ClassNotFoundException, IOException {
        try {
            PackageScanner packageScanner = new PackageScanner();
            Set<String> list = new HashSet<>();
            if (packageScanner.getClasses() != null && !packageScanner.getClasses().isEmpty()) {
                for (Class<?> clazz : packageScanner.getClasses()) {
                    if (clazz != null) {
                        var array = clazz.getName().split('.');
                        String name = array[array.length - 1];
                        list.add();
                    }
                }
            }
            return list;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
