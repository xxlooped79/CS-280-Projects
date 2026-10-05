package assignments.sorting;

import java.util.Arrays;

/**
 * Superclass to ensure all sorting algorithms operate
 * in a consistent manner.
 *
 * @param <T> the type of object to be sorted
 */
public abstract class SortingAlgorithm<T extends Comparable<T>> {

    /**
     * Sort an array in-place.
     *
     * @param array the array to sort
     */
    public abstract void sort(T[] array);

    /**
     * Construct a sorted version of an array.
     *
     * @param array the array to sort
     * @return the sorted array
     */
    public T[] sorted(T[] array) {
        T[] copiedArray = Arrays.copyOf(array, array.length);
        sort(copiedArray);
        return copiedArray;
    }

    /**
     * Default constructor.
     */
    public SortingAlgorithm() {}

    /**
     * Test a sorting algorithm with a standard test.
     *
     * @param algorithm a freshly initialized SortingAlgorithm object
     */
    public static void validate(SortingAlgorithm<Integer> algorithm) {

        // Sample array with negatives and duplicates.
        Integer[] random_array = {
            -7, 4, 3, 5, -9, 1, -6, 7, -5, -9, -1, 1, 7, 7, 6
        };

        Integer[] sorted_array = {
            -9, -9, -7, -6, -5, -1, 1, 1, 3, 4, 5, 6, 7, 7, 7
        };

        // Copy the sample array twice.
        Integer[] tosort_array =
            Arrays.copyOf(random_array, random_array.length);

        Integer[] copied_array =
            Arrays.copyOf(random_array, random_array.length);

        // Test sort().
        algorithm.sort(tosort_array);

        assert Arrays.equals(sorted_array, tosort_array) :
            String.format(
                """

                %s.sort() produced incorrect array

                Original: %s
                Expected: %s
                Encountered: %s

                """,
                algorithm.getClass().getSimpleName(),
                Arrays.toString(random_array),
                Arrays.toString(sorted_array),
                Arrays.toString(tosort_array)
            );

        // Test sorted().
        Integer[] tested_array = algorithm.sorted(copied_array);

        assert Arrays.equals(random_array, copied_array) :
            String.format(
                """

                %s.sorted() modified input array

                Original: %s
                Encountered: %s

                """,
                algorithm.getClass().getSimpleName(),
                Arrays.toString(random_array),
                Arrays.toString(copied_array)
            );

        assert Arrays.equals(sorted_array, tested_array) :
            String.format(
                """

                %s.sorted() produced incorrect array

                Original: %s
                Expected: %s
                Encountered: %s

                """,
                algorithm.getClass().getSimpleName(),
                Arrays.toString(random_array),
                Arrays.toString(sorted_array),
                Arrays.toString(tested_array)
            );

        System.out.println(String.format(
            "* %s passes sorting validation.",
            algorithm.getClass().getSimpleName()
        ));
    }

    /**
     * Tests the InsertionSort implementation.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {
        ;
    }
}