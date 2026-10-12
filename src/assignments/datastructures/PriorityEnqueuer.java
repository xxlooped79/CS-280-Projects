
package assignments.datastructures;

import adt.PriorityQueue;

/**
 * A priority queue that uses a linked list.
 *
 * New elements are added at the beginning of the list,
 * which makes enqueue run in O(1) time.
 *
 * When removing an element, the queue searches for
 * the highest-priority value.
 *
 * Larger values have higher priority in this version.
 *
 * @param <T> the type of elements stored in the queue
 */
public class PriorityEnqueuer<T extends Comparable<T>>
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
    public PriorityEnqueuer() {
        head = null;
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * This method takes O(1) time.
     *
     * @return true if empty, false otherwise
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
     * Adds a value to the beginning of the queue.
     *
     * This method runs in O(1) time because it
     * does not need to search through the list.
     *
     * @param value the value being added
     */
    @Override
    public void enqueue(T value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Finds the highest-priority value without
     * removing it from the queue.
     *
     * This method runs in O(N) time.
     *
     * @return the highest-priority value
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T highest = head.data;
        Node current = head.next;

        while (current != null) {
            if (current.data.compareTo(highest) > 0) {
                highest = current.data;
            }

            current = current.next;
        }

        return highest;
    }

    /**
     * Removes and returns the highest-priority value.
     *
     * This method runs in O(N) time because it
     * searches through the linked list.
     *
     * @return the removed value
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        Node current = head;
        Node previous = null;
        Node highestNode = head;
        Node highestPrevious = null;

        while (current != null) {
            if (current.data.compareTo(highestNode.data) > 0) {
                highestNode = current;
                highestPrevious = previous;
            }

            previous = current;
            current = current.next;
        }

        if (highestPrevious == null) {
            head = head.next;
        } else {
            highestPrevious.next = highestNode.next;
        }

        size--;
        return highestNode.data;
    }

    /**
     * Runs the PriorityQueue interface validation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Run the professor's official validation.
        PriorityQueue.validate(new PriorityEnqueuer<Integer>());

        System.out.println(
            "PriorityEnqueuer passes PriorityQueue interface validation."
        );
    }
}
