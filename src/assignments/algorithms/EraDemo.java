package assignments.algorithms;

import java.util.Random;

import assignments.datastructures.VectorSet;
import assignments.datastructures.TreeSet;
import assignments.datastructures.HashSet;

public class EraDemo {

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000, 100000};

        Random random = new Random();

        System.out.println("Set,N,Time(ns)");

        for (int n : sizes) {

            // Create numbers from 1 to N.
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

            // Test VectorSet.
            VectorSet<Integer> vector = new VectorSet<>();

            for (int number : numbers) {
                vector.add(number);
            }

            long start = System.nanoTime();
            vector.contains(n + 1);
            long end = System.nanoTime();

            System.out.println("VectorSet," + n + "," + (end - start));

            // Test TreeSet.
            TreeSet<Integer> tree = new TreeSet<>();

            for (int number : numbers) {
                tree.add(number);
            }

            start = System.nanoTime();
            tree.contains(n + 1);
            end = System.nanoTime();

            System.out.println("TreeSet," + n + "," + (end - start));

            // Test HashSet.
            HashSet<Integer> hash = new HashSet<>();

            for (int number : numbers) {
                hash.add(number);
            }

            start = System.nanoTime();
            hash.contains(n + 1);
            end = System.nanoTime();

            System.out.println("HashSet," + n + "," + (end - start));
        }
    }
}
