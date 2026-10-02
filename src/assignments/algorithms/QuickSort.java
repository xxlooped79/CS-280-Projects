package assignments.algorithms;

import assignments.sorting.SortingAlgorithm;

/**
 * This class uses QuickSort to sort an array of integers.
 */
public class QuickSort extends SortingAlgorithm<Integer> {

    /**
     * Creates a QuickSort object.
     */
    public QuickSort() {}

    /**
     * Sorts the array using QuickSort.
     *
     * @param array the array that needs to be sorted
     */
    @Override
    public void sort(Integer[] array) {
        // If there is only one value or no values, there is nothing to sort.
        if (array.length <= 1) {
            return;
        }

        // Start sorting the whole array.
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Sorts smaller parts of the array using recursion.
     *
     * @param array the array being sorted
     * @param low the starting index
     * @param high the ending index
     */
    private void quickSort(Integer[] array, int low, int high) {
        if (low < high) {
            // Find where the pivot should go.
            int pivotIndex = partition(array, low, high);

            // Sort the left and right sides of the pivot.
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Splits the array around a pivot value.
     *
     * @param array the array being sorted
     * @param low the starting index
     * @param high the ending index
     * @return the position of the pivot
     */
    private int partition(Integer[] array, int low, int high) {
        // Use the last value as the pivot.
        int pivot = array[high];
        int i = low;

        // Go through the values and move smaller ones to the left.
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                // Swap the two values.
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        // Move the pivot into the correct spot.
        Integer temp = array[i];
        array[i] = array[high];
        array[high] = temp;

        return i;
    }

    /**
     * Tests the QuickSort code.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort());
        System.out.println("QuickSort passes all tests.");
    }
}