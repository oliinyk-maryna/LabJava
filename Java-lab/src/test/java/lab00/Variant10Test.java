package lab00;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


class Variant10Test {

    private final Variant10 variant10 = new Variant10();

    // region integerNumberTask
    @ParameterizedTest
    @DisplayName("Test valid three-digit numbers for correct digit rearrangement")
    @CsvSource({
            "00, 100",   // tens = 0, units = 0 -> result = 00
            "10, 101",   // tens = 0, units = 1 -> result = 10
            "01, 110",   // tens = 1, units = 0 -> result = 01
            "50, 205",   // tens = 0, units = 5 -> result = 50
            "00, 300"    // tens = 0, units = 0 -> result = 00
    })
    void integerNumbersTestValidNumbers(int expected, int number) {
        assertEquals(expected, variant10.integerNumbersTask(number),
                () -> "Failed for number: " + number);
    }

    @ParameterizedTest
    @DisplayName("Test invalid arguments for integerNumbersTask method")
    @ValueSource(ints = {99, 1000, -123, 10})
    void integerNumbersTestInvalidArguments(int number) {
        assertThrows(IllegalArgumentException.class,
                () -> variant10.integerNumbersTask(number),
                () -> "Expected IllegalArgumentException for number: " + number);
    }
    // endregion

    // region booleanTask
    @ParameterizedTest
    @CsvSource({
            "1, 2, true",    // A is odd, B is even
            "2, 1, true",    // A is even, B is odd
            "4, 3, true",    // A is even, B is odd
            "3, 4, true",    // A is odd, B is even
            "2, 4, false",   // Both are even
            "1, 3, false",   // Both are odd
            "0, 0, false",   // Both are even
            "-1, -2, true",  // A is odd, B is even
            "-2, -1, true",  // A is even, B is odd
            "-3, -3, false"  // Both are odd
    })
    @DisplayName("Boolean task should return correct results based on odd/even status")
    void booleanTest(int A, int B, boolean expected) {
        assertEquals(expected, variant10.booleanTask(A, B),
                "Error with numbers A=" + A + " and B=" + B);
    }
    // endregion

    // region ifTask
    @ParameterizedTest
    @DisplayName("If task should return correct results based on equality")
    @CsvSource({
            "1, 2, 3, 3",    // A is 1, B is 2, so new values are 3 and 3
            "0, 0, 0, 0",    // A and B are equal (0), so new values are 0 and 0
            "-3, -3, 0, 0",  // A and B are equal (-3), so new values are 0 and 0
            "4, 4, 0, 0",    // A and B are equal (4), so new values are 0 and 0
            "5, 7, 12, 12"   // A is 5, B is 7, so new values are 12 and 12
    })
    void ifTest(int A, int B, int expectedA, int expectedB) {
        assertArrayEquals(new int[]{expectedA, expectedB}, variant10.ifTask(A, B));
    }
    // endregion

    // region switchTask
    @ParameterizedTest
    @DisplayName("Switch direction based on command")
    @CsvSource({
            "N, 0, N",   // Continue in the same direction
            "N, 1, W",   // Turn left from North to West
            "N, -1, E",  // Turn right from North to East
            "W, 0, W",   // Continue in the same direction
            "W, 1, S",   // Turn left from West to South
            "W, -1, N",  // Turn right from West to North
            "S, 0, S",   // Continue in the same direction
            "S, 1, E",   // Turn left from South to East
            "S, -1, W",  // Turn right from South to West
            "E, 0, E",   // Continue in the same direction
            "E, 1, N",   // Turn left from East to North
            "E, -1, S"   // Turn right from East to South
    })
    void switchTest(char initialDirection, int command, char expectedDirection) {
        assertEquals(expectedDirection, variant10.switchTask(initialDirection, command));
    }

    @ParameterizedTest
    @DisplayName("Switch invalid arguments")
    @CsvSource({
            "A, 0",  // Invalid direction
            "N, 2",  // Invalid command
            "S, -2"  // Invalid command
    })
    void switchTestInvalidArguments(char initialDirection, int command) {
        assertThrows(IllegalArgumentException.class, () -> variant10.switchTask(initialDirection, command));
    }
    // endregion

