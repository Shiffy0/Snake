package org.example.ssnake;

/**
 * Перечисление, представляющее возможные направления движения змейки в игре.
 *
 * <p>Это перечисление используется для определения направления движения змейки на игровом поле.
 * Направления играют ключевую роль в управлении змейкой и определяют, в какую сторону будет
 * двигаться змейка при каждом обновлении игрового процесса.</p>
 *
 * <p>Направления могут быть использованы для контроля ввода с клавиатуры и логики движения
 * змейки. Змейка может двигаться только в одном из этих направлений за раз, и направление
 * может изменяться в зависимости от ввода пользователя или логики игры.</p>
 *
 * <ul>
 *     <li>{@link Direction#UP} - Направление вверх, в сторону увеличения координаты Y.</li>
 *     <li>{@link Direction#DOWN} - Направление вниз, в сторону уменьшения координаты Y.</li>
 *     <li>{@link Direction#LEFT} - Направление влево, в сторону уменьшения координаты X.</li>
 *     <li>{@link Direction#RIGHT} - Направление вправо, в сторону увеличения координаты X.</li>
 * </ul>
 */
public enum Direction {
    /**
     * Направление вверх.
     * Змейка будет двигаться в сторону увеличения оси Y (вверх на игровом поле).
     */
    UP,

    /**
     * Направление вниз.
     * Змейка будет двигаться в сторону уменьшения оси Y (вниз на игровом поле).
     */
    DOWN,

    /**
     * Направление влево.
     * Змейка будет двигаться в сторону уменьшения оси X (влево на игровом поле).
     */
    LEFT,

    /**
     * Направление вправо.
     * Змейка будет двигаться в сторону увеличения оси X (вправо на игровом поле).
     */
    RIGHT
}