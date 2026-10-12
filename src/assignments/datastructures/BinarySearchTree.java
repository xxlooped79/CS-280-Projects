package assignments.datastructures;

import java.util.ArrayList;
import java.util.Iterator;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
/// comprised of nodes which have at most two children.
///
/// The binary search tree satisfies the binary search tree condition:
/// every node has a value greater than that of its left child,
/// and less than or equal to that of its right child.
///
/// @param <T> the type of each element, which must have a natural ordering
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return length(this.root);
    }

    private int length(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + length(node.left) + length(node.right);
    }

    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return degree(this.root);
    }

    private int degree(Node node) {
        if (node == null) {
            return 0;
        }

        int children = 0;

        if (node.left != null) {
            children++;
        }

        if (node.right != null) {
            children++;
        }

        int leftDegree = degree(node.left);
        int rightDegree = degree(node.right);

        return Math.max(children, Math.max(leftDegree, rightDegree));
    }

    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * An empty tree has a height of 0.
     * @return the height of the tree
     */
    public int height() {
        if (this.root == null) {
            return 0;
        }

        return height(this.root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        ArrayList<T> values = new ArrayList<>();
        preorder(this.root, values);
        return values.iterator();
    }

    private void preorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        values.add(node.data);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        ArrayList<T> values = new ArrayList<>();
        inorder(this.root, values);
        return values.iterator();
    }

    private void inorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        inorder(node.left, values);
        values.add(node.data);
        inorder(node.right, values);
    }

    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        ArrayList<T> values = new ArrayList<>();
        postorder(this.root, values);
        return values.iterator();
    }

    private void postorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        postorder(node.left, values);
        postorder(node.right, values);
        values.add(node.data);
    }

    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        ArrayList<T> values = new ArrayList<>();

        if (this.root == null) {
            return values.iterator();
        }

        Queue<Node> queue = new CircularLinkedList<>();
        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();

            values.add(current.data);

            if (current.left != null) {
                queue.enqueue(current.left);
            }

            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        return values.iterator();
    }

    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }

    /**
     * Check whether a value is in the tree.
     * @param value the value to find
     * @return true if the value is found, otherwise false
     */
    public boolean contains(T value) {
        Node current = this.root;

        while (current != null) {
            int comparison = value.compareTo(current.data);

            if (comparison == 0) {
                return true;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /**
     * Add a value to the tree.
     * Equal values are placed on the right.
     * @param value the value to add
     */
    public void add(T value) {
        this.root = add(this.root, value);
    }

    private Node add(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        if (value.compareTo(node.data) < 0) {
            node.left = add(node.left, value);
        } else {
            node.right = add(node.right, value);
        }

        return node;
    }

    /**
     * Remove one matching value from the tree.
     * If the value is not found, nothing changes.
     * @param value the value to remove
     */
    public void remove(T value) {
        this.root = remove(this.root, value);
    }

    private Node remove(Node node, T value) {
        if (node == null) {
            return null;
        }

        int comparison = value.compareTo(node.data);

        if (comparison < 0) {
            node.left = remove(node.left, value);
        } else if (comparison > 0) {
            node.right = remove(node.right, value);
        } else {

            // No left child.
            if (node.left == null) {
                return node.right;
            }

            // No right child.
            if (node.right == null) {
                return node.left;
            }

            // Find the smallest value on the right.
            Node smallest = node.right;

            while (smallest.left != null) {
                smallest = smallest.left;
            }

            node.data = smallest.data;
            node.right = remove(node.right, smallest.data);
        }

        return node;
    }

    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new BinarySearchTree<>());

        // Build a sample tree.
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(4);
        tree.add(-4);
        tree.add(-6);
        tree.add(0);
        tree.add(-3);
        tree.add(5);
        tree.add(3);

        // Check structure.
        assert tree.length() == 7;
        assert tree.degree() == 2;
        assert tree.height() == 3;

        // Check traversals.
        assert testTraversal(tree.preorder(),
            new int[]{4, -4, -6, 0, -3, 3, 5});

        assert testTraversal(tree.inorder(),
            new int[]{-6, -4, -3, 0, 3, 4, 5});

        assert testTraversal(tree.postorder(),
            new int[]{-6, -3, 3, 0, -4, 5, 4});

        assert testTraversal(tree.levelorder(),
            new int[]{4, -4, 5, -6, 0, -3, 3});

        assert testTraversal(tree.iterator(),
            new int[]{-6, -4, -3, 0, 3, 4, 5});

        // Test contains.
        assert tree.contains(4);
        assert tree.contains(-6);
        assert tree.contains(5);
        assert !tree.contains(100);

        // Test add.
        tree.add(10);
        assert tree.contains(10);
        assert tree.length() == 8;

        // Test duplicate values.
        tree.add(5);
        assert tree.length() == 9;

        tree.remove(5);
        assert tree.contains(5);
        assert tree.length() == 8;

        // Test remove.
        tree.remove(10);
        assert !tree.contains(10);
        assert tree.length() == 7;

        // Test removing a node with two children.
        tree.remove(4);
        assert !tree.contains(4);
        assert tree.length() == 6;

        // Test removing a value that does not exist.
        tree.remove(100);
        assert tree.length() == 6;

        System.out.println("BinarySearchTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     * @return true if the values match
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!iterator.hasNext()) {
                return false;
            }

            Integer next = iterator.next();

            if (!next.equals(array[i])) {
                return false;
            }
        }

        return !iterator.hasNext();
    }
}
