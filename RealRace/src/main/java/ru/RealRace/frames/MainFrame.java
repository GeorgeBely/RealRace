package ru.RealRace.frames;

import ru.RealRace.camera.CameraHelper;
import ru.RealRace.control.ControlHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                ControlHelper.pressMoveKey(e);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

        JPanel panel = new JPanel(){{
            setFocusable(true);
        }};
        add(panel);

        Container video = new Container();
        video.setSize(VIDEO_WIDTH, VIDEO_HEIGHT);
        CameraHelper.startGstreamer(video);
        panel.add(video);

    }
}
