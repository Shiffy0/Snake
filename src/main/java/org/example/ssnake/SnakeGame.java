package org.example.ssnake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Класс, представляющий игровую логику для игры "Змейка".
 * <p>
 * Этот класс отвечает за создание и управление игровым процессом, включая движение змейки,
 * обработку ввода с клавиатуры, обновление состояния игры и отображение графики.
 * </p>
 */
public class SnakeGame extends Application {

    private Snake snake; // Ссылка на объект змейки
    private Wall wall; // Ссылка на объект стены
    private long lastUpdate = 0; // Для отслеживания времени
    private long updateInterval; // Интервал обновления для выбранной скорости
    private final int snakeLength; // Начальная длина змейки
    private static final Logger logger = LogManager.getLogger(SnakeGame.class);
    private Canvas canvas; // Добавляем поле canvas
    private Stage gameStage;
    private boolean gameOverShown = false; // Флаг для отслеживания, было ли показано окно завершения игры


    /**
     * Конструктор игры, принимающий параметры скорости и длины змейки.
     *
     * @param speedLevel       Уровень скорости игры (влияет на частоту обновления).
     * @param initialSnakeLength Начальная длина змейки.
     */
    public SnakeGame(int speedLevel, int initialSnakeLength) {
        this.snakeLength = initialSnakeLength;
        this.updateInterval = 100_000_000 / speedLevel; // Разные скорости (чем выше уровень, тем быстрее)
    }

    /**
     * Запускает игровой процесс, создавая необходимые элементы интерфейса и логики.
     *
     * @param stage Основная сцена игры.
     */
    @Override
    public void start(Stage stage) {
        this.gameStage = stage;

        // Создаем контейнер и холст
        Pane root = new AnchorPane(); // Используем AnchorPane для центровки
        canvas = new Canvas(600, 400); // Создаем холст
        root.getChildren().add(canvas);

        // Получаем графический контекст
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Создаем змейку и стены
        snake = new Snake(snakeLength); // Передаем начальную длину змейки
        wall = new Wall();

        // Настроим сцену
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.show();

        logger.info("Игра началась с уровнем скорости: {} и начальной длиной змейки: {}", updateInterval, snakeLength);

        // Обработка нажатия клавиш
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                snake.setDirection(Direction.UP);
                logger.info("Направление змейки изменено на ВВЕРХ");
            } else if (event.getCode() == KeyCode.DOWN) {
                snake.setDirection(Direction.DOWN);
                logger.info("Направление змейки изменено на ВНИЗ");
            } else if (event.getCode() == KeyCode.LEFT) {
                snake.setDirection(Direction.LEFT);
                logger.info("Направление змейки изменено на ВЛЕВО");
            } else if (event.getCode() == KeyCode.RIGHT) {
                snake.setDirection(Direction.RIGHT);
                logger.info("Направление змейки изменено на ВПРАВО");
            }
        });

        // Центрируем холст
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        AnchorPane.setTopAnchor(canvas, (root.getHeight() - canvas.getHeight()) / 2);
        AnchorPane.setLeftAnchor(canvas, (root.getWidth() - canvas.getWidth()) / 2);

        // Отрисовка на старте
        draw(gc);

        // Создаем таймер, который будет обновлять игру каждый кадр
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval) { // Проверяем, прошло ли нужное время
                    update(); // Обновляем состояние игры
                    draw(gc); // Отрисовываем изменения
                    lastUpdate = now; // Обновляем время последнего обновления
                }
            }
        };
        timer.start(); // Запускаем таймер
    }

    /**
     * Метод для обновления состояния игры. Перемещает змейку и проверяет завершение игры.
     */
    private void update() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Перемещение змейки
        snake.move(gc);  // Передаем gc в move() для отрисовки сообщения

        // Проверка, если игра завершена, не обновляем состояние
        if (snake.isGameOver() && !gameOverShown) {
            gameOver(); // Показать окно завершения игры
        }
    }

    /**
     * Метод для обработки завершения игры.
     * Показывает окно завершения игры и отображает соответствующие сообщения.
     */
    private void gameOver() {
        logger.info("Игра завершена. Показываем окно завершения игры.");
        // Создаем окно завершения игры
        GameOverWindow gameOverWindow = new GameOverWindow(gameStage);
        gameOverWindow.showGameOverWindow();

        // Устанавливаем флаг, что окно завершения игры было показано
        gameOverShown = true;
    }

    /**
     * Метод для отрисовки игры на экране.
     * Очистка экрана и рисование змейки и стен.
     *
     * @param gc Графический контекст, используемый для рисования.
     */
    private void draw(GraphicsContext gc) {
        // Очистка фона
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 600, 400);

        // Рисуем змейку и стены
        snake.draw(gc);
        wall.draw(gc);
    }

    /**
     * Точка входа для запуска приложения.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        launch(); // Запуск приложения
    }
}
