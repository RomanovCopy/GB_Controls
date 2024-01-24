package org.romanov;

import org.romanov.utilities.PackageScanner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        try {
            PackageScanner packageScanner = new PackageScanner();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}