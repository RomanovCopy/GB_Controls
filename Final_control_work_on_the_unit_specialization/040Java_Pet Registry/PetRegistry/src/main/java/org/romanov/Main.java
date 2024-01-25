package org.romanov;

import org.romanov.controller.PetRegistryController;
import org.romanov.model.PetRegistryModel;
import org.romanov.utilities.PackageScanner;
import org.romanov.view.PetRegistryView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        PetRegistryModel model = new PetRegistryModel();
        PetRegistryView view = new PetRegistryView();
        PetRegistryController controller = new PetRegistryController(model, view);

        controller.processUserInput();
    }
}