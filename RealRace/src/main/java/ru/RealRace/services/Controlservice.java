package ru.RealRace.services;


import ru.RealRace.properties.PropertiesManager;
import ru.RealRace.request.RequestParams;
import ru.RealRace.request.RequestHelper;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Controlservice {

    /** Номер gpio отвечающий за движение вперёд */
    private static final int MOVE_UP = 10;

    /** Номер gpio отвечающий за движение назад */
    private static final int MOVE_DOWN = 11;

    /** Номер gpio отвечающий за движение влево */
    private static final int MOVE_LEFT = 12;

    /** Номер gpio отвечающий за движение вправо */
    private static final int MOVE_RIGHT = 13;


    /** Выполняет команду движения объекта вперёд. */
    public static void moveUp() {
        move(MOVE_UP);
    }

    /** Выполняет команду движения объекта назад. */
    public static void moveDown() {
        move(MOVE_DOWN);
    }

    /** Выполняет команду движения объекта влево. */
    public static void moveLeft() {
        move(MOVE_LEFT);
    }

    /** Выполняет команду движения объекта вправо. */
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
     * Осуществляет команду управления.
     * @param param параметры запроса.
     */
    private static void request(RequestParams param){
        try {
            RequestHelper.requestGet(PropertiesManager.getPropertyValue("server.controller"), param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Задаёт определённое движение от нажатия определённой клавиши.
     * @param e нажатая клавиша.
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
