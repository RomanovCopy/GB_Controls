package org.example.interfaces;

import java.io.Serializable;

public interface IDiskIO <T extends Serializable> {

    void writeObjectToFile(T object, String filePath);
    T readObjectFromFile(String filePath);
}
