package assignments.sorting;

/**
 * Find the smallest element and move it into its correct
 * position until the whole array is sorted.
 */
public class SelectionSort extends SortingAlgorithm
{
    /**
     * Sort an array in-place using selection sort.
     *
     * Post-condition: 'array' is sorted in ascending order.
     *
     * @param array an array of integers
     */
    public void sort(Integer[] array)
    {
        // Go through each position in the array.
        // The last position does not need to be checked
        // because the remaining elements will already be sorted.
        for (int i = 0; i < array.length - 1; i++)
        {
            // Assume the current position contains
            // the smallest element.
            int minIndex = i;

            // Search the unsorted portion of the array
            // for an element smaller than the current minimum.
            for (int j = i + 1; j < array.length; j++)
            {
                // If a smaller element is found,
                // remember its index.
                if (array[j] < array[minIndex])
                {
                    minIndex = j;
                }
            }

            // Swap the smallest element found with
            // the element at the current position.
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
    private void swap(Integer[] array, int i, int j)
    {
        // Temporarily store the first value.
        Integer temp = array[i];

        // Move the second value into the first position.
        array[i] = array[j];

        // Move the original first value into the second position.
        array[j] = temp;
    }

    public static void main(String[] args)
    {
        // Test the SelectionSort implementation
        // using the validation method from SortingAlgorithm.
        SortingAlgorithm.validate(new SelectionSort());

        System.out.println("SelectionSort has passed all tests");

        // Fill an array with random numbers.
        int N = 3;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int)(N * Math.random());
        }

        // Create a SelectionSort object.
        SortingAlgorithm sorter = new SelectionSort();

        // Start measuring the runtime.
        long start = System.nanoTime();

        // Sort the array.
        sorter.sort(array);

        // Stop measuring the runtime.
        long end = System.nanoTime();

        // Convert the runtime from nanoseconds to seconds.
        double duration = (end - start) / (1e9);

        System.out.println("Array size " + N);
        System.out.println("Total duration: " + duration);
    }
}