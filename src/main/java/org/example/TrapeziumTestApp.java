package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 *This application: checking all methods and constructors of Point, Figure, Trapezium
 * with the output of data and results. Assert and exception handling are used.
 * @author ilya
 * @version 1.0
 */
public class TrapeziumTestApp {

    static void main(String[] args) {
        System.out.println("========== ТЕСТИРОВАНИЕ КЛАССОВ ==========\n");

        testPoint();
        testFigureAndTrapezium();
        testTrapeziumConstructors();
        testTrapeziumMethods();
        testComparableAndComparator();
        testIterable();
        testToStringAndFromString();
        testExceptionsAndAssert();

        System.out.println("\n========== ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ ==========");
    }

    private static void testPoint() {
        System.out.println("--- Point: конструктор и геттеры/сеттеры ---");
        Point p = new Point(3, 4);
        System.out.println("Point(3, 4) -> x=" + p.getX() + ", y=" + p.getY());
        p.setX(5);
        p.setY(12);
        System.out.println("После setX(5), setY(12) -> x=" + p.getX() + ", y=" + p.getY());

        System.out.println("\n--- Point: distanceTo ---");
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        double d = a.distanceTo(b);
        System.out.println("Расстояние (0,0) до (3,4) = " + d + " (ожидается 5.0)");
        assert Math.abs(d - 5.0) < 1e-9 : "distanceTo должен быть 5.0";

        System.out.println("\n--- Point: fromString / toString ---");
        Point p2 = Point.fromString(" -1.5 , 2.5 ");
        System.out.println("fromString(\" -1.5 , 2.5 \") -> " + p2 + ", x=" + p2.getX() + ", y=" + p2.getY());
        assert Math.abs(p2.getX() - (-1.5)) < 1e-9 && Math.abs(p2.getY() - 2.5) < 1e-9;
        System.out.println("toString() = " + p2);
    }

    private static void testFigureAndTrapezium() {
        System.out.println("\n--- Figure / Trapezium: площадь и периметр ---");
        // Трапеция: нижнее основание 10, верхнее 6, высота 4 -> площадь = (10+6)*4/2 = 32
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "blue"
        );
        double area = t.getArea();
        double perimeter = t.getPerimeter();
        System.out.println("Трапеция (0,0)-(10,0)-(8,4)-(2,4): площадь=" + area + ", периметр=" + perimeter);
        assert area > 0 && perimeter > 0 : "Площадь и периметр должны быть положительными";
    }

    private static void testTrapeziumConstructors() {
        System.out.println("\n--- Trapezium: конструктор по вершинам и цветам ---");
        Trapezium t1 = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "green", "yellow"
        );
        System.out.println("Создана трапеция: " + t1);
        System.out.println("  p1=" + t1.getP1() + ", p2=" + t1.getP2() + ", p3=" + t1.getP3() + ", p4=" + t1.getP4());
        System.out.println("  контур=" + t1.getOutlineColor() + ", заливка=" + t1.getFillColor());

        System.out.println("\n--- Trapezium: конструктор из строки ---");
        String str = "0,0,4,0,3,2,1,2,green,yellow";
        Trapezium t2 = new Trapezium(str);
        System.out.println("Из строки: \"" + str + "\"");
        System.out.println("  площадь=" + t2.getArea() + ", периметр=" + t2.getPerimeter());
        assert t1.equals(t2) : "Трапеция из строки должна совпадать с построенной по точкам";
        System.out.println("  Результат: объект эквивалентен трапеции, созданной по точкам (equals=true).");
    }

    private static void testTrapeziumMethods() {
        System.out.println("\n--- Trapezium: геттеры ---");
        Trapezium t = new Trapezium("1,1,5,1,4,3,2,3,red,blue");
        System.out.println("getP1()=" + t.getP1() + ", getP2()=" + t.getP2() + ", getP3()=" + t.getP3() + ", getP4()=" + t.getP4());
        System.out.println("getOutlineColor()=" + t.getOutlineColor() + ", getFillColor()=" + t.getFillColor());
        System.out.println("getArea()=" + t.getArea() + ", getPerimeter()=" + t.getPerimeter());
    }

    private static void testComparableAndComparator() {
        System.out.println("\n--- Comparable и Comparator ---");
        Trapezium a = new Trapezium("0,0,10,0,8,4,2,4,a,z");  // большая площадь
        Trapezium b = new Trapezium("0,0,4,0,3,2,1,2,b,y");   // меньшая площадь

        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        int cmpArea = a.compareTo(b);
        System.out.println("compareTo (по площади): большая vs меньшая = " + cmpArea + " (ожидается > 0)");
        assert cmpArea > 0 : "По площади большая трапеция должна быть больше";

        Comparator<Trapezium> byPerimeter = Trapezium.comparatorBy(Trapezium.CompareField.PERIMETER);
        int cmpPer = byPerimeter.compare(a, b);
        System.out.println("comparatorBy(PERIMETER).compare(a,b) = " + cmpPer);

        Comparator<Trapezium> byOutline = Trapezium.comparatorBy(Trapezium.CompareField.OUTLINE_COLOR);
        int cmpOut = byOutline.compare(a, b);
        System.out.println("comparatorBy(OUTLINE_COLOR).compare(a,b) = " + cmpOut + " (a<b по алфавиту)");
        assert cmpOut < 0 : "a<b по строке контура";

        Trapezium.setDefaultCompareField(Trapezium.CompareField.FILL_COLOR);
        int cmpFill = a.compareTo(b);
        System.out.println("compareTo (по заливке): z vs y = " + cmpFill + " (ожидается > 0)");
    }

    private static void testIterable() {
        System.out.println("\n--- Iterable: обход полей ---");
        Trapezium t = new Trapezium("0,0,2,0,1.5,1,0.5,1,red,blue");
        int index = 0;
        for (Object field : t) {
            System.out.println("  Поле[" + index++ + "] = " + field);
        }
        assert index == 6 : "Должно быть 6 полей (4 точки + 2 цвета)";
    }

    private static void testToStringAndFromString() {
        System.out.println("\n--- toString и инициализация из строки ---");
        Trapezium t = new Trapezium(new Point(0, 0), new Point(4, 0), new Point(3, 2), new Point(1, 2), "out", "fill");
        String s = t.toString();
        System.out.println("toString() = " + s);
        Trapezium t2 = new Trapezium(s);
        System.out.println("Восстановлено из строки: площадь=" + t2.getArea() + ", контур=" + t2.getOutlineColor());
        assert t.equals(t2) : "Восстановленный объект должен быть равен исходному";
        System.out.println("Результат: объекты равны (equals=true).");
    }

    private static void testExceptionsAndAssert() {
        System.out.println("\n--- Обработка ошибочных ситуаций ---");

        try {
            Point.fromString("");
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("Point.fromString(\"\"): поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            Point.fromString("1,2,3");
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("Point.fromString(\"1,2,3\"): поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            new Trapezium(null);
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("Trapezium(null): поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            new Trapezium("1,2,3,4,5,6"); // только 6 чисел, нужно 8
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("Trapezium(\"1,2,3,4,5,6\"): поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            Trapezium t = new Trapezium(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1), "a", "b");
            t.compareTo(null);
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("compareTo(null): поймано IllegalArgumentException: " + e.getMessage());
        }

        try {
            Point p = new Point(0, 0);
            p.distanceTo(null);
            assert false : "Должно быть выброшено исключение";
        } catch (IllegalArgumentException e) {
            System.out.println("distanceTo(null): поймано IllegalArgumentException: " + e.getMessage());
        }

        System.out.println("Все ошибочные ситуации обработаны корректно.");
    }
}
