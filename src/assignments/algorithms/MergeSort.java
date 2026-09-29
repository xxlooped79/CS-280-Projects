package assignments.algorithms;

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

        int middle = array.length / 2;

        Integer[] left = new Integer[middle];
        Integer[] right = new Integer[array.length - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }

        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }

        sort(left);
        sort(right);

        merge(array, left, right);
    }

    private void merge(Integer[] array, Integer[] left, Integer[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[arrayIndex] = left[leftIndex];
                leftIndex++;
            } else {
                array[arrayIndex] = right[rightIndex];
                rightIndex++;
            }

            arrayIndex++;
        }

        while (leftIndex < left.length) {
            array[arrayIndex] = left[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex < right.length) {
            array[arrayIndex] = right[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort());
    }
}