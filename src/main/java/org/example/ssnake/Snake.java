package org.example.ssnake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий змейку в игре.
 * Управляет состоянием змейки, её движением, направлением и проверкой столкновений.
 */
public class Snake {

    private List<Point> body; // Список сегментов змейки
    private Direction direction; // Текущее направление движения

    private static final int BOARD_WIDTH = 30; // Ширина игрового поля (например, 30 клеток по горизонтали)
    private static final int BOARD_HEIGHT = 20; // Высота игрового поля (например, 20 клеток по вертикали)

    /**
     * Конструктор класса Snake.
     * Инициализирует змейку с заданной начальной длиной.
     * Начальная позиция змейки — на координатах (10, 10) и далее по направлению вправо.
     *
     * @param initialLength Начальная длина змейки.
     */
    public Snake(int initialLength) {
        body = new ArrayList<>();
        // Добавляем начальные сегменты змейки
        for (int i = 0; i < initialLength; i++) {
            body.add(new Point(10 - i, 10)); // Начальная позиция змейки
        }
        direction = Direction.RIGHT; // Начальное направление движения
    }

    /**
     * Получение головы змейки.
     * Голова — это первый сегмент тела змейки.
     *
     * @return Текущая позиция головы змейки.
     */
    public Point getHead() {
        return body.get(0); // Возвращаем первый сегмент тела змейки (голову)
    }

    /**
     * Отрисовка змейки на холсте.
     *
     * @param gc Графический контекст для рисования.
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        for (Point segment : body) {
            gc.fillRect(segment.x * 20, segment.y * 20, 20, 20); // 20x20 — размер ячейки
        }
    }

    private boolean gameOver = false;  // Флаг для отслеживания завершения игры

    /**
     * Проверка, завершена ли игра.
     *
     * @return true, если игра завершена, иначе false.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Метод для перемещения змейки.
     * Перемещает голову змейки в соответствии с её направлением.
     * Если змейка выходит за пределы поля, она "перемещается" с другой стороны (зеркальный эффект).
     * Проверяет столкновение с телом змейки.
     *
     * @param gc Графический контекст для рисования.
     */
    public void move(GraphicsContext gc) {
        if (gameOver) return;  // Если игра завершена, не двигаем змейку

        Point head = body.get(0); // Получаем текущую голову
        Point newHead = switch (direction) {
            case UP -> new Point(head.x, head.y - 1);
            case DOWN -> new Point(head.x, head.y + 1);
            case LEFT -> new Point(head.x - 1, head.y);
            case RIGHT -> new Point(head.x + 1, head.y);
        };

        // Проверка выхода за пределы и зеркальное движение
        if (newHead.x < 0) {
            newHead.x = BOARD_WIDTH - 1;
        } else if (newHead.x >= BOARD_WIDTH) {
            newHead.x = 0;
        }

        if (newHead.y < 0) {
            newHead.y = BOARD_HEIGHT - 1;
        } else if (newHead.y >= BOARD_HEIGHT) {
            newHead.y = 0;
        }

        // Проверка на столкновение головы с телом
        if (body.stream().skip(1).anyMatch(segment -> segment.equals(newHead))) {
            // Если голова сталкивается с телом, игра завершена
            gameOver = true;  // Обновляем флаг завершения игры
            return;  // Останавливаем движение змейки
        }

        // Добавляем новый сегмент головы
        body.add(0, newHead);

        // Убираем хвост
        body.remove(body.size() - 1);
    }

    /**
     * Изменяет направление движения змейки.
     * Направление нельзя менять на противоположное (например, нельзя сразу двигаться влево, если змейка движется вправо).
     *
     * @param newDirection Новое направление для змейки.
     */
    public void setDirection(Direction newDirection) {
        // Запрещаем смену направления на противоположное (180 градусов)
        if (body.size() > 1) {  // Проверяем, что змейка больше одного сегмента
            if (newDirection == Direction.UP && direction != Direction.DOWN) {
                direction = newDirection;
            } else if (newDirection == Direction.DOWN && direction != Direction.UP) {
                direction = newDirection;
            } else if (newDirection == Direction.LEFT && direction != Direction.RIGHT) {
                direction = newDirection;
            } else if (newDirection == Direction.RIGHT && direction != Direction.LEFT) {
                direction = newDirection;
            }
        } else {
            direction = newDirection; // Если длина змейки 1, можем менять направление
        }
    }
}
