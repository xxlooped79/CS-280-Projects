package assignments.algorithms;

import assignments.sorting.SortingAlgorithm;

/**
 * Merge sort implementation.
 */
public class MergeSort extends SortingAlgorithm<Integer> {

    public MergeSort() {}

    @Override
    public void sort(Integer[] array) {
        if (array.length <= 1) {
            return;
        }

        Integer[] work = new Integer[array.length];

        mergeSort(array, work, 0, array.length - 1);
    }

    private void mergeSort(Integer[] array, Integer[] work, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;

            mergeSort(array, work, low, middle);
            mergeSort(array, work, middle + 1, high);

            merge(array, work, low, middle, high);
        }
    }

    private void merge(Integer[] array, Integer[] work,
                       int low, int middle, int high) {

        for (int i = low; i <= high; i++) {
            work[i] = array[i];
        }

        int leftIndex = low;
        int rightIndex = middle + 1;
        int arrayIndex = low;

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

        while (leftIndex <= middle) {
            array[arrayIndex] = work[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex <= high) {
            array[arrayIndex] = work[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort());
        System.out.println("MergeSort passes all tests.");
    }
}