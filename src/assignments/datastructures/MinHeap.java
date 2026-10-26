package assignments.datastructures;
import adt.PriorityQueue;
/**
 * A priority queue that uses a min heap stored in a Vector.
 *
 * A min heap keeps the smallest value at the top.
 * This means the smallest value is removed first.
 *
 * The heap is stored in a Vector. Each parent has a
 * value that is smaller than or equal to its children.
 *
 * Enqueue takes O(log N) time because a new value
 * may need to move up the heap.
 *
 * Dequeue takes O(log N) time because a value may
 * need to move down the heap.
 *
 * Peek takes O(1) time because the smallest value
 * is always at index 0.
 *
 * @param <T> the type of values stored in the heap
 */
public class MinHeap<T extends Comparable<T>>
        implements PriorityQueue<T> {

    private Vector<T> heap;

    /**
     * Creates an empty min heap.
     */
    public MinHeap() {
        heap = new Vector<T>();
    }

    /**
     * Checks if the heap is empty.
     *
     * Time complexity: O(1).
     *
     * @return true if empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return heap.length() == 0;
    }

    /**
     * Adds a value to the heap.
     * The new value moves up until it is in
     * the correct position.
     *
     * Time complexity: O(log N).
     *
     * @param value the value to add
     */
    @Override
    public void enqueue(T value) {
        heap.insert(heap.length(), value);

        int index = heap.length() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.at(index).compareTo(heap.at(parent)) >= 0) {
                break;
            }

            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Removes and returns the smallest value.
     * The last value moves to the top and then
     * moves down until the heap is ordered again.
     *
     * Time complexity: O(log N).
     *
     * @return the smallest value, or null if empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T smallest = heap.at(0);

        // Remove the last value from the Vector.
        T last = heap.delete(heap.length() - 1);

        if (!isEmpty()) {
            heap.set(0, last);

            int index = 0;

            while (true) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int smallestIndex = index;

                // Check the left child.
                if (left < heap.length()
                        && heap.at(left).compareTo(
                            heap.at(smallestIndex)) < 0) {
                    smallestIndex = left;
                }

                // Check the right child.
                if (right < heap.length()
                        && heap.at(right).compareTo(
                            heap.at(smallestIndex)) < 0) {
                    smallestIndex = right;
                }

                // Stop if the heap is ordered.
                if (smallestIndex == index) {
                    break;
                }

                swap(index, smallestIndex);
                index = smallestIndex;
            }
        }

        return smallest;
    }

    /**
     * Returns the smallest value without removing it.
     *
     * Time complexity: O(1).
     *
     * @return the smallest value, or null if empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return heap.at(0);
    }

    /**
     * Swaps two values in the heap.
     *
     * @param first index of the first value
     * @param second index of the second value
     */
    private void swap(int first, int second) {
        T temp = heap.at(first);
        heap.set(first, heap.at(second));
        heap.set(second, temp);
    }

    /**
     * Runs the PriorityQueue validation and ordering tests.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<Integer>();

        // Test the PriorityQueue interface.
        PriorityQueue.validate(heap);

        System.out.println(
            "MinHeap passes PriorityQueue interface validation."
        );

        // Test the order of the values.
        heap.enqueue(8);
        heap.enqueue(3);
        heap.enqueue(10);
        heap.enqueue(1);
        heap.enqueue(5);

        assert heap.dequeue() == 1;
        assert heap.dequeue() == 3;
        assert heap.dequeue() == 5;
        assert heap.dequeue() == 8;
        assert heap.dequeue() == 10;

        System.out.println("MinHeap ordering tests passed.");
    }
}
