package ru.RealRace.frames;

import ru.RealRace.camera.CameraHelper;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    /**
     * ������ ����
     */
    private static final int WIDTH = 900;

    /**
     * ������ ����
     */
    private static final int HEIGHT = 530;

    /**
     * ������������ ����
     */
    private static final String TITLE = "RealRace";

    /**
     * ������ �������� �����
     */
    private static final int VIDEO_WIDTH = 640;

    /**
     * ������ �������� �����
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
