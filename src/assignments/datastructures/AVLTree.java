
package assignments.datastructures;

import java.util.ArrayList;
import java.util.Iterator;

import adt.Queue;
import adt.Tree;

/**
 * An AVL tree is a binary search tree that stays balanced.
 * Each node has at most two children.
 * Rotations help keep the tree balanced.
 *
 * @param <T> the type of values stored in the tree
 */
public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node root;

    /**
     * Create an empty AVL tree.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Count the values in the tree.
     * @return the number of values
     */
    public int length() {
        return length(this.root);
    }

    private int length(Node node) {
        if (node == null) {
            return 0;
        }

        return node.count + length(node.left) + length(node.right);
    }

    /**
     * Find the largest number of children a node has.
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
     * Find the height of the tree.
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

        return node.height;
    }

    /**
     * Update the height after changing a node.
     */
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(
                height(node.left), height(node.right)
            );
        }
    }

    /**
     * Visit the root, left side, then right side.
     * @return a preorder iterator
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

        for (int i = 0; i < node.count; i++) {
            values.add(node.data);
        }

        preorder(node.left, values);
        preorder(node.right, values);
    }

    /**
     * Visit the left side, root, then right side.
     * @return an inorder iterator
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

        for (int i = 0; i < node.count; i++) {
            values.add(node.data);
        }

        inorder(node.right, values);
    }

    /**
     * Visit the left and right sides before the root.
     * @return a postorder iterator
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

        for (int i = 0; i < node.count; i++) {
            values.add(node.data);
        }
    }

    /**
     * Visit the tree one level at a time.
     * @return a level order iterator
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

            for (int i = 0; i < current.count; i++) {
                values.add(current.data);
            }

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
     * Go through the values in sorted order.
     * @return an inorder iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }

    /**
     * Check if a value is in the tree.
     * @param value the value to find
     * @return true if found, otherwise false
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
     * Add a value and balance the tree.
     * Duplicate values are stored in the same node.
     * @param value the value to add
     */
    public void add(T value) {
        this.root = add(this.root, value);
    }

    private Node add(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        int comparison = value.compareTo(node.data);

        if (comparison < 0) {
            node.left = add(node.left, value);
        } else if (comparison > 0) {
            node.right = add(node.right, value);
        } else {
            node.count++;
            return node;
        }

        return balance(node);
    }

    /**
     * Remove one value and balance the tree.
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

            // Remove one duplicate.
            if (node.count > 1) {
                node.count--;
                return node;
            }

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
            node.count = smallest.count;

            // Remove the replacement node.
            smallest.count = 1;
            node.right = remove(node.right, smallest.data);
        }

        return balance(node);
    }

    /**
     * Check if a node needs to be rotated.
     * @param node the node being checked
     * @return the balanced node
     */
    private Node balance(Node node) {
        if (node == null) {
            return null;
        }

        updateHeight(node);

        int difference = height(node.left) - height(node.right);

        // Left side is too tall.
        if (difference > 1) {
            if (height(node.left.left) < height(node.left.right)) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        // Right side is too tall.
        if (difference < -1) {
            if (height(node.right.right) < height(node.right.left)) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Rotate a node to the left.
     * @param node the node to rotate
     * @return the new root of this part of the tree
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right;

        node.right = newRoot.left;
        newRoot.left = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Rotate a node to the right.
     * @param node the node to rotate
     * @return the new root of this part of the tree
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;

        node.left = newRoot.right;
        newRoot.right = node;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * A node stores a value and its children.
     */
    private class Node {
        T data;
        Node left;
        Node right;
        int height;
        int count;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
            this.count = 1;
        }
    }

    /**
     * Run tests for the AVL tree.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Tree.validate(new AVLTree<>());

        AVLTree<Integer> tree = new AVLTree<>();

        // Test an empty tree.
        assert tree.length() == 0;
        assert tree.height() == 0;

        // Test adding values.
        tree.add(10);
        tree.add(20);
        tree.add(30);

        assert tree.length() == 3;
        assert tree.height() == 1;
        assert tree.contains(10);
        assert tree.contains(20);
        assert tree.contains(30);

        // Test left rotation.
        assert tree.preorder().next() == 20;

        // Test removing a value.
        tree.remove(10);
        assert !tree.contains(10);
        assert tree.length() == 2;

        // Test right rotation.
        AVLTree<Integer> rightTree = new AVLTree<>();
        rightTree.add(30);
        rightTree.add(20);
        rightTree.add(10);

        assert rightTree.preorder().next() == 20;

        // Test left-right rotation.
        AVLTree<Integer> leftRightTree = new AVLTree<>();
        leftRightTree.add(30);
        leftRightTree.add(10);
        leftRightTree.add(20);

        assert leftRightTree.preorder().next() == 20;

        // Test right-left rotation.
        AVLTree<Integer> rightLeftTree = new AVLTree<>();
        rightLeftTree.add(10);
        rightLeftTree.add(30);
        rightLeftTree.add(20);

        assert rightLeftTree.preorder().next() == 20;

        // Test removing a node with two children.
        AVLTree<Integer> testTree = new AVLTree<>();

        testTree.add(50);
        testTree.add(30);
        testTree.add(70);
        testTree.add(20);
        testTree.add(40);
        testTree.add(60);
        testTree.add(80);

        testTree.remove(50);

        assert !testTree.contains(50);
        assert testTree.length() == 6;

        // Test duplicate values.
        testTree.add(40);
        assert testTree.length() == 7;

        testTree.remove(40);
        assert testTree.contains(40);
        assert testTree.length() == 6;

        // Test removing a missing value.
        testTree.remove(100);
        assert testTree.length() == 6;

        System.out.println("AVLTree passes all tests.");
    }
}
