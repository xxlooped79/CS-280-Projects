package assignments.sorting;

/**
 * Move each element into its correct position
 * within the already sorted portion of the array.
 */
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
    /**
     * Sort an array in-place using insertion sort.
     *
     * Post-condition: 'array' is sorted in ascending order.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array)
    {
        // Start at the second element because the first
        // element is already considered sorted.
        for (int i = 1; i < array.length; i++)
        {
            // Store the current element.
            T key = array[i];

            // Start comparing with the element to the left.
            int j = i - 1;

            // Move larger elements one position to the right.
            while (j >= 0 && array[j].compareTo(key) > 0)
            {
                array[j + 1] = array[j];
                j--;
            }

            // Insert the key into its correct position.
            array[j + 1] = key;
        }
    }

    /**
     * Default constructor.
     */
    public InsertionSort() {}

    public static void main(String[] args)
    {
        // Test the InsertionSort implementation.
        SortingAlgorithm.validate(new InsertionSort<Integer>());

        System.out.println("InsertionSort has passed all tests");

        // Fill an array with random numbers.
        int N = 8;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int)(N * Math.random());
        }

        // Create an InsertionSort object.
        SortingAlgorithm<Integer> sorter = new InsertionSort<Integer>();

        // Start measuring the runtime.
        long start = System.nanoTime();

        // Sort the array.
        sorter.sort(array);

        // Stop measuring the runtime.
        long end = System.nanoTime();

        // Convert nanoseconds to seconds.
        double duration = (end - start) / 1e9;

        System.out.println("Array size " + N);
        System.out.println("Total duration: " + duration);
    }
}