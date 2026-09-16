package assignments.algorithms;

/**
 * Compute Fibonacci numbers using Binet's Formula.
 */
public class BinetFibonacci {

    public static long fibonacci(int n) {

        if (n <= 1)
            return 1;

        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;

        double answer =
                (Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / sqrt5;

        return Math.round(answer);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}