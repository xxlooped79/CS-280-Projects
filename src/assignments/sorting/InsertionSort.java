package assignments.sorting;

/**
 * Move each element into its correct position
 * within the already sorted portion of the array.
 */
public class InsertionSort extends SortingAlgorithm
{
    /**
     * Sort an array in-place using insertion sort.
     *
     * Post-condition: 'array' is sorted in ascending order.
     *
     * @param array an array of integers
     */
    public void sort(Integer[] array)
    {
        // Start at the second element because the first
        // element is already considered sorted.
        for (int i = 1; i < array.length; i++)
        {
            // Store the current element that needs
            // to be inserted into the sorted portion.
            Integer key = array[i];

            // Start comparing the key with the element
            // immediately to its left.
            int j = i - 1;

            // Move larger elements one position to the right
            // until the correct position for the key is found.
            while (j >= 0 && array[j] > key)
            {
                // Shift the larger element to the right.
                array[j + 1] = array[j];

                // Move one position to the left.
                j--;
            }

            // Insert the key into its correct position.
            array[j + 1] = key;
        }
    }

    public static void main(String[] args)
    {
        // Test the InsertionSort implementation
        // using the validation method from SortingAlgorithm.
        SortingAlgorithm.validate(new InsertionSort());

        System.out.println("InsertionSort has passed all tests");

        // Fill an array with random numbers.
        int N = 8;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int)(N * Math.random());
        }

        // Create an InsertionSort object.
        SortingAlgorithm sorter = new InsertionSort();

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
