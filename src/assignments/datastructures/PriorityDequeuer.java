
package assignments.datastructures;

import adt.PriorityQueue;

/**
 * A priority queue that uses a sorted linked list.
 *
 * This class stores elements from highest priority
 * to lowest priority. When a new element is added,
 * it is placed in the correct position in the list.
 *
 * The highest-priority element is always at the
 * beginning, so dequeue takes O(1) time.
 *
 * Enqueue takes O(N) time because it may need to
 * search through the list.
 *
 * Larger values are treated as higher priority.
 *
 * @param <T> the type of elements stored in the queue
 */
public class PriorityDequeuer<T extends Comparable<T>>
        implements PriorityQueue<T> {

    // A node stores a value and the next node.
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Creates an empty priority queue.
     */
    public PriorityDequeuer() {
        head = null;
        size = 0;
    }

    /**
     * Checks whether the queue is empty.
     *
     * This method takes O(1) time.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * This method takes O(1) time.
     *
     * @return the number of elements
     */
    public int length() {
        return size;
    }

    /**
     * Adds an element to the queue in priority order.
     *
     * Larger values have higher priority.
     * This method takes O(N) time because it may
     * need to search through the linked list.
     *
     * @param value the element being added
     */
    @Override
    public void enqueue(T value) {
        Node newNode = new Node(value);

        // Add to the beginning if the queue is empty
        // or the new value has higher priority.
        if (head == null ||
                value.compareTo(head.data) > 0) {

            newNode.next = head;
            head = newNode;

        } else {
            Node current = head;

            // Find the correct position.
            while (current.next != null &&
                    current.next.data.compareTo(value) >= 0) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    /**
     * Returns the highest-priority element
     * without removing it.
     *
     * This method takes O(1) time because
     * the highest-priority element is at the front.
     *
     * @return the highest-priority element
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return head.data;
    }

    /**
     * Removes and returns the highest-priority element.
     *
     * This method takes O(1) time because it
     * only removes the first node.
     *
     * @return the removed element
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T value = head.data;
        head = head.next;
        size--;

        return value;
    }

    /**
     * Runs the PriorityQueue interface validation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Run the professor's official validation.
        PriorityQueue.validate(new PriorityDequeuer<Integer>());

        System.out.println(
            "PriorityDequeuer passes PriorityQueue interface validation."
        );
    }
}
