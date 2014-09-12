package ru.RealRace.frames;

import ru.RealRace.services.CameraService;
import ru.RealRace.services.Controlservice;
import ru.RealRace.services.ImagesService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

    /** Ширина окна */
    private static final int WIDTH = 900;

    /** Высота окна */
    private static final int HEIGHT = 535;

    /** Наименование окна */
    private static final String TITLE = "RealRace";

    /** Ширина картинки видео */
    private static final int VIDEO_WIDTH = 640;

    /** Высота картинки видео */
    private static final int VIDEO_HEIGHT = 480;

    /** Расположение информации о заряде батареи */
    private static final int LOCATION_BATTERY_WIDTH = 620;
    private static final int LOCATION_BATTERY_HEIGHT = 250;

    /** Координаты клавиш */
    private static final int LOCATION_KEYS_WIDTH = 720;
    private static final int LOCATION_KEYS_HEIGHT = 450;

    /** Координаты блока данных о расположении */
    private static final int LOCATION_COORDINATES_WIDTH = 620;
    private static final int LOCATION_COORDINATES_HEIGHT = 25;

    /** Координаты блока термометр */
    private static final int LOCATION_THERMOMETER_WIDTH = 620;
    private static final int LOCATION_THERMOMETER_HEIGHT = 100;

    /** Расстояние между клавишами */
    private static final int DISTANCE_BETWEEN_KEY = 30;

    /** Строка со значением заряда батареи */
    private JLabel labelBatteryPower;


    public MainFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel(){{
            setFocusable(true);
            setLayout(null);
            setBackground(Color.LIGHT_GRAY);
        }};
        add(panel);

        panel.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                Controlservice.pressMoveKey(e);
                writeKeyImage(panel, KeyEvent.getKeyText(e.getKeyCode()), true);
            }
            public void keyReleased(KeyEvent e) {
                writeKeyImage(panel, KeyEvent.getKeyText(e.getKeyCode()), false);
            }
            public void keyTyped(KeyEvent e) {}
        });

        JLabel labelLocationText = new JLabel("Координаты устройства") {{
            setLocation(LOCATION_COORDINATES_WIDTH + 10, LOCATION_COORDINATES_HEIGHT - 15);
            setSize(200, 20);
        }};
        panel.add(labelLocationText);

        JLabel labelLatitude = new JLabel("Широта:") {{
            setLocation(LOCATION_COORDINATES_WIDTH, LOCATION_COORDINATES_HEIGHT);
            setSize(100, 20);
        }};
        panel.add(labelLatitude);

        JLabel labelLongitude = new JLabel("Долгота:") {{
            setLocation(LOCATION_COORDINATES_WIDTH + 130, LOCATION_COORDINATES_HEIGHT);
            setSize(100, 20);
        }};
        panel.add(labelLongitude);

        JLabel labelThermometerText = new JLabel("Температура:") {{
            setLocation(LOCATION_THERMOMETER_WIDTH, LOCATION_THERMOMETER_HEIGHT);
            setSize(100, 20);
        }};
        panel.add(labelThermometerText);

        JLabel labelThermometerValue = new JLabel() {{
            setLocation(LOCATION_THERMOMETER_HEIGHT + 105, LOCATION_THERMOMETER_HEIGHT);
            setSize(100, 20);
        }};
        panel.add(labelThermometerValue);

        JLabel labelBatteryText = new JLabel("Заряд аккамулятора:") {{
            setLocation(LOCATION_BATTERY_WIDTH, LOCATION_BATTERY_HEIGHT - 60);
            setSize(200, 20);
        }};
        panel.add(labelBatteryText);

        labelBatteryPower = new JLabel() {{
            setLocation(LOCATION_BATTERY_WIDTH + 160, LOCATION_BATTERY_HEIGHT - 60);
            setSize(100, 20);
        }};
        panel.add(labelBatteryPower);
        drawBatteryPower(panel, 0);

        JLabel labelKey = new JLabel("Управление объектом осуществяется с помощью клавиш:") {{
            setLocation(620, 370);
            setSize(300, 20);
        }};
        panel.add(labelKey);

        writeKeyImage(panel, "Up", false);
        writeKeyImage(panel, "Down", false);
        writeKeyImage(panel, "Left", false);
        writeKeyImage(panel, "Right", false);


        Container video = new Container();
        video.setSize(VIDEO_WIDTH, VIDEO_HEIGHT);
        video.setLocation(-15, 5);
        CameraService.startGstreamer(video);
        panel.add(video);

    }

    private void writeKeyImage(JPanel panel, String key, boolean pressed) {
        if (key == null)
            return;

        int locationWidth = 0;
        int locationHeight = 0;
        if ("W".equals(key.toUpperCase()) || "Up".equals(key)) {
            if (!pressed)
                key = "Up";
            locationHeight = LOCATION_KEYS_HEIGHT - DISTANCE_BETWEEN_KEY;
            locationWidth = LOCATION_KEYS_WIDTH;
        } else if ("S".equals(key.toUpperCase()) || "Down".equals(key)) {
            if (!pressed)
                key = "Down";
            locationHeight = LOCATION_KEYS_HEIGHT;
            locationWidth = LOCATION_KEYS_WIDTH;
        } else if ("A".equals(key) || "Left".equals(key)) {
            if (!pressed)
                key = "Left";
            locationHeight = LOCATION_KEYS_HEIGHT;
            locationWidth = LOCATION_KEYS_WIDTH - DISTANCE_BETWEEN_KEY;
        } else if ("D".equals(key) || "Right".equals(key)) {
            if (!pressed)
                key = "Right";
            locationHeight = LOCATION_KEYS_HEIGHT;
            locationWidth = LOCATION_KEYS_WIDTH + DISTANCE_BETWEEN_KEY;
        }

        Image iconKey = ImagesService.getKeyIcon(key, pressed);
        if (iconKey != null)
            panel.getGraphics().drawImage(iconKey, locationWidth, locationHeight, null);
    }

    /**
     * Рисует заряд батареи.
     *
     * @param powerEnergy % заряда батареи.
     */
    public void drawBatteryPower(JPanel panel, Integer powerEnergy) {
        if (powerEnergy == null)
            return;
        if (powerEnergy > 100)
            powerEnergy = 100;

        labelBatteryPower.setText(powerEnergy.toString() + "%");

        Image batteryEmpty = ImagesService.getBatteryEmptyImage();
        if (batteryEmpty != null)
            panel.getGraphics().drawImage(batteryEmpty, LOCATION_BATTERY_WIDTH, LOCATION_BATTERY_HEIGHT - 37, null);

        Image batteryPowerLine = ImagesService.getBatteryPowerLineImage();
        if (batteryPowerLine != null) {
            for (int i = 0; i < powerEnergy; i++)
                panel.getGraphics().drawImage(batteryPowerLine, LOCATION_BATTERY_WIDTH + 13 + i, LOCATION_BATTERY_HEIGHT - 37, null);
        }
    }
}
