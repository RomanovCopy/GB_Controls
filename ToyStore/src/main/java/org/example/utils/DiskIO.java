package org.example.utils;

import org.example.interfaces.IDiskIO;

import java.io.*;

public class DiskIO<T> implements IDiskIO<T> {

    @Override
    public <T> void writeObjectToFile(T object, String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        }
    }

    @Override
    public <T> T readObjectFromFile(String filePath)throws IOException, ClassNotFoundException {
        T object;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = (T) objectInputStream.readObject();
        }
        return object;
    }

    @Override
    public void close() throws Exception {
    }
}
