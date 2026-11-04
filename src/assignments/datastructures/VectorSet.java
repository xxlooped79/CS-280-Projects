
package assignments.datastructures;

import adt.Set;
import java.util.Iterator;
import java.util.Objects;

/**
 * A set that stores elements using a Vector.
 *
 * A set does not allow duplicate elements.
 * This class uses a Vector to store the values.
 *
 * @param <T> the type of elements in the set
 */
public class VectorSet<T> implements Set<T> {

    /** The vector that stores the elements. */
    private Vector<T> data;

    /**
     * Creates an empty VectorSet.
     */
    public VectorSet() {
        data = new Vector<>();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    @Override
    public int length() {
        return data.length();
    }

    /**
     * Checks if an element exists in the set.
     *
     * @param value the element to find
     * @return true if the element exists
     */
    @Override
    public boolean contains(T value) {
        return data.contains(value);
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
        if (contains(value)) {
            return false;
        }

        data.insert(data.length(), value);
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
        for (int i = 0; i < data.length(); i++) {
            if (Objects.equals(data.at(i), value)) {
                data.delete(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns an iterator for the set.
     *
     * @return an iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    /**
     * Runs tests for VectorSet.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Run the professor's validation.
        Set.validate(new VectorSet<Integer>());

        System.out.println(
            "VectorSet passes Set interface validation."
        );

        // Create a set for additional tests.
        VectorSet<Integer> set = new VectorSet<>();

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

        System.out.println("VectorSet passes all tests.");
    }
}
