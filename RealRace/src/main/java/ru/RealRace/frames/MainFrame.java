package ru.RealRace.frames;

import ru.RealRace.services.CameraService;
import ru.RealRace.services.Controlservice;
import ru.RealRace.services.ImagesService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    /** Ширина окна */
    private static final int WIDTH = 900;

    /** Высота окна */
    private static final int HEIGHT = 530;

    /** Наименование окна */
    private static final String TITLE = "RealRace";

    /** Ширина картинки видео */
    private static final int VIDEO_WIDTH = 640;

    /** Высота картинки видео */
    private static final int VIDEO_HEIGHT = 480;

    /** Координаты клавиш */
    private static final int KEYS_WIDTH = 700;
    private static final int KEYS_HEIGHT = 100;

    /** Расстояние между клавишами */
    private static final int DISTANCE_BETWEEN_KEY = 30;


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


        Container video = new Container();
        video.setSize(VIDEO_WIDTH, VIDEO_HEIGHT);
        CameraService.startGstreamer(video);
        panel.add(video);

        writeKeyImage(panel, "Up", false);
        writeKeyImage(panel, "Down", false);
        writeKeyImage(panel, "Left", false);
        writeKeyImage(panel, "Right", false);

    }

    private void writeKeyImage(JPanel panel, String key, boolean pressed) {
        if (key == null)
            return;

        int locationWidth = 0;
        int locationHeight = 0;
        if ("W".equals(key.toUpperCase()) || "Up".equals(key)) {
            if (!pressed)
                key = "Up";
            locationHeight = KEYS_HEIGHT - DISTANCE_BETWEEN_KEY;
            locationWidth = KEYS_WIDTH;
        } else if ("S".equals(key.toUpperCase()) || "Down".equals(key)) {
            if (!pressed)
                key = "Down";
            locationHeight = KEYS_HEIGHT;
            locationWidth = KEYS_WIDTH;
        } else if ("A".equals(key) || "Left".equals(key)) {
            if (!pressed)
                key = "Left";
            locationHeight = KEYS_HEIGHT;
            locationWidth = KEYS_WIDTH - DISTANCE_BETWEEN_KEY;
        } else if ("D".equals(key) || "Right".equals(key)) {
            if (!pressed)
                key = "Right";
            locationHeight = KEYS_HEIGHT;
            locationWidth = KEYS_WIDTH + DISTANCE_BETWEEN_KEY;
        }

        Image iconKey = ImagesService.getKeyIcon(key, pressed);
        if (iconKey != null)
            panel.getGraphics().drawImage(iconKey, locationWidth, locationHeight, null);
    }
}
