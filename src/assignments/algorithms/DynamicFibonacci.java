package assignments.algorithms;
/**
 * Computes Fibonacci numbers using dynamic programming.
 */
public class DynamicFibonacci {

    /**
     * Computes the nth Fibonacci number.
     *
     * @param n the position in the Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public static long fibonacci(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        if (n <= 1) {
            return n;
        }

        long[] values = new long[n + 1];

        values[0] = 0;
        values[1] = 1;

        for (int i = 2; i <= n; i++) {
            values[i] = values[i - 1] + values[i - 2];
        }

        return values[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}
