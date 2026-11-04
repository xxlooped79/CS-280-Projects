
package assignments.datastructures;

import adt.Set;
import java.util.Iterator;

/**
 * A set that stores elements using a BinarySearchTree.
 *
 * A set does not allow duplicate elements.
 * The BinarySearchTree keeps the elements organized
 * so they can be searched, added, and removed.
 *
 * @param <T> the type of elements in the set
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {

    /** The binary search tree that stores the elements. */
    private BinarySearchTree<T> tree;

    /**
     * Creates an empty TreeSet.
     */
    public TreeSet() {
        tree = new BinarySearchTree<>();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    @Override
    public int length() {
        return tree.length();
    }

    /**
     * Checks if an element exists in the set.
     *
     * @param value the element to find
     * @return true if the element exists
     */
    @Override
    public boolean contains(T value) {
        return tree.contains(value);
    }

    /**
     * Adds an element to the set.
     * Duplicate elements are not added.
     *
     * @param value the element to add
     * @return true if added, false if already present
     */
    @Override
    public boolean add(T value) {
        if (tree.contains(value)) {
            return false;
        }

        tree.add(value);
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
        if (!tree.contains(value)) {
            return false;
        }

        tree.remove(value);
        return true;
    }

    /**
     * Returns an iterator over the elements in the set.
     *
     * @return an iterator for the set
     */
    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }

    /**
     * Runs tests for TreeSet.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Run the professor's interface validation.
        Set.validate(new TreeSet<Integer>());

        System.out.println(
            "TreeSet passes Set interface validation."
        );

        // Create a set for additional tests.
        TreeSet<Integer> set = new TreeSet<>();

        // Test adding elements.
        assert set.add(10);
        assert set.add(20);
        assert set.add(30);

        assert set.length() == 3;

        // Test duplicate elements.
        assert !set.add(20);
        assert set.length() == 3;

        // Test contains.
        assert set.contains(10);
        assert set.contains(20);
        assert !set.contains(40);

        // Test removing elements.
        assert set.remove(20);
        assert !set.contains(20);
        assert set.length() == 2;

        // Test removing a missing element.
        assert !set.remove(50);

        // Test iterator.
        int count = 0;

        for (Integer value : set) {
            assert value != null;
            count++;
        }

        assert count == set.length();

        System.out.println("TreeSet passes all tests.");
    }
}
