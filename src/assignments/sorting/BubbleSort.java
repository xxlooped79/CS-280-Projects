package assignments.sorting;

/**
 * Swap adjacent elements over and over until the whole array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
    /**
     * Sort an array in-place using bubble sort.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(T[] array)
    {
        for (int k = array.length; k >= 2; k--)
        {
            for (int i = 0; i < k - 1; i++)
            {
                if (array[i].compareTo(array[i + 1]) > 0)
                {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    /**
     * Swap two elements within an array.
     *
     * @param array the array to swap values in
     * @param i the first index
     * @param j the second index
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
    public BubbleSort() {}

    /**
     * Tests BubbleSort.
     *
     * @param args command-line arguments
     */
    @Override
    public void main(String[] args)
    {
        SortingAlgorithm.validate(new BubbleSort<Integer>());
        System.out.println("BubbleSort has passed all tests");
    }
}