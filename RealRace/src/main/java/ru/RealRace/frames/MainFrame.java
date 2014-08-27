package ru.RealRace.frames;

import ru.RealRace.services.CameraService;
import ru.RealRace.services.Controlservice;
import ru.RealRace.services.ImagesService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

    /** ������ ���� */
    private static final int WIDTH = 900;

    /** ������ ���� */
    private static final int HEIGHT = 535;

    /** ������������ ���� */
    private static final String TITLE = "RealRace";

    /** ������ �������� ����� */
    private static final int VIDEO_WIDTH = 640;

    /** ������ �������� ����� */
    private static final int VIDEO_HEIGHT = 480;

    /** ������������ ���������� � ������ ������� */
    private static final int LOCATION_BATTERY_WIDTH = 620;
    private static final int LOCATION_BATTERY_HEIGHT = 250;

    /** ���������� ������ */
    private static final int KEYS_WIDTH = 720;
    private static final int KEYS_HEIGHT = 450;

    /** ���������� ����� ��������� */
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

        JLabel labelLocationText = new JLabel("���������� ����������") {{
            setLocation(630, 10);
            setSize(200, 20);
        }};
        panel.add(labelLocationText);

        JLabel labelLatitude = new JLabel("������:") {{
            setLocation(620, 25);
            setSize(100, 20);
        }};
        panel.add(labelLatitude);

        JLabel labelLongitude = new JLabel("�������:") {{
            setLocation(750, 25);
            setSize(100, 20);
        }};
        panel.add(labelLongitude);

        JLabel labelThermometerText = new JLabel("�����������:") {{
            setLocation(620, 100);
            setSize(100, 20);
        }};
        panel.add(labelThermometerText);

        JLabel labelThermometerValue = new JLabel() {{
            setLocation(725, 100);
            setSize(100, 20);
        }};
        panel.add(labelThermometerValue);

        JLabel labelBatteryText = new JLabel("����� ������������:") {{
            setLocation(LOCATION_BATTERY_WIDTH, LOCATION_BATTERY_HEIGHT - 60);
            setSize(200, 20);
        }};
        panel.add(labelBatteryText);

        JLabel labelBatteryPower = new JLabel() {{
            setLocation(LOCATION_BATTERY_WIDTH + 160, LOCATION_BATTERY_HEIGHT - 60);
            setSize(100, 20);
        }};
        panel.add(labelBatteryPower);


        JLabel labelKey = new JLabel("���������� �������� ������������� � ������� ������:") {{
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

    /**
     * ������ ����� �������.
     *
     * @param powerEnergy % ������ �������.
     */
    public void drawBatteryPower(Integer powerEnergy) {
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(LOCATION_BATTERY_WIDTH+50, LOCATION_BATTERY_HEIGHT, 100, 20);
        g.setColor(Color.BLACK);
        g.drawRect(LOCATION_BATTERY_WIDTH+50, LOCATION_BATTERY_HEIGHT, 100, 20);
        g.setColor(Color.GREEN);
        g.fillRect(LOCATION_BATTERY_WIDTH+51, LOCATION_BATTERY_HEIGHT+1, powerEnergy-1, 20-1);

    }
}
