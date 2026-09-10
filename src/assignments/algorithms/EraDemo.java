package assignments.algorithms;
public class EraDemo {

    public static void main(String[] args) {

        System.out.println("N\tDynamic\tBinet\tRecursive");

        for (int N = 1; N <= 40; N++) {

            // Dynamic Programming
            long start = System.nanoTime();

            DynamicFibonacci.fibonacci(N);

            long end = System.nanoTime();

            double dynamicTime = (end - start) / 1e9;


            // Binet's Formula
            start = System.nanoTime();

            BinetFibonacci.fibonacci(N);

            end = System.nanoTime();

            double binetTime = (end - start) / 1e9;


            // Recursion
            start = System.nanoTime();

            RecursiveFibonacci.fibonacci(N);

            end = System.nanoTime();

            double recursiveTime = (end - start) / 1e9;


            System.out.println(
                    N + "\t" +
                    dynamicTime + "\t" +
                    binetTime + "\t" +
                    recursiveTime
            );
        }
    }
}