package ru.RealRace.console;


import java.io.IOException;

public class ConsoleHelper {

    /**
     * ��������� ���������� �������;
     * @param command �������� �������, ������� ���������� ���������.
     * @throws IOException ������ ��� ���������� �������.
     */
    public static void useCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
