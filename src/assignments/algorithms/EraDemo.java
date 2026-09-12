package assignments.algorithms;

/**
 * Measures the time required to compute Fibonacci numbers
 * using different algorithms.
 */
public class EraDemo {

    /**
     * Measures how long a Fibonacci method takes to run.
     *
     * @param n Fibonacci index
     * @return elapsed time in nanoseconds
     */
    private static long timeDynamic(int n) {
        long start = System.nanoTime();

        DynamicFibonacci.fibonacci(n);

        long end = System.nanoTime();

        return end - start;
    }

    private static long timeBinet(int n) {
        long start = System.nanoTime();

        BinetFibonacci.fibonacci(n);

        long end = System.nanoTime();

        return end - start;
    }

    private static long timeRecursive(int n) {
        long start = System.nanoTime();

        RecursiveFibonacci.fibonacci(n);

        long end = System.nanoTime();

        return end - start;
    }

    public static void main(String[] args) {

        System.out.println("N\tDynamic\tBinet\tRecursive");

        for (int n = 1; n <= 40; n++) {

            long dynamicTime = timeDynamic(n);
            long binetTime = timeBinet(n);
            long recursiveTime = timeRecursive(n);

            System.out.println(
                n + "," +
                dynamicTime + "," +
                binetTime + "," +
                recursiveTime
            );
        }
    }
}