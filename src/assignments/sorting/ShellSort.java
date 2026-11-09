
package assignments.sorting;

/**
 * Implements the Shell Sort algorithm.
 *
 * Shell Sort works like insertion sort, but it starts
 * by comparing elements that are farther apart.
 * The gap gets smaller until the array is sorted.
 *
 * @param <T> the type of elements being sorted
 */
public class ShellSort<T extends Comparable<T>>
        extends SortingAlgorithm<T> {

    /**
     * Sorts an array using Shell Sort.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array) {

        int n = array.length;

        // Start with half the array size as the gap.
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // Go through the array using the gap.
            for (int i = gap; i < n; i++) {

                T temp = array[i];
                int j = i;

                // Move larger values to the right.
                while (j >= gap &&
                        temp.compareTo(array[j - gap]) < 0) {

                    array[j] = array[j - gap];
                    j -= gap;
                }

                // Put the value in its correct position.
                array[j] = temp;
            }
        }
    }

    /**
     * Tests Shell Sort using the sorting validation.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {

        SortingAlgorithm.validate(new ShellSort<Integer>());

        System.out.println("ShellSort passes all tests.");
    }
}
