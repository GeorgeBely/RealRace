package ru.RealRace.frames;

import ru.RealRace.camera.CameraHelper;
import ru.RealRace.control.ControlHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    /**
     * Ширина окна
     */
    private static final int WIDTH = 900;

    /**
     * Высота окна
     */
    private static final int HEIGHT = 530;

    /**
     * Наименование окна
     */
    private static final String TITLE = "RealRace";

    /**
     * Ширина картинки видео
     */
    private static final int VIDEO_WIDTH = 640;

    /**
     * Высота картинки видео
     */
    private static final int VIDEO_HEIGHT = 480;


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
                ControlHelper.pressMoveKey(e);
                writeKeyImage(panel, e.getKeyChar(), true);
            }
            public void keyReleased(KeyEvent e) {
                writeKeyImage(panel, e.getKeyChar(), false);
            }
            public void keyTyped(KeyEvent e) {}
        });


        Container video = new Container();
        video.setSize(VIDEO_WIDTH, VIDEO_HEIGHT);
        CameraHelper.startGstreamer(video);
        panel.add(video);

        writeKeyImage(panel, 'W', false);
        writeKeyImage(panel, 'S', false);
        writeKeyImage(panel, 'A', false);
        writeKeyImage(panel, 'D', false);

    }

    private void writeKeyImage(JPanel panel, char key, boolean pressed) {
        int locationWidth = 0;
        int locationHeight = 0;
        if ('S' == key || 's' == key) {
            key = 'S';
            locationWidth = 700;
            locationHeight = 100;
        } else if ('W' == key || 'w' == key) {
            key = 'W';
            locationHeight = 70;
            locationWidth = 700;
        } else if ('A' == key || 'a' == key) {
            key = 'A';
            locationHeight = 100;
            locationWidth = 670;
        } else if ('D' == key || 'd' == key) {
            key = 'D';
            locationHeight = 100;
            locationWidth = 730;
        }

        try {
            String fileName = "./RealRace/images/keys/key-" + key + (pressed ? "-press" : "") + ".png";
            Image imW = ImageIO.read(new File(fileName));
            panel.getGraphics().drawImage(imW, locationWidth, locationHeight, null);
        } catch (IOException ignore) {}
    }
}
