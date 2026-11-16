package assignments.algorithms;

import java.util.Random;
import assignments.datastructures.VectorSet;
import assignments.datastructures.TreeSet;
import assignments.datastructures.HashSet;
public class EraDemo {

    public static void main(String[] args) {

        int[] sizes = {10, 50, 100, 500, 1000, 2000,
                       5000, 10000, 20000, 50000, 100000};

        int repeats = 10000;
        Random random = new Random();

        System.out.println("Set,N,Time(ns)");

        for (int n : sizes) {

            int[] numbers = new int[n];

            for (int i = 0; i < n; i++) {
                numbers[i] = i + 1;
            }

            // Put numbers in random order.
            for (int i = n - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);

                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }

            // VectorSet
            VectorSet<Integer> vector = new VectorSet<>();

            for (int number : numbers) {
                vector.add(number);
            }

            long start = System.nanoTime();

            for (int i = 0; i < repeats; i++) {
                vector.contains(n + 1);
            }

            long end = System.nanoTime();

            System.out.println("VectorSet," + n + ","
                    + (end - start) / repeats);

            // TreeSet
            TreeSet<Integer> tree = new TreeSet<>();

            for (int number : numbers) {
                tree.add(number);
            }

            start = System.nanoTime();

            for (int i = 0; i < repeats; i++) {
                tree.contains(n + 1);
            }

            end = System.nanoTime();

            System.out.println("TreeSet," + n + ","
                    + (end - start) / repeats);

            // HashSet
            HashSet<Integer> hash = new HashSet<>();

            for (int number : numbers) {
                hash.add(number);
            }

            start = System.nanoTime();

            for (int i = 0; i < repeats; i++) {
                hash.contains(n + 1);
            }

            end = System.nanoTime();

            System.out.println("HashSet," + n + ","
                    + (end - start) / repeats);
        }
    }
}
