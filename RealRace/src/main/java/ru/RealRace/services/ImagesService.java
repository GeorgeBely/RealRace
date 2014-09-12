package ru.RealRace.services;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagesService {

    /** ������������ ����������� */
    private static final String LOCATION_IMAGES = "./RealRace/images";

    /** ������������ ������ ������ */
    private static final String LOCATION_KEYS = LOCATION_IMAGES + "/icons/keys";

    /** ������������ ����������� ������������ */
    private static final String LOCATION_BATTERY = LOCATION_IMAGES + "/battery";


    /**
     * ����� ������ �������.
     *
     * @param key ������������ �������.
     * @param pressed ������ �������. ���� true ������� ������.
     * @return ������ �������.
     */
    public static Image getKeyIcon(String key, boolean pressed) {
        String fileName = LOCATION_KEYS + "/key-" + key + (pressed ? "-press" : "") + ".png";
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @return �������� ������� ������������.
     */
    public static Image getBatteryEmptyImage() {
        try {
            return ImageIO.read(new File(LOCATION_BATTERY + "/battery_empty.png"));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @return �������� ����� ������.
     */
    public static Image getBatteryPowerLineImage() {
        try {
            return ImageIO.read(new File(LOCATION_BATTERY + "/power_line.png"));
        } catch (IOException e) {
            return null;
        }
    }
}
