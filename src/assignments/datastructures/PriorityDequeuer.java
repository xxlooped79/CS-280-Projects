package assignments.datastructures;

import adt.PriorityQueue;

/**
 * A priority queue that uses a sorted linked list.
 *
 * This class stores values in order from smallest
 * to largest. When a new value is added, it goes
 * into the correct position in the list.
 *
 * Smaller values have higher priority, so the
 * smallest value is always at the beginning.
 *
 * Enqueue takes O(N) time because we may need
 * to search through the list to add a value.
 *
 * Dequeue and peek take O(1) time because the
 * highest-priority value is always at the front.
 *
 * @param <T> the type of values stored in the queue
 */
public class PriorityDequeuer<T extends Comparable<T>>
        implements PriorityQueue<T> {

    // Each node stores a value and the next node.
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
     * Checks if the queue is empty.
     *
     * This takes O(1) time because we only
     * need to check the size.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of values in the queue.
     *
     * This takes O(1) time because the size
     * is updated when values are added or removed.
     *
     * @return the number of values in the queue
     */
    public int length() {
        return size;
    }

    /**
     * Adds a new value in the correct position.
     *
     * Smaller values have higher priority.
     * This takes O(N) time because we may
     * need to search through the linked list.
     *
     * @param value the value being added
     */
    @Override
    public void enqueue(T value) {
        Node newNode = new Node(value);

        // Add to the front if the list is empty
        // or the new value has higher priority.
        if (head == null ||
                value.compareTo(head.data) < 0) {

            newNode.next = head;
            head = newNode;

        } else {
            Node current = head;

            // Find the correct place for the new value.
            while (current.next != null &&
                    current.next.data.compareTo(value) <= 0) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    /**
     * Returns the highest-priority value without
     * removing it from the queue.
     *
     * This takes O(1) time because the smallest
     * value is always at the beginning.
     *
     * @return the highest-priority value
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
     * Removes and returns the highest-priority value.
     *
     * This takes O(1) time because we only
     * need to remove the first node.
     *
     * @return the value that was removed
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
     * Runs the PriorityQueue interface validation
     * to check if the queue works correctly.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        PriorityQueue.validate(new PriorityDequeuer<Integer>());

    }
}
