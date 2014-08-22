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
}
