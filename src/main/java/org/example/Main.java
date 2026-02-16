package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <b>Console app</b> array of trapezium which is sorted by field
 * @author Ilya Biryuk
 * @version 1.0
 */
public class Main {
    /**
     * where program starts
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("=== Демонстрация класса Trapezium ===\n");

        // Массив трапеций (вершины заданы по порядку: нижнее основание, право, верхнее основание, лево)
        Trapezium[] arr = new Trapezium[] {
                new Trapezium(
                        new Point(0, 0), new Point(10, 0),
                        new Point(8, 4), new Point(2, 4),
                        "red", "yellow"
                ),
                new Trapezium(
                        new Point(0, 0), new Point(6, 0),
                        new Point(5, 3), new Point(1, 3),
                        "blue", "green"
                ),
                new Trapezium(
                        new Point(0, 0), new Point(12, 0),
                        new Point(10, 5), new Point(2, 5),
                        "black", "white"
                ),
                new Trapezium("1,1,5,1,4,3,2,3,orange,pink"),
                new Trapezium("0,0,4,0,3,2,1,2,gray,cyan")
        };

        System.out.println("Исходный массив:");
        printTrapezia(arr);

        // Сортировка по площади (Comparable по умолчанию)
        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        Arrays.sort(arr);
        System.out.println("\nОтсортировано по площади (по умолчанию):");
        printTrapezia(arr);

        // Сортировка по периметру (Comparator)
        Arrays.sort(arr, Trapezium.comparatorBy(Trapezium.CompareField.PERIMETER));
        System.out.println("\nОтсортировано по периметру:");
        printTrapezia(arr);

        // Сортировка по цвету контура
        Arrays.sort(arr, Trapezium.comparatorBy(Trapezium.CompareField.OUTLINE_COLOR));
        System.out.println("\nОтсортировано по цвету контура:");
        printTrapezia(arr);

        // Сортировка по цвету заливки
        Arrays.sort(arr, Trapezium.comparatorBy(Trapezium.CompareField.FILL_COLOR));
        System.out.println("\nОтсортировано по цвету заливки:");
        printTrapezia(arr);

        // Демонстрация toString / из строки
        System.out.println("\n--- Сериализация в строку и обратно ---");
        String s = arr[0].toString();
        System.out.println("Строка (первый элемент): " + s);
        Trapezium restored = new Trapezium(s);
        System.out.println("Восстановленный: площадь=" + restored.getArea() + ", периметр=" + restored.getPerimeter());
        System.out.println("Равен исходному: " + arr[0].equals(restored));

        // Демонстрация Iterable
        System.out.println("\n--- Итератор по полям (первый элемент) ---");
        int i = 0;
        for (Object field : arr[0]) {
            System.out.println("  Поле " + (i++) + ": " + field);
        }
    }

    /**
     * prints trapezia values
     * @param arr array of trapezia object
     * @see Main#main(String[])
     */
    private static void printTrapezia(Trapezium[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Trapezium t = arr[i];
            System.out.printf("  [%d] площадь=%.2f периметр=%.2f контур=%s заливка=%s%n",
                    i, t.getArea(), t.getPerimeter(), t.getOutlineColor(), t.getFillColor());
        }
    }
}
