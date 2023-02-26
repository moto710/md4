package org.example;

import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("JUnitExampleTest")
public class JUnitExampleTest {

    @DisplayName("Test before all")
    @BeforeAll
    static void setup() {
        System.out.println("Before all");
    }
    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }
    @Test
    @DisplayName("My test method")
    public void test() {
        System.out.println("Test");
    }
    @Test
    @Disabled
    public void disable() {
        System.out.println("disable");
    }

    @Test
    void assertTrueEx() {
        assertTrue(Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum() < 5, () -> "Sum should be greater than 5");
    }

    @Test
    void assertEqualsEx() {
        assertEquals(1, 5 + 2);
    }

    @Test
    void assertAllEx() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 1),
                () -> assertEquals(3, numbers[3], "should be 3"),
                () -> assertEquals(numbers[4], 1)
        );
    }

    @Test
    public void assumpTrue() {
        int x = 5, y = 4;
        assumeTrue(x < y);
        assertTrue(x - y < 0);
    }

    @Test
    public void assumpFalse() {
        int x = 5, y = 4;
        assumeFalse(x < y);
        assertTrue(x - y < 0);
    }

    @Test
    void assumptionThat() {
        String someString = "Just a string";
        assumingThat(
                someString.equals("Just a string"),
                () -> assertEquals(2 + 2, 4)
        );
    }

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }
}
