package ru.RealRace.services;


import java.io.IOException;

public class ConsoleService {

    /**
     * ��������� ���������� �������;
     * @param command �������� �������, ������� ���������� ���������.
     * @throws IOException ������ ��� ���������� �������.
     */
    public static void useCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
