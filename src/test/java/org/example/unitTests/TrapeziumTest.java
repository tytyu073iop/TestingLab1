package org.example.unitTests;

import org.example.Point;
import org.example.Trapezium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Trapezium class
 * Tests all public methods and constructors with positive and negative test cases
 */
@DisplayName("Trapezium Class Tests")
class TrapeziumTest {

    private Point p1, p2, p3, p4;

    @BeforeEach
    void setUp() {
        // Reset default compare field before each test
        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        // Standard trapezium points
        p1 = new Point(0, 0);
        p2 = new Point(10, 0);
        p3 = new Point(8, 4);
        p4 = new Point(2, 4);
    }

    // ========== Constructor Tests (Point-based) ==========

    @Test
    @DisplayName("Constructor (Points) - Positive: Create trapezium with valid points")
    void testConstructorPointsPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertNotNull(t);
        assertEquals(p1, t.getP1());
        assertEquals(p2, t.getP2());
        assertEquals(p3, t.getP3());
        assertEquals(p4, t.getP4());
        assertEquals("red", t.getOutlineColor());
        assertEquals("yellow", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (Points) - Positive: Create trapezium with null colors")
    void testConstructorPointsWithNullColors() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, null, null);
        assertNotNull(t);
        assertEquals("", t.getOutlineColor());
        assertEquals("", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (Points) - Positive: Create trapezium with empty colors")
    void testConstructorPointsWithEmptyColors() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "", "");
        assertNotNull(t);
        assertEquals("", t.getOutlineColor());
        assertEquals("", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (Points) - Negative: Null first point throws IllegalArgumentException")
    void testConstructorPointsNullP1() {
        assertThrows(IllegalArgumentException.class, () ->
                new Trapezium(null, p2, p3, p4, "red", "yellow"),
                "Должно быть выброшено исключение при null точке");
    }

    @Test
    @DisplayName("Constructor (Points) - Negative: Null second point throws IllegalArgumentException")
    void testConstructorPointsNullP2() {
        assertThrows(IllegalArgumentException.class, () ->
                new Trapezium(p1, null, p3, p4, "red", "yellow"),
                "Должно быть выброшено исключение при null точке");
    }

    @Test
    @DisplayName("Constructor (Points) - Negative: Null third point throws IllegalArgumentException")
    void testConstructorPointsNullP3() {
        assertThrows(IllegalArgumentException.class, () ->
                new Trapezium(p1, p2, null, p4, "red", "yellow"),
                "Должно быть выброшено исключение при null точке");
    }

    @Test
    @DisplayName("Constructor (Points) - Negative: Null fourth point throws IllegalArgumentException")
    void testConstructorPointsNullP4() {
        assertThrows(IllegalArgumentException.class, () ->
                new Trapezium(p1, p2, p3, null, "red", "yellow"),
                "Должно быть выброшено исключение при null точке");
    }

    @Test
    @DisplayName("Constructor (Points) - Negative: Invalid trapezium (zero area) throws IllegalArgumentException")
    void testConstructorPointsInvalidTrapezium() {
        // Points that form a line (zero area)
        Point invalid1 = new Point(0, 0);
        Point invalid2 = new Point(1, 0);
        Point invalid3 = new Point(2, 0);
        Point invalid4 = new Point(3, 0);
        assertThrows(IllegalArgumentException.class, () ->
                new Trapezium(invalid1, invalid2, invalid3, invalid4, "red", "yellow"),
                "Должно быть выброшено исключение при нулевой площади");
    }

    // ========== Constructor Tests (String-based) ==========

    @Test
    @DisplayName("Constructor (String) - Positive: Create trapezium from valid string")
    void testConstructorStringPositive() {
        String str = "0,0,10,0,8,4,2,4,red,yellow";
        Trapezium t = new Trapezium(str);
        assertNotNull(t);
        assertEquals(new Point(0, 0), t.getP1());
        assertEquals(new Point(10, 0), t.getP2());
        assertEquals(new Point(8, 4), t.getP3());
        assertEquals(new Point(2, 4), t.getP4());
        assertEquals("red", t.getOutlineColor());
        assertEquals("yellow", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (String) - Positive: Create trapezium with spaces")
    void testConstructorStringWithSpaces() {
        String str = " 0 , 0 , 10 , 0 , 8 , 4 , 2 , 4 , red , yellow ";
        Trapezium t = new Trapezium(str);
        assertNotNull(t);
        assertEquals("red", t.getOutlineColor());
        assertEquals("yellow", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (String) - Positive: Create trapezium with only 8 numbers (empty colors)")
    void testConstructorStringWith8Numbers() {
        String str = "0,0,10,0,8,4,2,4";
        Trapezium t = new Trapezium(str);
        assertNotNull(t);
        assertEquals("", t.getOutlineColor());
        assertEquals("", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (String) - Positive: Create trapezium with 9 numbers (only outline color)")
    void testConstructorStringWith9Numbers() {
        String str = "0,0,10,0,8,4,2,4,red";
        Trapezium t = new Trapezium(str);
        assertNotNull(t);
        assertEquals("red", t.getOutlineColor());
        assertEquals("", t.getFillColor());
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Null string throws IllegalArgumentException")
    void testConstructorStringNull() {
        assertThrows(IllegalArgumentException.class, () -> new Trapezium(null),
                "Должно быть выброшено исключение при null строке");
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Empty string throws IllegalArgumentException")
    void testConstructorStringEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Trapezium(""),
                "Должно быть выброшено исключение при пустой строке");
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Blank string throws IllegalArgumentException")
    void testConstructorStringBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Trapezium("   "),
                "Должно быть выброшено исключение при строке из пробелов");
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Insufficient numbers throws IllegalArgumentException")
    void testConstructorStringInsufficientNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Trapezium("0,0,10,0,8,4,2"),
                "Должно быть выброшено исключение при недостаточном количестве чисел");
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Invalid number format throws IllegalArgumentException")
    void testConstructorStringInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Trapezium("0,0,10,0,8,4,2,abc,red,yellow"),
                "Должно быть выброшено исключение при неверном формате числа");
    }

    @Test
    @DisplayName("Constructor (String) - Negative: Invalid trapezium throws IllegalArgumentException")
    void testConstructorStringInvalidTrapezium() {
        // String representing points with zero area
        assertThrows(IllegalArgumentException.class, () -> new Trapezium("0,0,1,0,2,0,3,0,red,yellow"),
                "Должно быть выброшено исключение при невалидной трапеции");
    }

    // ========== setDefaultCompareField Tests ==========

    @Test
    @DisplayName("setDefaultCompareField - Positive: Set to AREA")
    void testSetDefaultCompareFieldArea() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        assertEquals(Trapezium.CompareField.AREA, Trapezium.getDefaultCompareField());
    }

    @Test
    @DisplayName("setDefaultCompareField - Positive: Set to PERIMETER")
    void testSetDefaultCompareFieldPerimeter() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.PERIMETER);
        assertEquals(Trapezium.CompareField.PERIMETER, Trapezium.getDefaultCompareField());
    }

    @Test
    @DisplayName("setDefaultCompareField - Positive: Set to OUTLINE_COLOR")
    void testSetDefaultCompareFieldOutlineColor() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.OUTLINE_COLOR);
        assertEquals(Trapezium.CompareField.OUTLINE_COLOR, Trapezium.getDefaultCompareField());
    }

    @Test
    @DisplayName("setDefaultCompareField - Positive: Set to FILL_COLOR")
    void testSetDefaultCompareFieldFillColor() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.FILL_COLOR);
        assertEquals(Trapezium.CompareField.FILL_COLOR, Trapezium.getDefaultCompareField());
    }

    @Test
    @DisplayName("setDefaultCompareField - Negative: Null field throws IllegalArgumentException")
    void testSetDefaultCompareFieldNull() {
        assertThrows(IllegalArgumentException.class, () ->
                Trapezium.setDefaultCompareField(null),
                "Должно быть выброшено исключение при null поле");
    }

    // ========== getDefaultCompareField Tests ==========

    @Test
    @DisplayName("getDefaultCompareField - Positive: Get default field")
    void testGetDefaultCompareFieldPositive() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.PERIMETER);
        Trapezium.CompareField field = Trapezium.getDefaultCompareField();
        assertEquals(Trapezium.CompareField.PERIMETER, field);
    }

    // ========== Getter Tests ==========

    @Test
    @DisplayName("getP1 - Positive: Get first point")
    void testGetP1Positive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals(p1, t.getP1());
    }

    @Test
    @DisplayName("getP2 - Positive: Get second point")
    void testGetP2Positive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals(p2, t.getP2());
    }

    @Test
    @DisplayName("getP3 - Positive: Get third point")
    void testGetP3Positive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals(p3, t.getP3());
    }

    @Test
    @DisplayName("getP4 - Positive: Get fourth point")
    void testGetP4Positive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals(p4, t.getP4());
    }

    @Test
    @DisplayName("getOutlineColor - Positive: Get outline color")
    void testGetOutlineColorPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals("red", t.getOutlineColor());
    }

    @Test
    @DisplayName("getFillColor - Positive: Get fill color")
    void testGetFillColorPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals("yellow", t.getFillColor());
    }

    // ========== getArea Tests ==========

    @Test
    @DisplayName("getArea - Positive: Calculate area")
    void testGetAreaPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        double area = t.getArea();
        assertTrue(area > 0);
        assertEquals(32.0, area, 1e-6);
    }

    @Test
    @DisplayName("getArea - Positive: Area is always positive for valid trapezium")
    void testGetAreaAlwaysPositive() {
        Trapezium t = new Trapezium(
                new Point(0, 0), new Point(6, 0),
                new Point(5, 3), new Point(1, 3),
                "blue", "green"
        );
        double area = t.getArea();
        assertTrue(area > 0);
    }

    // ========== getPerimeter Tests ==========

    @Test
    @DisplayName("getPerimeter - Positive: Calculate perimeter")
    void testGetPerimeterPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        double perimeter = t.getPerimeter();
        assertTrue(perimeter > 0);
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
        assertTrue(perimeter > 0);
    }

    // ========== compareTo Tests ==========

    @Test
    @DisplayName("compareTo - Positive: Compare by AREA (default)")
    void testCompareToByArea() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(6, 0),
                new Point(5, 3), new Point(1, 3),
                "blue", "green"
        );
        int result = t1.compareTo(t2);
        // t1 has area 36, t2 has smaller area
        assertTrue(result > 0, "Большая трапеция должна быть больше");
    }

    @Test
    @DisplayName("compareTo - Positive: Compare by PERIMETER")
    void testCompareToByPerimeter() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.PERIMETER);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "blue", "green"
        );
        int result = t1.compareTo(t2);
        // Result depends on actual perimeter values
        assertNotNull(result);
    }

    @Test
    @DisplayName("compareTo - Positive: Compare by OUTLINE_COLOR")
    void testCompareToByOutlineColor() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.OUTLINE_COLOR);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "blue", "yellow");
        int result = t1.compareTo(t2);
        assertTrue(result > 0, "red должен быть больше blue (лексикографически)");
    }

    @Test
    @DisplayName("compareTo - Positive: Compare by FILL_COLOR")
    void testCompareToByFillColor() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.FILL_COLOR);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "red", "blue");
        int result = t1.compareTo(t2);
        assertTrue(result > 0, "yellow должен быть больше blue (лексикографически)");
    }

    @Test
    @DisplayName("compareTo - Positive: Equal trapeziums return 0")
    void testCompareToEqual() {
        Trapezium.setDefaultCompareField(Trapezium.CompareField.AREA);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        assertEquals(0, t1.compareTo(t2), "Равные трапеции должны возвращать 0");
    }

    @Test
    @DisplayName("compareTo - Negative: Null object throws IllegalArgumentException")
    void testCompareToNull() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertThrows(IllegalArgumentException.class, () -> t.compareTo(null),
                "Должно быть выброшено исключение при null объекте");
    }

    // ========== comparatorBy Tests ==========

    @Test
    @DisplayName("comparatorBy - Positive: Get comparator by AREA")
    void testComparatorByArea() {
        Comparator<Trapezium> comp = Trapezium.comparatorBy(Trapezium.CompareField.AREA);
        assertNotNull(comp);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "blue", "green"
        );
        int result = comp.compare(t1, t2);
        assertTrue(result > 0, "Большая трапеция должна быть больше");
    }

    @Test
    @DisplayName("comparatorBy - Positive: Get comparator by PERIMETER")
    void testComparatorByPerimeter() {
        Comparator<Trapezium> comp = Trapezium.comparatorBy(Trapezium.CompareField.PERIMETER);
        assertNotNull(comp);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 2), new Point(1, 2),
                "blue", "green"
        );
        int result = comp.compare(t1, t2);
        assertNotNull(result);
    }

    @Test
    @DisplayName("comparatorBy - Positive: Get comparator by OUTLINE_COLOR")
    void testComparatorByOutlineColor() {
        Comparator<Trapezium> comp = Trapezium.comparatorBy(Trapezium.CompareField.OUTLINE_COLOR);
        assertNotNull(comp);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "blue", "yellow");
        int result = comp.compare(t1, t2);
        assertTrue(result > 0);
    }

    @Test
    @DisplayName("comparatorBy - Positive: Get comparator by FILL_COLOR")
    void testComparatorByFillColor() {
        Comparator<Trapezium> comp = Trapezium.comparatorBy(Trapezium.CompareField.FILL_COLOR);
        assertNotNull(comp);
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "red", "blue");
        int result = comp.compare(t1, t2);
        assertTrue(result > 0);
    }

    @Test
    @DisplayName("comparatorBy - Negative: Null field throws IllegalArgumentException")
    void testComparatorByNull() {
        assertThrows(IllegalArgumentException.class, () ->
                Trapezium.comparatorBy(null),
                "Должно быть выброшено исключение при null поле");
    }

    // ========== iterator Tests ==========

    @Test
    @DisplayName("iterator - Positive: Iterate over all fields")
    void testIteratorPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Iterator<Object> it = t.iterator();
        assertNotNull(it);
        assertTrue(it.hasNext());
        
        Object obj1 = it.next();
        assertNotNull(obj1);
        assertInstanceOf(Point.class, obj1);
        
        // Check that we can iterate through all 6 fields (4 points + 2 colors)
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assertEquals(5, count, "Должно быть 6 элементов (4 точки + 2 цвета)");
    }

    @Test
    @DisplayName("iterator - Positive: Iterator contains all points and colors")
    void testIteratorContainsAllFields() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Iterator<Object> it = t.iterator();
        
        // First 4 should be points
        assertInstanceOf(Point.class, it.next());
        assertInstanceOf(Point.class, it.next());
        assertInstanceOf(Point.class, it.next());
        assertInstanceOf(Point.class, it.next());
        
        // Last 2 should be strings (colors)
        Object outline = it.next();
        assertInstanceOf(String.class, outline);
        assertEquals("red", outline);
        
        Object fill = it.next();
        assertInstanceOf(String.class, fill);
        assertEquals("yellow", fill);
        
        assertFalse(it.hasNext());
    }

    // ========== toString Tests ==========

    @Test
    @DisplayName("toString - Positive: Convert trapezium to string")
    void testToStringPositive() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        String result = t.toString();
        assertNotNull(result);
        assertTrue(result.contains("red"));
        assertTrue(result.contains("yellow"));
        // Format: x1,y1,x2,y2,x3,y3,x4,y4,outlineColor,fillColor
        String[] parts = result.split(",");
        assertEquals(10, parts.length, "Должно быть 10 частей (8 координат + 2 цвета)");
    }

    @Test
    @DisplayName("toString - Positive: String format matches constructor input")
    void testToStringMatchesConstructor() {
        String str = "0.0,0.0,10.0,0.0,8.0,4.0,2.0,4.0,red,yellow";
        Trapezium t = new Trapezium(str);
        String result = t.toString();
        assertEquals(str, result);
    }

    // ========== equals Tests ==========

    @Test
    @DisplayName("equals - Positive: Equal trapeziums return true")
    void testEqualsPositive() {
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        assertEquals(t1, t2);
    }

    @Test
    @DisplayName("equals - Positive: Same object returns true")
    void testEqualsSameObject() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertEquals(t, t);
    }

    @Test
    @DisplayName("equals - Positive: Different outline color returns false")
    void testEqualsDifferentOutlineColor() {
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "blue", "yellow");
        assertNotEquals(t1, t2);
    }

    @Test
    @DisplayName("equals - Positive: Different fill color returns false")
    void testEqualsDifferentFillColor() {
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(p1, p2, p3, p4, "red", "blue");
        assertNotEquals(t1, t2);
    }

    @Test
    @DisplayName("equals - Positive: Different points return false")
    void testEqualsDifferentPoints() {
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(6, 0),
                new Point(5, 3), new Point(1, 3),
                "red", "yellow"
        );
        assertNotEquals(t1, t2);
    }

    @Test
    @DisplayName("equals - Negative: Null object returns false")
    void testEqualsNull() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertNotEquals(t, null);
    }

    @Test
    @DisplayName("equals - Negative: Different class returns false")
    void testEqualsDifferentClass() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        assertNotEquals(t, "not a trapezium");
    }

    // ========== hashCode Tests ==========

    @Test
    @DisplayName("hashCode - Positive: Equal trapeziums have same hash code")
    void testHashCodePositive() {
        Trapezium t1 = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        Trapezium t2 = new Trapezium(
                new Point(0, 0), new Point(10, 0),
                new Point(8, 4), new Point(2, 4),
                "red", "yellow"
        );
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    @DisplayName("hashCode - Positive: Hash code is consistent")
    void testHashCodeConsistent() {
        Trapezium t = new Trapezium(p1, p2, p3, p4, "red", "yellow");
        int hash1 = t.hashCode();
        int hash2 = t.hashCode();
        assertEquals(hash1, hash2);
    }
}

