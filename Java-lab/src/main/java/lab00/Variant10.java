package lab00;

public class Variant10 {

    /**
     * Takes a three-digit number and returns a new number with the last digit (units) followed by the middle digit (tens).
     * <p>
     * For example, if the input is 123, the method will return 32
     *
     * @param number a three-digit natural number
     * @return a new number with the last digit (units) followed by the middle digit (tens)
     */
    public int integerNumbersTask(int number) {
        if (number < 100 || number > 999) {
            throw new IllegalArgumentException("The number must be a three-digit");
        }

        int tens = (number / 10) % 10;
        int units = number % 10;

        return units * 10 + tens;
    }

    /**
     * @param a first integer to check
     * @param b second integer to check
     * @return true, if exactly one of the numbers A and B is odd
     */
    public boolean booleanTask(int a, int b) {
        return (a % 2 != 0 && b % 2 == 0) || (a % 2 == 0 && b % 2 != 0);
    }

    /**
     * Takes two integer variables A and B. If their values are not equal, assigns the sum of these values
     * to both A and B. If their values are equal, assigns zero to both A and B.
     *
     * @param a first integer variable
     * @param b second integer variable
     * @return an array containing the new values of A and B
     */
    public int[] ifTask(int a, int b) {

        if (a != b) {
            int sum = a + b;
            a = sum;
            b = sum;
        } else {
            a = 0;
            b = 0;
        }

        return new int[]{a, b};
    }

    /**
     * Determines the new direction of the robot after receiving a command.
     * The robot can move in four directions:
     * 'N' - North, 'W' - West, 'S' - South, 'E' - East.
     * The robot receives three possible commands:
     * 0 - continue in the same direction,
     * 1 - turn left,
     * -1 - turn right.
     *
     * @param c initial direction ('N', 'W', 'S', 'E')
     * @param n command (0 - continue, 1 - turn left, -1 - turn right)
     * @return new direction after executing the command
     * @throws IllegalArgumentException if the initial direction or command is invalid
     */
    public char switchTask(char c, int n) {
        int currentDirectionIndex = switch (c) {
            case 'N' -> 0;
            case 'W' -> 1;
            case 'S' -> 2;
            case 'E' -> 3;
            default -> throw new IllegalArgumentException("Invalid initial direction.");
        };

        if (n < -1 || n > 1) {
            throw new IllegalArgumentException("Invalid command. Command must be 0, 1, or -1.");
        }

        char[] directions = {'N', 'W', 'S', 'E'};

        int newDirectionIndex = (currentDirectionIndex + n + 4) % 4;
        return directions[newDirectionIndex];

    }

    /**
     * Finds the sum of the series 1 + 1/2 + 1/3 + ... + 1/N
     *
     * @param n a positive integer
     * @return the sum of the series
     * @throws IllegalArgumentException if n is 0 or less
     */
    public double forTask(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }

        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }

        return sum;
    }

    /**
     * Finds the largest integer K such that 3^k < n.
     *
     * @param n a positive integer greater than 1
     * @return the largest integer K such that 3^k < n
     * @throws IllegalArgumentException if n is less than or equal to 1
     */
    public int whileTask(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException("N must be greater than 1");
        }

        int k = 0;
        int powerOfThree = 1;

        // Find the largest K such that 3^K < N
        while (powerOfThree * 3 < n) {
            powerOfThree *= 3;
            k++;
        }

        return k;
    }

    /**
     * Processes an integer array and returns a new array where all even numbers are first in ascending index order,
     * followed by all odd numbers in descending index order.
     *
     * @param array an integer array
     * @return a new integer array with even numbers first (ascending order by index) and odd numbers last (descending order by index)
     */
    public int[] arrayTask(int[] array) {
        int n = array.length;
        int[] result = new int[n];

        int evenIndex = 0;
        int oddIndex = n - 1;

        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                result[evenIndex++] = array[i];  // Add even numbers to the beginning
            } else {
                result[oddIndex--] = array[i];   // Add odd numbers to the end
            }
        }

        return result;
    }

    /**
     * Outputs the elements of the matrix located in columns with odd numbers (1, 3, 5, ...)
     * The elements are output column by column.
     *
     * @param matrix a two-dimensional integer array of size M x N
     * @return an array of elements located in odd-numbered columns
     */
    public int[] twoDimensionArrayTask(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int resultSize = n / 2 * m;
        int[] result = new int[resultSize];

        int index = 0;

        for (int j = 1; j < n; j += 2) {
            for (int i = 0; i < m; i++) {
                result[index++] = matrix[i][j];
            }
        }

        return result;
    }
//
//    public static void main(String... strings) {
//        System.out.println("Start of zero lab");
//        System.out.println("Done!!!");
//    }

}