package ru.RealRace.properties;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    /**
     * Наименование файла настроик.
     */
    private static final String APP_FILE_NAME = "application.properties";

    /**
     * Настройки приложения.
     */
    private static Properties properties = null;


    private static void initProperties() {
        try {
            String appFilePath = new File(".").getCanonicalPath() + System.getProperty("file.separator") + APP_FILE_NAME;
            properties.load(new FileInputStream(appFilePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
        }
    }

    /**
     * Если настройки не инициализированны, инициализирует их.
     * @return настройки приложения.
     */
    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            initProperties();
            return properties;
        } else {
            return properties;
        }
    }

    /**
     * Возвращает значение свойства по его наименованию.
     * @param property наименование свойства значение которого надо получить.
     * @return значение свойства.
     */
    public static String getPropertyValue(String property) {
        return getProperties().getProperty(property);
    }

}

