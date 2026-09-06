package assignments.sorting;

/**
 * Find the smallest element and move it into its correct
 * position until the whole array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
    /**
     * Sort an array in-place using selection sort.
     *
     * Post-condition: 'array' is sorted in ascending order.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array)
    {
        // Go through each position in the array.
        for (int i = 0; i < array.length - 1; i++)
        {
            // Assume the current position contains
            // the smallest element.
            int minIndex = i;

            // Search the unsorted portion of the array.
            for (int j = i + 1; j < array.length; j++)
            {
                // Compare generic elements.
                if (array[j].compareTo(array[minIndex]) < 0)
                {
                    minIndex = j;
                }
            }

            // Swap the smallest element into position.
            swap(array, i, minIndex);
        }
    }

    /**
     * Swap two elements within an array.
     *
     * @param array the array to swap values in
     * @param i the first index to swap
     * @param j the second index to swap
     */
    private void swap(T[] array, int i, int j)
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Default constructor.
     */
    public SelectionSort() {}

    public static void main(String[] args)
    {
        // Test the SelectionSort implementation.
        SortingAlgorithm.validate(new SelectionSort<Integer>());

        System.out.println("SelectionSort has passed all tests");

        // Fill an array with random numbers.
        int N = 3;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int)(N * Math.random());
        }

        // Create a SelectionSort object.
        SortingAlgorithm<Integer> sorter = new SelectionSort<Integer>();

        // Start measuring the runtime.
        long start = System.nanoTime();

        // Sort the array.
        sorter.sort(array);

        // Stop measuring the runtime.
        long end = System.nanoTime();

        // Convert the runtime from nanoseconds to seconds.
        double duration = (end - start) / 1e9;

        System.out.println("Array size " + N);
        System.out.println("Total duration: " + duration);
    }
}