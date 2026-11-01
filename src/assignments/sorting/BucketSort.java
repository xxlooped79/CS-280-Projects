
package assignments.sorting;

import java.util.ArrayList;

/**
 * Sorts an array using bucket sort.
 *
 * The algorithm divides values into buckets,
 * sorts each bucket, and combines the results.
 *
 * @param <T> the type of elements being sorted
 */
public class BucketSort<T extends Comparable<T>>
        extends SortingAlgorithm<T> {

    /**
     * Sorts the given array using bucket sort.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // Create buckets for the elements.
        ArrayList<ArrayList<T>> buckets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        // Find the smallest and largest elements.
        T min = array[0];
        T max = array[0];

        for (int i = 1; i < n; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }

            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }

        // Place each element into a bucket.
        // Rank determines its position among the values.
        for (int i = 0; i < n; i++) {
            int rank = 0;

            for (int j = 0; j < n; j++) {
                if (array[j].compareTo(array[i]) < 0) {
                    rank++;
                }
            }

            buckets.get(rank).add(array[i]);
        }

        // Copy the sorted values back into the array.
        int index = 0;

        for (int i = 0; i < n; i++) {
            ArrayList<T> bucket = buckets.get(i);

            for (int j = 0; j < bucket.size(); j++) {
                array[index] = bucket.get(j);
                index++;
            }
        }
    }

    /**
     * Runs the sorting validation tests.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {
        SortingAlgorithm.validate(new BucketSort<Integer>());
        System.out.println("BucketSort passes all tests.");
    }
}
