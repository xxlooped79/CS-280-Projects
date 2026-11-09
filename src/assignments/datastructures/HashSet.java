
package assignments.datastructures;

import adt.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A set that uses a hash table to store elements.
 *
 * Each bucket uses a linked list to handle collisions.
 * A bucket can hold up to five elements. If a sixth
 * element is added, the hash table is resized.
 *
 * Duplicate elements are not allowed.
 *
 * @param <T> the type of elements stored in the set
 */
public class HashSet<T> implements Set<T> {

    private static final int MAX_BUCKET_SIZE = 5;
    private static final int INITIAL_CAPACITY = 10;

    /**
     * A node that stores one element in a bucket.
     */
    private class Node {
        T data;
        Node next;

        /**
         * Creates a node with the given value.
         *
         * @param data the value to store
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    /**
     * Creates an empty hash set.
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        table = (Node[]) java.lang.reflect.Array.newInstance(
                Node.class, INITIAL_CAPACITY);
        size = 0;
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    @Override
    public int length() {
        return size;
    }

    /**
     * Finds the bucket index for an element.
     *
     * @param value the element to hash
     * @return the bucket index
     */
    private int hash(T value) {
        return Math.floorMod(value.hashCode(), table.length);
    }

    /**
     * Checks if the set contains an element.
     *
     * @param value the element to find
     * @return true if the element exists, false otherwise
     */
    @Override
    public boolean contains(T value) {
        int index = hash(value);
        Node current = table[index];

        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Adds an element to the set.
     *
     * Duplicate elements are not added.
     * If a bucket becomes too large, the table
     * is resized and the elements are rehashed.
     *
     * @param value the element to add
     * @return true if added, false if already present
     */
    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }

        int index = hash(value);
        Node current = table[index];
        int bucketSize = 0;

        while (current != null) {
            bucketSize++;
            current = current.next;
        }

        if (bucketSize >= MAX_BUCKET_SIZE) {
            rehash();
            return add(value);
        }

        Node newNode = new Node(value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;

        return true;
    }

    /**
     * Removes an element from the set.
     *
     * @param value the element to remove
     * @return true if removed, false if not found
     */
    @Override
    public boolean remove(T value) {
        int index = hash(value);
        Node current = table[index];
        Node previous = null;

        while (current != null) {
            if (current.data.equals(value)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Makes the hash table larger and moves
     * all existing elements into new buckets.
     *
     * The new table has twice as many buckets.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Node[] oldTable = table;

        table = (Node[]) java.lang.reflect.Array.newInstance(
                Node.class, oldTable.length * 2);

        size = 0;

        for (Node bucket : oldTable) {
            Node current = bucket;

            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }

    /**
     * Returns an iterator that visits all elements
     * stored in the hash table.
     *
     * @return an iterator over the set
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index = 0;
            private Node current = null;

            /**
             * Checks if another element is available.
             *
             * @return true if another element exists
             */
            @Override
            public boolean hasNext() {
                if (current != null) {
                    return true;
                }

                while (index < table.length) {
                    if (table[index] != null) {
                        current = table[index];
                        index++;
                        return true;
                    }
                    index++;
                }

                return false;
            }

            /**
             * Returns the next element in the set.
             *
             * @return the next element
             * @throws NoSuchElementException if none remain
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    /**
     * Tests the hash set, including rehashing.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();

        Set.validate(set);
        System.out.println(
                "HashSet passes Set interface validation.");

        HashSet<Integer> test = new HashSet<>();

        // Add elements and check for duplicates.
        assert test.add(10);
        assert test.add(20);
        assert test.add(30);
        assert !test.add(10);
        assert test.length() == 3;

        // Check contains.
        assert test.contains(20);
        assert !test.contains(40);

        // Check remove.
        assert test.remove(20);
        assert !test.contains(20);
        assert !test.remove(40);
        assert test.length() == 2;

        System.out.println("HashSet basic tests passed.");

        // Test rehashing using values that collide.
        HashSet<Integer> rehashTest = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            assert rehashTest.add(i * 10);
        }

        // Make sure every element is still present.
        for (int i = 0; i < 100; i++) {
            assert rehashTest.contains(i * 10);
        }

        assert rehashTest.length() == 100;

        // Check that the iterator visits every element.
        int count = 0;

        for (Integer value : rehashTest) {
            assert rehashTest.contains(value);
            count++;
        }

        assert count == 100;

        System.out.println("HashSet rehashing test passed.");
        System.out.println("HashSet passes all tests.");
    }
}
