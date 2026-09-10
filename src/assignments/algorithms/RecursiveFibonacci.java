package assignments.algorithms;

/**
 * Computes Fibonacci numbers using recursion.
 */
public class RecursiveFibonacci {

    /**
     * Computes the nth Fibonacci number recursively.
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

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}

