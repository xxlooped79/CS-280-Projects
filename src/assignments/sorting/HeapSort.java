package assignments.sorting;
/**
 * A sorting algorithm that uses a max heap.
 *
 * Heap sort organizes the array into a max heap,
 * where the largest value is at the root.
 * It then moves the largest value to the end
 * of the array until everything is sorted.
 *
 * The time complexity is O(N log N).
 *
 * @param <T> the type of elements being sorted
 */
public class HeapSort<T extends Comparable<T>>
        extends SortingAlgorithm<T> {

    /**
     * Sorts an array using heap sort.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array) {
        int n = array.length;

        // Build a max heap.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Move the largest value to the end.
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);

            // Fix the remaining heap.
            heapify(array, i, 0);
        }
    }

    /**
     * Fixes the heap so the largest value
     * is at the root of the current subtree.
     *
     * @param array the array containing the heap
     * @param size the size of the heap
     * @param root the index of the current root
     */
    private void heapify(T[] array, int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // Check the left child.
        if (left < size &&
                array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        // Check the right child.
        if (right < size &&
                array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        // Swap if the root is not the largest.
        if (largest != root) {
            swap(array, root, largest);

            // Fix the affected subtree.
            heapify(array, size, largest);
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param array the array
     * @param i the first index
     * @param j the second index
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Tests the heap sort algorithm.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {
        HeapSort<Integer> sorter = new HeapSort<>();

        Integer[] numbers = {8, 3, 5, 1, 9, 2, 7};

        sorter.sort(numbers);

        System.out.println("Sorted array:");

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        // Check that the numbers are sorted.
        for (int i = 1; i < numbers.length; i++) {
            assert numbers[i - 1] <= numbers[i];
        }

        System.out.println("HeapSort passes all tests.");
    }
}
