package assignments.algorithms;

/**
 * Quick sort implementation.
 */
public class QuickSort extends SortingAlgorithm<Integer> {

    public QuickSort() {}

    @Override
    public void sort(Integer[] array) {
        if (array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(Integer[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(Integer[] array, int low, int high) {
        int pivot = array[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                Integer temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        Integer temp = array[i];
        array[i] = array[high];
        array[high] = temp;

        return i;
    }

    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort());
        System.out.println("QuickSort passes all tests.");
    }
}