package ru.RealRace.services;


import ru.RealRace.properties.PropertiesManager;
import ru.RealRace.request.RequestParams;
import ru.RealRace.request.RequestHelper;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Controlservice {

    /** ����� gpio ���������� �� �������� ����� */
    private static final int MOVE_UP = 10;

    /** ����� gpio ���������� �� �������� ����� */
    private static final int MOVE_DOWN = 11;

    /** ����� gpio ���������� �� �������� ����� */
    private static final int MOVE_LEFT = 12;

    /** ����� gpio ���������� �� �������� ������ */
    private static final int MOVE_RIGHT = 13;


    /** ��������� ������� �������� ������� �����. */
    public static void moveUp() {
        move(MOVE_UP);
    }

    /** ��������� ������� �������� ������� �����. */
    public static void moveDown() {
        move(MOVE_DOWN);
    }

    /** ��������� ������� �������� ������� �����. */
    public static void moveLeft() {
        move(MOVE_LEFT);
    }

    /** ��������� ������� �������� ������� ������. */
    public static void moveRight() {
        move(MOVE_RIGHT);
    }

    private static void move(int gpio) {
        RequestParams param = new RequestParams();
        param.add("cd", "/");
        param.add("sh", "w.sh+" + gpio + "+1");
        request(param);
    }

    /**
     * ������������ ������� ����������.
     * @param param ��������� �������.
     */
    private static void request(RequestParams param){
        try {
            RequestHelper.requestGet(PropertiesManager.getPropertyValue("server.controller"), param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����� ����������� �������� �� ������� ����������� �������.
     * @param e ������� �������.
     */
    public static void pressMoveKey(KeyEvent e) {
        char key = e.getKeyChar();
        switch (key) {
            case 'w' : moveUp();
            case 's' : moveDown();
            case 'a' : moveLeft();
            case 'd' : moveRight();
        }
    }
}
