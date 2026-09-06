package assignments.sorting;

/**
 * Swap adjacent elements over and over until the whole array is sorted.
 */
public class Bubblesort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
    /**
     * Sorting an array in-place using bubble sort.
     *
     * Post-condition: 'array' is sorted in ascending order.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array)
    {
        for (int k = array.length; k >= 2; k--)
        {
            // k is the length of the unsorted section.
            for (int i = 0; i < k - 1; i++)
            {
                // Compare adjacent elements.
                if (array[i].compareTo(array[i + 1]) > 0)
                {
                    // Swap adjacent items.
                    swap(array, i, i + 1);
                }
            }
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
    public Bubblesort() {}

    public static void main(String[] args)
    {
        SortingAlgorithm.validate(new Bubblesort<Integer>());
        System.out.println("Bubblesort has passed all tests");

        // Fill an array with random numbers.
        int N = 5;
        Integer[] array = new Integer[N];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int)(N * Math.random());
        }

        // Measuring runtime.
        SortingAlgorithm<Integer> sorter = new Bubblesort<Integer>();

        long start = System.nanoTime();

        sorter.sort(array);

        long end = System.nanoTime();

        double duration = (end - start) / 1e9;

        System.out.println("Array size " + N);
        System.out.println("Total duration: " + duration);
    }
}
