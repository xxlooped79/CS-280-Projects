
package assignments.sorting;

import java.util.Arrays;

/**
 * Implements Radix Sort for integers.
 *
 * Radix Sort sorts numbers one digit at a time,
 * starting with the least significant digit.
 *
 * This version uses counting sort for each digit.
 */
public class RadixSort extends SortingAlgorithm<Integer> {

    /**
     * Sorts an array of integers using Radix Sort.
     *
     * @param array the array to sort
     */
    @Override
    public void sort(Integer[] array) {

        if (array.length < 2) {
            return;
        }

        // Create temporary arrays only once.
        Integer[] output = new Integer[array.length];
        int[] count = new int[256];

        // Sort all four bytes of each integer.
        for (int shift = 0; shift < 32; shift += 8) {

            // Reset the counts for each pass.
            Arrays.fill(count, 0);

            // Count how many numbers have each byte value.
            for (int i = 0; i < array.length; i++) {
                int digit = (array[i] >>> shift) & 255;

                // Handle negative numbers in the final pass.
                if (shift == 24) {
                    digit ^= 128;
                }

                count[digit]++;
            }

            // Find the ending position for each bucket.
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            // Place numbers in order, working backward.
            for (int i = array.length - 1; i >= 0; i--) {
                int digit = (array[i] >>> shift) & 255;

                if (shift == 24) {
                    digit ^= 128;
                }

                output[--count[digit]] = array[i];
            }

            // Copy the values back into the original array.
            for (int i = 0; i < array.length; i++) {
                array[i] = output[i];
            }
        }
    }

    /**
     * Tests Radix Sort using different integer arrays.
     *
     * @param args command-line arguments
     */
    public void main(String[] args) {

        RadixSort sorter = new RadixSort();

        // Example from Section 7.11.
        Integer[] numbers = {
            64, 8, 216, 512, 27,
            729, 0, 1, 343, 125
        };

        sorter.sort(numbers);

        assert Arrays.equals(numbers, new Integer[] {
            0, 1, 8, 27, 64,
            125, 216, 343, 512, 729
        });

        // Test negative numbers and duplicates.
        Integer[] moreNumbers = {
            -5, 10, 0, -20, 10, 3
        };

        sorter.sort(moreNumbers);

        assert Arrays.equals(moreNumbers, new Integer[] {
            -20, -5, 0, 3, 10, 10
        });

        // Test empty and single-element arrays.
        sorter.sort(new Integer[0]);

        Integer[] single = {42};
        sorter.sort(single);
        assert single[0] == 42;

        System.out.println("RadixSort passes all tests.");
    }
}
