package assignments.algorithms;

import java.util.Random;
import assignments.sorting.*;

/**
 * Measures how long eight sorting algorithms take
 * to sort arrays of random integers.
 *
 * The time used to create the arrays is not included.
 */
public class EraDemo {

    /**
     * Creates an array of random integers.
     *
     * @param n the number of elements
     * @return an array with random numbers from 0 to n - 1
     */
    public static Integer[] randomArray(int n) {
        Random random = new Random();
        Integer[] array = new Integer[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }

        return array;
    }

    /**
     * Measures how long an algorithm takes to sort an array.
     *
     * @param algorithm the sorting algorithm
     * @param n the size of the array
     * @return the runtime in seconds
     */
    public static double timeSort(
            SortingAlgorithm<Integer> algorithm, int n) {

        Integer[] array = randomArray(n);

        long start = System.nanoTime();

        algorithm.sort(array);

        long end = System.nanoTime();

        return (end - start) / 1_000_000_000.0;
    }

    /**
     * Runs all eight sorting algorithms and prints
     * the results in CSV format.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        System.out.println(
            "N,Bubble,Insertion,Selection,Quick,Merge,Tree,Heap,Bucket"
        );

        int[] sizes = {
            100, 500, 1000, 2000,
            4000, 8000, 16000, 32000
        };

        for (int n : sizes) {

            double bubble = timeSort(new BubbleSort(), n);
            double insertion = timeSort(new InsertionSort(), n);
            double selection = timeSort(new SelectionSort(), n);
            double quick = timeSort(new QuickSort(), n);
            double merge = timeSort(new MergeSort(), n);
            double tree = timeSort(new TreeSort(), n);
            double heap = timeSort(new HeapSort(), n);
            double bucket = timeSort(new BucketSort(), n);

            System.out.println(
                n + "," +
                bubble + "," +
                insertion + "," +
                selection + "," +
                quick + "," +
                merge + "," +
                tree + "," +
                heap + "," +
                bucket
            );
        }
    }
}
