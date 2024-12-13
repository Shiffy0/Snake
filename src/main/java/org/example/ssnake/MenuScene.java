package org.example.ssnake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для отображения главного меню игры Snake.
 * В этом классе пользователь может выбрать уровень сложности (скорость) и начальную длину змейки,
 * а затем начать игру с выбранными параметрами.
 */
public class MenuScene extends Application {

    // Логгер для отслеживания событий в классе MenuScene
    private static final Logger logger = LogManager.getLogger(MenuScene.class);    // Переменные для выбранного уровня скорости и начальной длины змейки
    private int selectedSpeedLevel = 1; // Выбранный уровень скорости
    private int snakeLength = 3; // Начальная длина змейки

    /**
     * Метод запускает сцены главного меню игры.
     * В меню есть слайдеры для выбора уровня сложности и длины змейки, а также кнопка для старта игры.
     *
     * @param primaryStage Главная сцена приложения
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Элементы управления для скорости игры
            Label speedLabel = new Label("Выберите уровень скорости (1 до 10):");
            Slider speedSlider = new Slider(1, 10, selectedSpeedLevel); // Уровни сложности от 1 до 10
            speedSlider.setShowTickLabels(true);
            speedSlider.setShowTickMarks(true);
            speedSlider.setBlockIncrement(1);

            // Элементы управления для длины змейки
            Label lengthLabel = new Label("Введите начальную длину змейки:");
            Slider lengthSlider = new Slider(3, 30, snakeLength); // Длина змейки от 3 до 30
            lengthSlider.setShowTickLabels(true);
            lengthSlider.setShowTickMarks(true);
            lengthSlider.setBlockIncrement(1);

            // Кнопка для старта игры
            Button startButton = new Button("Начать игру");

            // Обработчик для кнопки старта игры
            startButton.setOnAction(event -> {
                // Получаем значения слайдеров
                selectedSpeedLevel = (int) speedSlider.getValue();
                snakeLength = (int) lengthSlider.getValue();
                logger.info("Игра начата с уровнем скорости: " + selectedSpeedLevel + " и длиной змейки: " + snakeLength);
                startGame(primaryStage); // Запускаем игру с выбранными параметрами
            });

            // Организуем элементы управления в вертикальном порядке
            VBox vbox = new VBox(20, speedLabel, speedSlider, lengthLabel, lengthSlider, startButton);
            vbox.setStyle("-fx-padding: 20px;");

            // Создаем сцену с заданным макетом
            Scene scene = new Scene(vbox, 400, 300);
            primaryStage.setTitle("Меню игры Snake");
            primaryStage.setScene(scene);
            primaryStage.show();

            logger.info("Отображена сцена меню.");

        } catch (Exception e) {
            // Логируем ошибку при создании сцены
            logger.info("Ошибка при настройке сцены меню: ", e);
        }
    }

    /**
     * Метод для запуска игры с выбранными пользователем параметрами.
     * При запуске игры передаются выбранные уровень скорости и длина змейки.
     *
     * @param primaryStage Ссылка на основной Stage для игры
     */
    private void startGame(Stage primaryStage) {
        try {
            // Создаем объект игры с переданными параметрами
            SnakeGame game = new SnakeGame(selectedSpeedLevel, snakeLength);
            game.start(primaryStage); // Запуск игры
            logger.info("Игра успешно запущена с уровнем скорости: " + selectedSpeedLevel + " и длиной змейки: " + snakeLength);

        } catch (Exception e) {
            // Логируем ошибку при запуске игры
            logger.info("Ошибка при запуске игры: ", e);
        }
    }

    /**
     * Главный метод для запуска приложения.
     * Запускает сцену MenuScene.
     *
     * @param args Аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            launch(args); // Запуск приложения
        } catch (Exception e) {
            // Логируем ошибку при запуске приложения
            logger.info("Ошибка при запуске приложения: ", e);
        }
    }
}
