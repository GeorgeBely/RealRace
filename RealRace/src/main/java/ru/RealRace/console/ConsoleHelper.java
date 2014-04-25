package ru.RealRace.console;


import java.io.IOException;

public class ConsoleHelper {

    /**
     * ¬ыполн€ет консольную команду;
     * @param command командка консоли, которую необходимо выполнить.
     * @throws IOException ошибка при выполнении команды.
     */
    public static void useCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
