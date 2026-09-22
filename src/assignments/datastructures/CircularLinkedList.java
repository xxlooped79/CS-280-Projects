package assignments.datastructures;

import adt.List;
import java.util.Iterator;

/// A circular list backed by a chain of nodes.
///
/// The idea here is to wrap each datum in a larger structure, a *node*,
/// which also contains a pointer to the node containing the *next* element
/// in the list. Unlike a regular linked list, the last node points back to
/// the first node.
///
/// @param <T> the type of each element
public class CircularLinkedList<T> implements List<T>, Iterable<T> {
    private Node tail;
    private int size;

    /**
     * Create an iterator that starts at the beginning of the list.
     * The iterator stops after visiting each element once.
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
     * Initialize an empty circular linked list.
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     *
     * @return the number of items
     */
    public int length() {
        return this.size;
    }

    /**
     * Fetch an item from the list.
     *
     * @param index the location of the item
     * @return the value stored at the given location
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
     * Change an item in the list.
     *
     * @param index the location of the item
     * @param value the new value to assign
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
     * Check if the list contains a given value.
     *
     * @param value the value to look for
     * @return true iff the collection contains value
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
     * Insert an item into the list.
     *
     * @param index the location where to put the item
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;

        if (this.size == 0) {
            Node node = new Node(value, null);
            node.link = node;
            this.tail = node;
        } else if (index == 0) {
            Node head = this.tail.link;
            this.tail.link = new Node(value, head);
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            current.link = new Node(value, current.link);

            if (index == this.size) {
                this.tail = current.link;
            }
        }

        this.size++;
    }

    /**
     * Remove an item from the list.
     *
     * @param index the location to delete from
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;

        T value;

        if (this.size == 1) {
            value = this.tail.data;
            this.tail = null;
        } else if (index == 0) {
            value = this.tail.link.data;
            this.tail.link = this.tail.link.link;
        } else {
            Node current = this.tail.link;

            for (int i = 0; i < index - 1; i++) {
                current = current.link;
            }

            value = current.link.data;

            if (current.link == this.tail) {
                this.tail = current;
            }

            current.link = current.link.link;
        }

        this.size--;

        return value;
    }

    /**
     * An encapsulation of a value with a pointer to the next node.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node.
         *
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
     * Run validation tests.
     *
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());

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