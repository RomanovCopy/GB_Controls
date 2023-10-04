package org.example.utils;

import org.example.interfaces.IDiskIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DiskIO implements IDiskIO {

    @Override
    public void writeObjectToFile(Serializable object, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(object);
            System.out.println("Объект успешно записан на диск.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи объекта на диск: " + e.getMessage());
        }
    }

    @Override
    public Serializable readObjectFromFile(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            T object = (T) objectIn.readObject();
            System.out.println("Объект успешно считан с диска.");
            return object;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при считывании объекта с диска: " + e.getMessage());
            return null;
        }
    }
}
