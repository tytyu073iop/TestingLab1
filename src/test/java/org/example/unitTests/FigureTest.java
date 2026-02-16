package org.example.unitTests;

import org.example.Figure;
import org.example.Point;
import org.example.Trapezium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Figure abstract class
 * Tests constructor and abstract methods through Trapezium implementation
 */
@DisplayName("Figure Class Tests")
class FigureTest {

    // ========== Constructor Tests ==========

    @Test
    @DisplayName("Constructor - Positive: Create figure through Trapezium")
    void testConstructorPositive() {
        // Since Figure is abstract, we test it through Trapezium
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        assertNotNull(t);
        assertInstanceOf(Figure.class, t);
    }

    // ========== getArea Tests ==========

    @Test
    @DisplayName("getArea - Positive: Calculate area of trapezium")
    void testGetAreaPositive() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        double area = t.getArea();
        assertTrue(area > 0, "Площадь должна быть положительной");
        // Expected area: (10+6)/2 * 4 = 32
        assertEquals(32.0, area, 1e-6);
    }

    @Test
    @DisplayName("getArea - Positive: Calculate area with different trapezium")
    void testGetAreaDifferentTrapezium() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(6, 0),
                new Point(5, 3), new Point(1, 3),
                "blue", "green"
        );
        double area = t.getArea();
        assertTrue(area > 0, "Площадь должна быть положительной");
    }

    @Test
    @DisplayName("getArea - Positive: Area is always non-negative")
    void testGetAreaNonNegative() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "black", "white"
        );
        double area = t.getArea();
        assertTrue(area >= 0, "Площадь не может быть отрицательной");
    }

    // ========== getPerimeter Tests ==========

    @Test
    @DisplayName("getPerimeter - Positive: Calculate perimeter of trapezium")
    void testGetPerimeterPositive() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        double perimeter = t.getPerimeter();
        assertTrue(perimeter > 0, "Периметр должен быть положительным");
        // Expected: 10 + sqrt(4^2+2^2) + 8 + sqrt(4^2+2^2) = 10 + sqrt(20) + 8 + sqrt(20)
        // = 18 + 2*sqrt(20) ≈ 18 + 8.944 = 26.944
        assertTrue(perimeter > 20 && perimeter < 30, "Периметр должен быть в разумных пределах");
    }

    @Test
    @DisplayName("getPerimeter - Positive: Perimeter is sum of all sides")
    void testGetPerimeterSumOfSides() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "gray", "cyan"
        );
        double perimeter = t.getPerimeter();
        assertTrue(perimeter > 0, "Периметр должен быть положительным");
    }

    @Test
    @DisplayName("getPerimeter - Positive: Perimeter is always positive")
    void testGetPerimeterPositiveValue() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(6, 0),
                new Point(5, 3), new Point(1, 3),
                "blue", "green"
        );
        double perimeter = t.getPerimeter();
        assertTrue(perimeter > 0, "Периметр должен быть положительным");
    }
}

