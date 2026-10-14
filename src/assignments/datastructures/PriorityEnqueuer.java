package assignments.datastructures;

import adt.PriorityQueue;

/**
 * A priority queue that uses an unsorted linked list.
 *
 * This class stores values in a linked list. When a
 * new value is added, it goes at the beginning of
 * the list without needing to sort anything.
 *
 * Smaller values have higher priority. When removing
 * a value, the queue searches for the smallest one.
 *
 * Enqueue takes O(1) time because it only adds
 * a new node at the beginning.
 *
 * Dequeue and peek take O(N) time because they
 * need to search through the linked list.
 *
 * @param <T> the type of values stored in the queue
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
     * Adds a new value to the beginning of the list.
     *
     * This takes O(1) time because we do not
     * need to search through the list.
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
     * Smaller values have higher priority.
     * This takes O(N) time because we need
     * to search through the linked list.
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

        // Look through the list for the smallest value.
        while (current != null) {
            if (current.data.compareTo(highest) < 0) {
                highest = current.data;
            }

            current = current.next;
        }

        return highest;
    }

    /**
     * Removes and returns the highest-priority value.
     *
     * This takes O(N) time because we need
     * to search through the linked list.
     *
     * @return the value that was removed
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

        // Find the node with the smallest value.
        while (current != null) {
            if (current.data.compareTo(highestNode.data) < 0) {
                highestNode = current;
                highestPrevious = previous;
            }

            previous = current;
            current = current.next;
        }

        // Remove the highest-priority node.
        if (highestPrevious == null) {
            head = head.next;
        } else {
            highestPrevious.next = highestNode.next;
        }

        size--;

        return highestNode.data;
    }

    /**
     * Runs the PriorityQueue interface validation
     * to check if the queue works correctly.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        PriorityQueue.validate(new PriorityEnqueuer<Integer>());

    }
}
