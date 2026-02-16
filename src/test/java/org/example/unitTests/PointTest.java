package org.example.unitTests;

import org.example.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Point class
 * Tests all public methods and constructors with positive and negative test cases
 */
@DisplayName("Point Class Tests")
class PointTest {

    // ========== Constructor Tests ==========

    @Test
    @DisplayName("Constructor - Positive: Create point with valid coordinates")
    void testConstructorPositive() {
        Point p = new Point(3.5, 4.2);
        assertNotNull(p);
        assertEquals(3.5, p.getX(), 1e-9);
        assertEquals(4.2, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("Constructor - Positive: Create point with zero coordinates")
    void testConstructorWithZero() {
        Point p = new Point(0.0, 0.0);
        assertNotNull(p);
        assertEquals(0.0, p.getX(), 1e-9);
        assertEquals(0.0, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("Constructor - Positive: Create point with negative coordinates")
    void testConstructorWithNegative() {
        Point p = new Point(-5.5, -10.2);
        assertNotNull(p);
        assertEquals(-5.5, p.getX(), 1e-9);
        assertEquals(-10.2, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("Constructor - Positive: Create point with large values")
    void testConstructorWithLargeValues() {
        Point p = new Point(1e10, -1e10);
        assertNotNull(p);
        assertEquals(1e10, p.getX(), 1e-9);
        assertEquals(-1e10, p.getY(), 1e-9);
    }

    // ========== Getter Tests ==========

    @Test
    @DisplayName("getX - Positive: Get x coordinate")
    void testGetXPositive() {
        Point p = new Point(7.3, 2.1);
        assertEquals(7.3, p.getX(), 1e-9);
    }

    @Test
    @DisplayName("getY - Positive: Get y coordinate")
    void testGetYPositive() {
        Point p = new Point(7.3, 2.1);
        assertEquals(2.1, p.getY(), 1e-9);
    }

    // ========== Setter Tests ==========

    @Test
    @DisplayName("setX - Positive: Set x coordinate")
    void testSetXPositive() {
        Point p = new Point(1.0, 2.0);
        p.setX(5.5);
        assertEquals(5.5, p.getX(), 1e-9);
        assertEquals(2.0, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("setX - Positive: Set x to zero")
    void testSetXToZero() {
        Point p = new Point(1.0, 2.0);
        p.setX(0.0);
        assertEquals(0.0, p.getX(), 1e-9);
    }

    @Test
    @DisplayName("setX - Positive: Set x to negative value")
    void testSetXToNegative() {
        Point p = new Point(1.0, 2.0);
        p.setX(-10.5);
        assertEquals(-10.5, p.getX(), 1e-9);
    }

    @Test
    @DisplayName("setY - Positive: Set y coordinate")
    void testSetYPositive() {
        Point p = new Point(1.0, 2.0);
        p.setY(8.7);
        assertEquals(1.0, p.getX(), 1e-9);
        assertEquals(8.7, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("setY - Positive: Set y to zero")
    void testSetYToZero() {
        Point p = new Point(1.0, 2.0);
        p.setY(0.0);
        assertEquals(0.0, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("setY - Positive: Set y to negative value")
    void testSetYToNegative() {
        Point p = new Point(1.0, 2.0);
        p.setY(-15.3);
        assertEquals(-15.3, p.getY(), 1e-9);
    }

    // ========== distanceTo Tests ==========

    @Test
    @DisplayName("distanceTo - Positive: Calculate distance between two points")
    void testDistanceToPositive() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        double distance = p1.distanceTo(p2);
        assertEquals(5.0, distance, 1e-9);
    }

    @Test
    @DisplayName("distanceTo - Positive: Distance to same point is zero")
    void testDistanceToSamePoint() {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(5, 7);
        double distance = p1.distanceTo(p2);
        assertEquals(0.0, distance, 1e-9);
    }

    @Test
    @DisplayName("distanceTo - Positive: Distance with negative coordinates")
    void testDistanceToNegativeCoordinates() {
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(-4, -5);
        double distance = p1.distanceTo(p2);
        assertEquals(5.0, distance, 1e-9);
    }

    @Test
    @DisplayName("distanceTo - Positive: Distance is symmetric")
    void testDistanceToSymmetric() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(4, 6);
        double d1 = p1.distanceTo(p2);
        double d2 = p2.distanceTo(p1);
        assertEquals(d1, d2, 1e-9);
    }

    @Test
    @DisplayName("distanceTo - Negative: Null point throws IllegalArgumentException")
    void testDistanceToNull() {
        Point p = new Point(1, 2);
        assertThrows(IllegalArgumentException.class, () -> p.distanceTo(null),
                "Должно быть выброшено исключение при null");
    }

    // ========== toString Tests ==========

    @Test
    @DisplayName("toString - Positive: Convert point to string")
    void testToStringPositive() {
        Point p = new Point(3.5, 4.2);
        String result = p.toString();
        assertEquals("3.5,4.2", result);
    }

    @Test
    @DisplayName("toString - Positive: Convert point with zero coordinates")
    void testToStringWithZero() {
        Point p = new Point(0.0, 0.0);
        String result = p.toString();
        assertEquals("0.0,0.0", result);
    }

    @Test
    @DisplayName("toString - Positive: Convert point with negative coordinates")
    void testToStringWithNegative() {
        Point p = new Point(-5.5, -10.2);
        String result = p.toString();
        assertEquals("-5.5,-10.2", result);
    }

    // ========== fromString Tests ==========

    @Test
    @DisplayName("fromString - Positive: Create point from valid string")
    void testFromStringPositive() {
        Point p = Point.fromString("3.5,4.2");
        assertNotNull(p);
        assertEquals(3.5, p.getX(), 1e-9);
        assertEquals(4.2, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("fromString - Positive: Create point with spaces")
    void testFromStringWithSpaces() {
        Point p = Point.fromString(" 3.5 , 4.2 ");
        assertNotNull(p);
        assertEquals(3.5, p.getX(), 1e-9);
        assertEquals(4.2, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("fromString - Positive: Create point with negative values")
    void testFromStringWithNegative() {
        Point p = Point.fromString("-5.5,-10.2");
        assertNotNull(p);
        assertEquals(-5.5, p.getX(), 1e-9);
        assertEquals(-10.2, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("fromString - Positive: Create point with zero values")
    void testFromStringWithZero() {
        Point p = Point.fromString("0,0");
        assertNotNull(p);
        assertEquals(0.0, p.getX(), 1e-9);
        assertEquals(0.0, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("fromString - Negative: Null string throws IllegalArgumentException")
    void testFromStringNull() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString(null),
                "Должно быть выброшено исключение при null");
    }

    @Test
    @DisplayName("fromString - Negative: Empty string throws IllegalArgumentException")
    void testFromStringEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString(""),
                "Должно быть выброшено исключение при пустой строке");
    }

    @Test
    @DisplayName("fromString - Negative: Blank string throws IllegalArgumentException")
    void testFromStringBlank() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString("   "),
                "Должно быть выброшено исключение при строке из пробелов");
    }

    @Test
    @DisplayName("fromString - Negative: Invalid format (one number) throws IllegalArgumentException")
    void testFromStringInvalidFormatOneNumber() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString("3.5"),
                "Должно быть выброшено исключение при неверном формате");
    }

    @Test
    @DisplayName("fromString - Negative: Invalid format (three numbers) throws IllegalArgumentException")
    void testFromStringInvalidFormatThreeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString("3.5,4.2,5.1"),
                "Должно быть выброшено исключение при неверном формате");
    }

    @Test
    @DisplayName("fromString - Negative: Non-numeric string throws IllegalArgumentException")
    void testFromStringNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString("abc,def"),
                "Должно быть выброшено исключение при нечисловых значениях");
    }

    @Test
    @DisplayName("fromString - Negative: Partially numeric string throws IllegalArgumentException")
    void testFromStringPartiallyNumeric() {
        assertThrows(IllegalArgumentException.class, () -> Point.fromString("3.5,abc"),
                "Должно быть выброшено исключение при частично числовой строке");
    }

    // ========== equals Tests ==========

    @Test
    @DisplayName("equals - Positive: Equal points return true")
    void testEqualsPositive() {
        Point p1 = new Point(3.5, 4.2);
        Point p2 = new Point(3.5, 4.2);
        assertEquals(p1, p2);
    }

    @Test
    @DisplayName("equals - Positive: Same object returns true")
    void testEqualsSameObject() {
        Point p = new Point(3.5, 4.2);
        assertEquals(p, p);
    }

    @Test
    @DisplayName("equals - Positive: Different x coordinates return false")
    void testEqualsDifferentX() {
        Point p1 = new Point(3.5, 4.2);
        Point p2 = new Point(3.6, 4.2);
        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("equals - Positive: Different y coordinates return false")
    void testEqualsDifferentY() {
        Point p1 = new Point(3.5, 4.2);
        Point p2 = new Point(3.5, 4.3);
        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("equals - Negative: Null object returns false")
    void testEqualsNull() {
        Point p = new Point(3.5, 4.2);
        assertNotEquals(null, p);
    }

    @Test
    @DisplayName("equals - Negative: Different class returns false")
    void testEqualsDifferentClass() {
        Point p = new Point(3.5, 4.2);
        assertNotEquals("not a point", p);
    }

    // ========== hashCode Tests ==========

    @Test
    @DisplayName("hashCode - Positive: Equal points have same hash code")
    void testHashCodePositive() {
        Point p1 = new Point(3.5, 4.2);
        Point p2 = new Point(3.5, 4.2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("hashCode - Positive: Different points may have different hash codes")
    void testHashCodeDifferent() {
        Point p1 = new Point(3.5, 4.2);
        Point p2 = new Point(3.6, 4.2);
        // Note: Different hash codes are not guaranteed, but likely
        // This test just ensures hashCode() doesn't throw an exception
        assertNotNull(p1.hashCode());
        assertNotNull(p2.hashCode());
    }
}

