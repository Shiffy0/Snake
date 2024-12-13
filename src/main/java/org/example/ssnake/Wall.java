package org.example.ssnake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий стены на игровом поле.
 * <p>
 * Этот класс управляет созданием и отрисовкой стен, которые окружают игровое поле,
 * а также проверяет столкновение змейки с этими стенами.
 * </p>
 */
public class Wall {

    private List<Point> wallBlocks; // Координаты блоков стен

    /**
     * Конструктор класса Wall, который инициализирует блоки стен.
     * Стены создаются по периметру игрового поля (по бокам и сверху/снизу).
     */
    public Wall(){
        wallBlocks = new ArrayList<>();

        // Добавление верхней и нижней стены
        for(int i=0; i<30; i++){
            wallBlocks.add(new Point(i, 0)); // Верхняя стена
            wallBlocks.add(new Point(i, 19)); // Нижняя стена
        }

        // Добавление левой и правой стены
        for(int i=0; i<20; i++){
            wallBlocks.add(new Point(0,i)); // Левая стена
            wallBlocks.add(new Point(29,i)); // Правая стена
        }
    }

    /**
     * Метод для отрисовки стен на игровом поле.
     *
     * @param gc Графический контекст, используемый для рисования стен.
     */
    public void draw(GraphicsContext gc){
        gc.setFill(Color.RED); // Цвет стен (красный)
        for (Point block: wallBlocks) {
            gc.fillRect(block.x * 20, block.y * 20, 20, 20); // Отрисовка каждого блока стены
        }
    }
}
