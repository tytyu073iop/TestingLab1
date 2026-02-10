package org.example;

/**
 * Describes x,y coordinates
 * @author ilya
 * @version 1.0
 */
public class Point {
    private double x;
    private double y;

    /**
     * contsucts a point with given coordinates
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Computes distance to another point
     * @param other point to which distance should be computed
     */
    public double distanceTo(Point other) {
        if (other == null) {
            throw new IllegalArgumentException("Другая точка не может быть null");
        }
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    /**
     * Creates point from string
     * @param s string to convert to point
     */
    public static Point fromString(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("Строка не может быть пустой");
        }
        String[] parts = s.trim().split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Ожидается формат 'x,y', получено: " + s);
        }
        try {
            double x = Double.parseDouble(parts[0].trim());
            double y = Double.parseDouble(parts[1].trim());
            return new Point(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректные координаты: " + s, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}
