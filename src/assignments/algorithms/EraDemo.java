package assignments.algorithms;

public class EraDemo {

    private static double timeDynamic(int n) {
        long start = System.nanoTime();

        DynamicFibonacci.fibonacci(n);

        long end = System.nanoTime();

        double duration = (end - start) / 1e9;

        return duration;
    }

    private static double timeBinet(int n) {
        long start = System.nanoTime();

        BinetFibonacci.fibonacci(n);

        long end = System.nanoTime();

        double duration = (end - start) / 1e9;

        return duration;
    }

    private static double timeRecursive(int n) {
        long start = System.nanoTime();

        RecursiveFibonacci.fibonacci(n);

        long end = System.nanoTime();

        double duration = (end - start) / 1e9;

        return duration;
    }

    public static void main(String[] args) {

        System.out.println("N\tDynamic\tBinet\tRecursive");

        for (int n = 1; n <= 40; n++) {

            double dynamicTime = timeDynamic(n);
            double binetTime = timeBinet(n);
            double recursiveTime = timeRecursive(n);

            System.out.println(
                n + "\t" +
                dynamicTime + "\t" +
                binetTime + "\t" +
                recursiveTime
            );
        }
    }
}