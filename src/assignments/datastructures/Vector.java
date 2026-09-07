package assignments.datastructures;

import adt.List;

/// An extensible list backed by an array buffer.
///
/// The idea here is to store your data in an array larger than it has to be.
/// This gives you room to add more items to the end, without having to reallocate memory every time.
/// You simply need to keep track of which parts of the array are currently in use.
///
/// Eventually, the array buffer *will* run out of space.
/// Then you need to allocate an even larger buffer, and copy the present buffer to the new one.
/// This is a very expensive operation, so you want to make sure it occurs very infrequently.
///
/// @param <T> the type of each element
public class Vector<T> implements List<T> {
    /** The initial amount of buffer space in a newly-created vector. */
    public static final int INITIAL_BUFFER_SIZE = 10;

    private T[] array;
    private int size;

    /**
     * Initialize an empty vector.
     */
    @SuppressWarnings("unchecked")
    public Vector() {
        // Generic types don't technically exist at runtime,
        // so allocate an Object array and cast it to T[].
        this.array = (T[])(new Object[INITIAL_BUFFER_SIZE]);
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        return this.size;
    }

    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        return this.array[index];
    }

    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        this.array[index] = value;
    }

    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        for (int i = 0; i < this.size; i++) {
            if (value == null) {
                if (this.array[i] == null) {
                    return true;
                }
            } else if (value.equals(this.array[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        // Note this function has a somewhat different pre-condition!
        assert 0 <= index && index <= this.size;

        // If the array is full, double its size.
        if (this.size == this.array.length) {
            resize(this.array.length * 2);
        }

        // Shift elements to the right to make room.
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }

        // Insert the new value.
        this.array[index] = value;
        this.size++;
    }

    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size;

        T removed = this.array[index];

        // Shift elements to the left.
        for (int i = index; i < this.size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }

        // Remove the duplicate reference at the end.
        this.array[this.size - 1] = null;

        this.size--;

        return removed;
    }

    /**
     * Resize the internal buffer array.
     *
     * This method involves copying from the current buffer to a newly allocated one.
     *
     * @param newSize the new size of the internal buffer array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArray = (T[])(new Object[newSize]);

        // Copy all currently-used elements.
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new Vector<>());
        System.out.println("Vector passes all tests.");
    }
}