    // region forTask
    @ParameterizedTest
    @DisplayName("For series sum")
    @MethodSource("forTaskTestData")
    void forTest(double expected, int N) {
        assertEquals(expected, variant10.forTask(N), 1e-10);
    }

    static Stream<Arguments> forTaskTestData() {
        return Stream.of(
                Arguments.arguments(1.0, 1),    // Sum of series for N=1: 1
                Arguments.arguments(1.5, 2),    // Sum of series for N=2: 1 + 1/2 = 1.5
                Arguments.arguments(1.8333333333333333, 3), // Sum of series for N=3: 1 + 1/2 + 1/3 ≈ 1.8333333333333333
                Arguments.arguments(2.0833333333333335, 4)  // Sum of series for N=4: 1 + 1/2 + 1/3 + 1/4 ≈ 2.0833333333333335
        );
    }

    @ParameterizedTest
    @DisplayName("For invalid arguments")
    @CsvSource({"-1", "0"})
    void forTestInvalidArguments(int N) {
        assertThrows(IllegalArgumentException.class, () -> variant10.forTask(N));
    }
    // endregion

    // region whileTask
    @Test
    void whileTest() {
        assertEquals(4, variant10.whileTask(100));  // 3^4 = 81 < 100 but 3^5 = 243 is not less than 100
        assertEquals(0, variant10.whileTask(3));    // 3^0 = 1 < 3 but 3^1 = 3 is not less than 3
        assertEquals(2, variant10.whileTask(10));   // 3^2 = 9 < 10 but 3^3 = 27 is not less than 10
    }

    @ParameterizedTest
    @DisplayName("While invalid arguments")
    @ValueSource(ints = {0, 1})
    void whileTestInvalidArguments(int N) {
        assertThrows(IllegalArgumentException.class, () -> variant10.whileTask(N));
    }
    // endregion

    // region arrayTask
    @ParameterizedTest
    @DisplayName("Array processing test")
    @MethodSource("arrayTaskTestData")
    void arrayTest(int[] expectedArr, int[] argArray, String message) {
        assertArrayEquals(expectedArr, variant10.arrayTask(argArray), message);
    }

    static Stream<Arguments> arrayTaskTestData() {
        return Stream.of(
                Arguments.arguments(new int[]{2, 4, 5, 3, 1}, new int[]{1, 2, 3, 4, 5}, "Error with mixed odd and even numbers"),
                Arguments.arguments(new int[]{2, 4, 3, 1}, new int[]{1, 2, 3, 4}, "Error with mixed even and odd numbers"),
                Arguments.arguments(new int[]{1}, new int[]{1}, "Error with single element array"),
                Arguments.arguments(new int[]{}, new int[]{}, "Error with an empty array")
        );
    }
    // endregion

    // region twoDimensionArrayTask
    @Test
    @DisplayName("Test matrix with multiple odd-numbered columns")
    void testMatrixWithMultipleOddColumns() {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };
        int[] expected = {2, 7, 12, 4, 9, 14}; // Columns 1, 3, 5
        assertArrayEquals(expected, variant10.twoDimensionArrayTask(matrix));
    }

    @Test
    @DisplayName("Test matrix with only one column")
    void testMatrixWithOneColumn() {
        int[][] matrix = {
                {1},
                {2},
                {3}
        };
        int[] expected = {};
        assertArrayEquals(expected, variant10.twoDimensionArrayTask(matrix));
    }

    @Test
    @DisplayName("Test matrix with only odd-numbered columns")
    void testMatrixWithOnlyOddColumns() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] expected = {2, 5, 8}; // Columns 1
        assertArrayEquals(expected, variant10.twoDimensionArrayTask(matrix));
    }

    @Test
    @DisplayName("Test matrix with no columns")
    void testMatrixWithNoColumns() {
        int[][] matrix = {};
        int[] expected = {};
        assertArrayEquals(expected, variant10.twoDimensionArrayTask(matrix));
    }

    @Test
    @DisplayName("Test matrix with empty rows")
    void testMatrixWithEmptyRows() {
        int[][] matrix = {
                {},
                {},
                {}
        };
        int[] expected = {};
        assertArrayEquals(expected, variant10.twoDimensionArrayTask(matrix));
    }
    // endregion
}
