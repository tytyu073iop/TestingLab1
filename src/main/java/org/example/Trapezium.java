package org.example;

import java.util.*;

/**
 * A trapezoid is a derived class from Figure.
 * Stores the four vertices, outline color, and fill color.
 * Implements Comparable, Comparator (by selected field), Iterable by all fields.
 */
public class Trapezium extends Figure implements Comparable<Trapezium>, Iterable<Object> {

    /**
     * <p><b>AREA</b> - compare by area. <i>default</i></p>
     * <p><b>PERIMETER</b> - by perimeter</p>
     * <p><b>OUTLINE_COLOR</b> - by outline color</p>
     * <p><b>FILL_COLOR</b> - by fill color</p>
     */
    public enum CompareField {
        AREA, PERIMETER, OUTLINE_COLOR, FILL_COLOR
    }

    private final Point p1;
    private final Point p2;
    private final Point p3;
    private final Point p4;
    private final String outlineColor;
    private final String fillColor;

    private static CompareField defaultCompareField = CompareField.AREA;

    /**
     * Устанавливает поле для сравнения по умолчанию (для compareTo).
     * @param field field to compare by
     * @throws IllegalArgumentException when field is null
     */
    public static void setDefaultCompareField(CompareField field) {
        if (field == null) {
            throw new IllegalArgumentException("Поле сравнения не может быть null");
        }
        defaultCompareField = field;
    }

    public static CompareField getDefaultCompareField() {
        return defaultCompareField;
    }

    /**
     * constructs trapezium
     * <b>Point order doesn't matter</b>
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @param p4 forth point
     * @param outlineColor color to which color lines of trapezium
     * @param fillColor color to fill area of trapezium with
     */
    public Trapezium(Point p1, Point p2, Point p3, Point p4, String outlineColor, String fillColor) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            throw new IllegalArgumentException("Вершины трапеции не могут быть null");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.outlineColor = outlineColor != null ? outlineColor : "";
        this.fillColor = fillColor != null ? fillColor : "";
        validateTrapezium();
    }

    /**
     * initialization by string
     * @param s should equal toString(): x1,y1,x2,y2,x3,y3,x4,y4,outlineColor,fillColor
     */
    public Trapezium(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("Строка инициализации не может быть пустой");
        }
        String trimmed = s.trim();
        // Разделитель — запятая, но цвета могут содержать запятые, поэтому разбираем по первым 8 числам
        String[] parts = trimmed.split(",", -1);
        if (parts.length < 8) {
            throw new IllegalArgumentException("Ожидается минимум 8 числовых полей и 2 цвета. Получено частей: " + parts.length);
        }
        try {
            double x1 = Double.parseDouble(parts[0].trim());
            double y1 = Double.parseDouble(parts[1].trim());
            double x2 = Double.parseDouble(parts[2].trim());
            double y2 = Double.parseDouble(parts[3].trim());
            double x3 = Double.parseDouble(parts[4].trim());
            double y3 = Double.parseDouble(parts[5].trim());
            double x4 = Double.parseDouble(parts[6].trim());
            double y4 = Double.parseDouble(parts[7].trim());
            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);
            this.p3 = new Point(x3, y3);
            this.p4 = new Point(x4, y4);
            // Остальное — outlineColor и fillColor (всё между 8-й и 9-й запятой — outline, после 9-й — fill)
            if (parts.length >= 10) {
                this.outlineColor = parts[8].trim();
                this.fillColor = parts[9].trim();
            } else if (parts.length == 9) {
                this.outlineColor = parts[8].trim();
                this.fillColor = "";
            } else {
                this.outlineColor = "";
                this.fillColor = "";
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректный числовой формат в строке: " + trimmed, e);
        }
        validateTrapezium();
    }

    private void validateTrapezium() {
        double area = computeArea();
        assert area >= 0 : "Площадь трапеции не может быть отрицательной";
        if (area < 1e-10) {
            throw new IllegalArgumentException("Вершины не образуют трапецию с положительной площадью");
        }
    }

    /**
     *
     * @param a one end of size
     * @param b second end of side
     * @return side length
     */
    private double sideLength(Point a, Point b) {
        return a.distanceTo(b);
    }

    @Override
    public double getArea() {
        return computeArea();
    }

    private double computeArea() {
        // Формула площади по координатам: 0.5 * |(x1(y2-y4) + x2(y3-y1) + x3(y4-y2) + x4(y1-y3))|
        double x1 = p1.getX(), y1 = p1.getY();
        double x2 = p2.getX(), y2 = p2.getY();
        double x3 = p3.getX(), y3 = p3.getY();
        double x4 = p4.getX(), y4 = p4.getY();
        return 0.5 * Math.abs(x1 * (y2 - y4) + x2 * (y3 - y1) + x3 * (y4 - y2) + x4 * (y1 - y3));
    }

    @Override
    public double getPerimeter() {
        return sideLength(p1, p2) + sideLength(p2, p3) + sideLength(p3, p4) + sideLength(p4, p1);
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Point getP3() { return p3; }
    public Point getP4() { return p4; }
    public String getOutlineColor() { return outlineColor; }
    public String getFillColor() { return fillColor; }

    @Override
    public int compareTo(Trapezium o) {
        if (o == null) throw new IllegalArgumentException("Сравниваемый объект не может быть null");
        return compareByField(this, o, defaultCompareField);
    }

    /**
     *
     * @param a
     * @param b
     * @param field by which to compare
     * @return difference between fields
     */
    private static int compareByField(Trapezium a, Trapezium b, CompareField field) {
        switch (field) {
            case AREA:
                return Double.compare(a.getArea(), b.getArea());
            case PERIMETER:
                return Double.compare(a.getPerimeter(), b.getPerimeter());
            case OUTLINE_COLOR:
                return (a.outlineColor != null ? a.outlineColor : "").compareToIgnoreCase(b.outlineColor != null ? b.outlineColor : "");
            case FILL_COLOR:
                return (a.fillColor != null ? a.fillColor : "").compareToIgnoreCase(b.fillColor != null ? b.fillColor : "");
            default:
                return Double.compare(a.getArea(), b.getArea());
        }
    }

    /**
     * @return comparator to compare by field
     */
    public static Comparator<Trapezium> comparatorBy(CompareField field) {
        if (field == null) throw new IllegalArgumentException("Поле сравнения не может быть null");
        return (a, b) -> compareByField(a, b, field);
    }

    /**
     * get iterator by all trapezium variables
     * @return iterator
     */
    @Override
    public Iterator<Object> iterator() {
        List<Object> fields = Arrays.asList(p1, p2, p3, p4, outlineColor, fillColor);
        return fields.iterator();
    }

    @Override
    public String toString() {
        return p1.getX() + "," + p1.getY() + ","
                + p2.getX() + "," + p2.getY() + ","
                + p3.getX() + "," + p3.getY() + ","
                + p4.getX() + "," + p4.getY() + ","
                + outlineColor + "," + fillColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trapezium t = (Trapezium) o;
        return p1.equals(t.p1) && p2.equals(t.p2) && p3.equals(t.p3) && p4.equals(t.p4)
                && Objects.equals(outlineColor, t.outlineColor) && Objects.equals(fillColor, t.fillColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, p3, p4, outlineColor, fillColor);
    }
}
