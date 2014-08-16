package ru.RealRace.properties;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    /**
     * ������������ ����� ��������.
     */
    private static final String APP_FILE_NAME = "application.properties";

    /**
     * ��������� ����������.
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
     * ���� ��������� �� �����������������, �������������� ��.
     * @return ��������� ����������.
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
     * ���������� �������� �������� �� ��� ������������.
     * @param property ������������ �������� �������� �������� ���� ��������.
     * @return �������� ��������.
     */
    public static String getPropertyValue(String property) {
        return getProperties().getProperty(property);
    }

}

