package org.romanov.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Данный класс, предназначенный для сканирования классов в указанном пакете и получения их
 *   имен. Класс использует рефлексию и динамическую загрузку классов для получения
 *   информации о классах в заданном пакете во время выполнения программы.
 *   Он может быть использован для динамического обнаружения и работы с классами без их
 *   явного упоминания в исходном коде.
 */
public class PackageScanner {

    public List<Class<?>> getClasses() {
        return classes;
    }
    private List<Class<?>> classes;


    public PackageScanner() throws ClassNotFoundException, IOException {
        String packageName = "org.romanov.species";
        classes = null;
        try {
            classes = getClasses(packageName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
            if (directory.exists()) {
                classes.addAll(findClasses(directory, packageName));
            }
        }
        return classes;
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (!file.getName().contains(".")) {
                        classes.addAll(findClasses(file, packageName + "." + file.getName()));
                    }
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }

}
