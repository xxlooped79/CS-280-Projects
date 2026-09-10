
package assignments.algorithms;

/**
 * Computes Fibonacci numbers using Binet's Formula.
 */
public class BinetFibonacci {

    /**
     * Computes the nth Fibonacci number using Binet's Formula.
     *
     * @param n the position in the Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public static long fibonacci(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        double sqrt5 = Math.sqrt(5.0);

        double phi = (1.0 + sqrt5) / 2.0;
        double psi = (1.0 - sqrt5) / 2.0;

        double result =
                (Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5;

        return Math.round(result);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}

