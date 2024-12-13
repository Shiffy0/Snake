package org.example.ssnake;

/**
 * Главный класс, точка входа в приложение.
 * Этот класс отвечает за запуск меню игры Snake.
 */
public class Run {

    /**
     * Точка входа в программу.
     * Этот метод запускает приложение, вызывая метод main у класса MenuScene,
     * который отображает начальное меню игры.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        MenuScene.main(args);  // Запуск основного меню игры
    }
}
