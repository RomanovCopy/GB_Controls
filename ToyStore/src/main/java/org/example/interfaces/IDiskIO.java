package org.example.interfaces;

import java.io.IOException;
import java.io.Serializable;

public interface IDiskIO<T>  extends Serializable {

    public <T> void writeObjectToFile(T object, String filePath)throws IOException;
    public <T> T readObjectFromFile(String filePath)throws IOException,ClassNotFoundException;
}
