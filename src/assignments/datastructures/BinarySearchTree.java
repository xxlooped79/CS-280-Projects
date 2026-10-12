
package assignments.datastructures;

import java.util.ArrayList;
import java.util.Iterator;

import adt.Queue;
import adt.Tree;

/**
 * A binary search tree that stores values in nodes.
 *
 * Each node can have a left child and a right child.
 * Values smaller than a node go to the left, while
 * equal or larger values go to the right.
 *
 * @param <T> the type of values stored in the tree
 */
public class BinarySearchTree<T extends Comparable<T>>
        implements Tree<T> {

    private Node root;

    /**
     * Creates an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Counts the number of values in the tree.
     *
     * @return the number of values
     */
    public int length() {
        return length(this.root);
    }

    /**
     * Counts the nodes starting from the given node.
     *
     * @param node the current node
     * @return the number of nodes
     */
    private int length(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + length(node.left) + length(node.right);
    }

    /**
     * Finds the largest number of children
     * that any node in the tree has.
     *
     * @return the degree of the tree
     */
    public int degree() {
        return degree(this.root);
    }

    /**
     * Finds the degree of the tree starting at a node.
     *
     * @param node the current node
     * @return the largest number of children
     */
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

        return Math.max(children,
                Math.max(leftDegree, rightDegree));
    }

    /**
     * Finds the height of the tree.
     *
     * An empty tree has a height of 0.
     *
     * @return the height of the tree
     */
    public int height() {
        if (this.root == null) {
            return 0;
        }

        return height(this.root);
    }

    /**
     * Finds the height starting from a node.
     *
     * @param node the current node
     * @return the height of the subtree
     */
    private int height(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Visits the nodes in preorder.
     *
     * The root is visited before the left and right children.
     *
     * @return an iterator containing the values
     */
    public Iterator<T> preorder() {
        ArrayList<T> values = new ArrayList<>();

        preorder(this.root, values);

        return values.iterator();
    }

    /**
     * Adds the nodes to a list in preorder.
     *
     * @param node the current node
     * @param values the list storing the values
     */
    private void preorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        values.add(node.data);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    /**
     * Visits the nodes in inorder.
     *
     * The left child is visited before the root
     * and then the right child.
     *
     * @return an iterator containing the values
     */
    public Iterator<T> inorder() {
        ArrayList<T> values = new ArrayList<>();

        inorder(this.root, values);

        return values.iterator();
    }

    /**
     * Adds the nodes to a list in inorder.
     *
     * @param node the current node
     * @param values the list storing the values
     */
    private void inorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        inorder(node.left, values);
        values.add(node.data);
        inorder(node.right, values);
    }

    /**
     * Visits the nodes in postorder.
     *
     * The left and right children are visited
     * before the root.
     *
     * @return an iterator containing the values
     */
    public Iterator<T> postorder() {
        ArrayList<T> values = new ArrayList<>();

        postorder(this.root, values);

        return values.iterator();
    }

    /**
     * Adds the nodes to a list in postorder.
     *
     * @param node the current node
     * @param values the list storing the values
     */
    private void postorder(Node node, ArrayList<T> values) {
        if (node == null) {
            return;
        }

        postorder(node.left, values);
        postorder(node.right, values);
        values.add(node.data);
    }

    /**
     * Visits the nodes level by level.
     *
     * This method uses a queue to visit each level
     * from left to right.
     *
     * @return an iterator containing the values
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
     * Returns an iterator that visits values in order.
     *
     * @return an inorder iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }

    /**
     * Checks whether a value exists in the tree.
     *
     * @param value the value to search for
     * @return true if found, false otherwise
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
     * Adds a value to the binary search tree.
     *
     * Equal values are placed on the right.
     *
     * @param value the value to add
     */
    public void add(T value) {
        this.root = add(this.root, value);
    }

    /**
     * Finds the correct position and adds a new node.
     *
     * @param node the current node
     * @param value the value being added
     * @return the updated node
     */
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
     * Removes one matching value from the tree.
     *
     * If the value does not exist, nothing changes.
     *
     * @param value the value to remove
     */
    public void remove(T value) {
        this.root = remove(this.root, value);
    }

    /**
     * Searches for and removes a value from the tree.
     *
     * This method handles nodes with zero, one,
     * or two children.
     *
     * @param node the current node
     * @param value the value being removed
     * @return the updated node
     */
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
     * A node that stores a value and two child references.
     */
    private class Node {
        T data;
        Node left;
        Node right;

        /**
         * Creates a node with no children.
         *
         * @param data the value stored in the node
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Runs the Tree interface validation and additional tests.
     *
     * @param args command-line arguments
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
     * Checks whether an iterator returns the expected values.
     *
     * @param iterator the iterator being tested
     * @param array the expected values
     * @return true if all values match, false otherwise
     */
    private static boolean testTraversal(
            Iterator<Integer> iterator, int[] array) {

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
