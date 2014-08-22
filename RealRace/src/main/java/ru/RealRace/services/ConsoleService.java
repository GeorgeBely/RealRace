package ru.RealRace.services;


import java.io.IOException;

public class ConsoleService {

    /**
     * ¬ыполн€ет консольную команду;
     * @param command командка консоли, которую необходимо выполнить.
     * @throws IOException ошибка при выполнении команды.
     */
    public static void useCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
