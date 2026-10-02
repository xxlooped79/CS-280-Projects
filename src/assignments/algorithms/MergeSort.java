package assignments.algorithms;

import assignments.sorting.SortingAlgorithm;

/**
 * This class uses MergeSort to sort an array of integers.
 */
public class MergeSort extends SortingAlgorithm<Integer> {

    /**
     * Creates a MergeSort object.
     */
    public MergeSort() {}

    /**
     * Sorts the array using MergeSort.
     *
     * @param array the array that needs to be sorted
     */
    @Override
    public void sort(Integer[] array) {
        // If there is only one value or no values, there is nothing to sort.
        if (array.length <= 1) {
            return;
        }

        // Make a work array to help with merging.
        Integer[] work = new Integer[array.length];

        // Start sorting the whole array.
        mergeSort(array, work, 0, array.length - 1);
    }

    /**
     * Splits the array into smaller parts and sorts them.
     *
     * @param array the array being sorted
     * @param work the extra array used while sorting
     * @param low the starting index
     * @param high the ending index
     */
    private void mergeSort(Integer[] array, Integer[] work, int low, int high) {
        if (low < high) {
            // Find the middle of this part of the array.
            int middle = (low + high) / 2;

            // Sort the left and right sides.
            mergeSort(array, work, low, middle);
            mergeSort(array, work, middle + 1, high);

            // Merge both sides back together.
            merge(array, work, low, middle, high);
        }
    }

    /**
     * Merges two sorted parts of the array back together.
     *
     * @param array the array being sorted
     * @param work the extra array used while merging
     * @param low the starting index
     * @param middle the middle index
     * @param high the ending index
     */
    private void merge(Integer[] array, Integer[] work,
                       int low, int middle, int high) {

        // Copy the values into the work array.
        for (int i = low; i <= high; i++) {
            work[i] = array[i];
        }

        // Keep track of where we are in each part of the array.
        int leftIndex = low;
        int rightIndex = middle + 1;
        int arrayIndex = low;

        // Compare both sides and put the smaller value back first.
        while (leftIndex <= middle && rightIndex <= high) {
            if (work[leftIndex] <= work[rightIndex]) {
                array[arrayIndex] = work[leftIndex];
                leftIndex++;
            } else {
                array[arrayIndex] = work[rightIndex];
                rightIndex++;
            }

            arrayIndex++;
        }

        // Add any values that are left on the left side.
        while (leftIndex <= middle) {
            array[arrayIndex] = work[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        // Add any values that are left on the right side.
        while (rightIndex <= high) {
            array[arrayIndex] = work[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
    }

    /**
     * Tests the MergeSort code.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort());
        System.out.println("MergeSort passes all tests.");
    }
}