package ru.RealRace.services;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagesService {

    /** –асположение изображений */
    private static final String LOCATION_IMAGES = "./RealRace/images";

    /** –асположение иконок кнопок */
    private static final String LOCATION_KEYS = LOCATION_IMAGES + "/icons/keys";

    /** –асположение изображений аккамул€тора */
    private static final String LOCATION_BATTERY = LOCATION_IMAGES + "/battery";


    /**
     * ¬ыбор иконки клавиши.
     *
     * @param key наименование клавиши.
     * @param pressed статус нажати€. ≈сли true клавиши зажата.
     * @return иконку клавиши.
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
     * @return картинку пустого аккамул€тора.
     */
    public static Image getBatteryEmptyImage() {
        try {
            return ImageIO.read(new File(LOCATION_BATTERY + "/battery_empty.png"));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @return картинку линии зар€да.
     */
    public static Image getBatteryPowerLineImage() {
        try {
            return ImageIO.read(new File(LOCATION_BATTERY + "/power_line.png"));
        } catch (IOException e) {
            return null;
        }
    }
}
