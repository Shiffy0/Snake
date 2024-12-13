package org.example.ssnake;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс, отображающий окно завершения игры с кнопкой для выхода из приложения.
 * После завершения игры окно будет показывать сообщение о завершении и кнопки для выхода.
 */
public class GameOverWindow {

    // Логгер для использования в классе
    private static final Logger logger = LogManager.getLogger(GameOverWindow.class);

    private Stage gameStage;

    /**
     * Конструктор, инициализирующий объект окна завершения игры.
     *
     * @param gameStage Ссылка на основное окно игры, которое будет минимизировано при отображении окна завершения игры
     */
    public GameOverWindow(Stage gameStage) {
        this.gameStage = gameStage;
    }

    /**
     * Показывает окно завершения игры с кнопкой для выхода.
     * При нажатии на кнопку приложение завершится.
     * В процессе отображения окна, основное окно игры будет минимизировано.
     */
    public void showGameOverWindow() {
        try {
            // Создаем новое окно для завершения игры
            Stage gameOverStage = new Stage();
            gameOverStage.setTitle("Конец игры");

            // Создаем кнопку выхода
            Button exitButton = new Button("Выход");
            exitButton.setOnAction(e -> {
                // Логируем информацию о нажатии кнопки
                logger.info("Кнопка выхода нажата. Закрытие приложения.");
                System.exit(0);
            });

            // Создаем HBox для размещения кнопки
            HBox layout = new HBox(20); // Отступ между кнопками
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(exitButton);

            // Создаем сцену и показываем её
            Scene gameOverScene = new Scene(layout, 300, 200);
            gameOverStage.setScene(gameOverScene);
            gameOverStage.show();

            // Минимизируем основное окно игры
            gameStage.setIconified(true);

            // Логируем факт отображения окна
            logger.info("Окно завершения игры отображено.");

        } catch (Exception e) {
            // Логируем ошибку, если она произошла
            logger.error("Произошла ошибка при отображении окна завершения игры", e);
        }
    }
}
