package org.example.ssnake;

/**
 * Класс для представления точки на игровом поле.
 * Каждая точка имеет координаты x и y, которые используются для отслеживания положения
 * объектов (например, сегментов змейки) на поле.
 */
public class Point {

    // Координаты точки
    public int x, y;

    /**
     * Конструктор для создания точки с заданными координатами.
     *
     * @param x координата по оси X
     * @param y координата по оси Y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Переопределение метода equals для сравнения точек.
     * Две точки считаются равными, если их координаты совпадают.
     *
     * @param obj объект, с которым сравнивается текущая точка
     * @return true, если точки равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Если объект идентичен
        if (obj == null || getClass() != obj.getClass()) return false;  // Если типы объектов разные
        Point point = (Point) obj;  // Приводим объект к типу Point
        return x == point.x && y == point.y;  // Сравниваем координаты
    }

}
