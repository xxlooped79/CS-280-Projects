package assignments.datastructures;

import adt.List;
import adt.Queue;
import java.util.Iterator;

/**
 * A circular linked list where the last node points back to the first node.
 *
 * @param <T> the type of each element
 */
public class CircularLinkedList<T> implements List<T>, Queue<T>, Iterable<T> {

    private Node tail;
    private int size;

    /**
     * Create an empty circular linked list.
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Return the number of elements in the list.
     *
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /**
     * Get the element at an index.
     *
     * @param index the index to look at
     * @return the element at the index
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;

        Node current = this.tail.link;

        for (int i = 0; i < index; i++) {
            current = current.link;
        }

        return current.data;
    }

    /**
     * Change the value at an index.
     *
     * @param index the index to change
     * @param value the new value
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;

        Node current = this.tail.link;

        for (int i = 0; i < index; i++) {
            current = current.link;
        }

        current.data = value;
    }

    /**
     * Check if the list contains a value.
     *
     * @param value the value to look for
     * @return true if the value is in the list
     */
    public boolean contains(T value) {
        if (this.size == 0) {
            return false;
        }

        Node current = this.tail.link;

        for (int i = 0; i < this.size; i++) {
            if (current.data.equals(value)) {
                return true;
            }

            current = current.link;
        }

        return false;
    }

    /**
     * Insert a value at an index.
     *
     * @param index the index to insert at
     * @param value the value to insert
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;

        Node newNode = new Node(value);

        if (this.size == 0) {
            newNode.link = newNode;
            this.tail = newNode;
        } else if (index == 0) {
            newNode.link = this.tail.link;
            this.tail.link = newNode;
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            newNode.link = current.link;
            current.link = newNode;

            if (index == this.size) {
                this.tail = newNode;
            }
        }

        this.size++;
    }

    /**
     * Delete the value at an index.
     *
     * @param index the index to delete
     * @return the value that was deleted
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;

        Node removed;

        if (this.size == 1) {
            removed = this.tail;
            this.tail = null;
        } else if (index == 0) {
            removed = this.tail.link;
            this.tail.link = removed.link;
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            removed = current.link;
            current.link = removed.link;

            if (index == this.size - 1) {
                this.tail = current;
            }
        }

        this.size--;
        return removed.data;
    }

    /**
     * Check if the queue is empty.
     *
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Add a value to the end of the queue.
     *
     * @param value the value to add
     */
    @Override
    public void enqueue(T value) {
        this.insert(this.size, value);
    }

    /**
     * Remove and return the first value in the queue.
     *
     * @return the value removed from the queue
     */
    @Override
    public T dequeue() {
        return this.delete(0);
    }

    /**
     * Look at the first value in the queue without removing it.
     *
     * @return the first value in the queue
     */
    @Override
    public T peek() {
        return this.at(0);
    }

    /**
     * Create an iterator for the list.
     *
     * @return an iterator over the list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cursor = tail == null ? null : tail.link;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T value = cursor.data;
                cursor = cursor.link;
                count++;
                return value;
            }
        };
    }

    /**
     * A node stores a value and a link to the next node.
     */
    private class Node {
        T data;
        Node link;

        Node(T data) {
            this.data = data;
            this.link = null;
        }
    }

    /**
     * Run tests for the circular linked list.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());
        Queue.validate(new CircularLinkedList<>());

        // Test iterator.
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        for (int i = 0; i < 5; i++) {
            list.insert(0, i);
        }

        Iterator<Integer> iter = list.iterator();

        for (int i = 5; i > 0; i--) {
            assert iter.next().equals(i - 1);
        }

        assert !iter.hasNext();

        System.out.println("CircularLinkedList passes all tests.");
    }
}