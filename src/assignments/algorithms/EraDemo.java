package assignments.algorithms;

import assignments.datastructures.Vector;
import assignments.datastructures.LinkedList;
import assignments.datastructures.CircularLinkedList;

public class EraDemo {

    private static double timeVector(int n) {
        Vector<Integer> list = new Vector<>();

        // Prepare the list - not timed
        for (int i = 0; i < n; i++) {
            list.insert(list.length(), 0);
        }

        long start = System.nanoTime();

        list.insert(0, 0);

        long end = System.nanoTime();

        return (end - start) / 1e9;
    }

    private static double timeLinkedList(int n) {
        LinkedList<Integer> list = new LinkedList<>();

        // Prepare the list - not timed
        for (int i = 0; i < n; i++) {
            list.insert(0, 0);
        }

        long start = System.nanoTime();

        list.insert(0, 0);

        long end = System.nanoTime();

        return (end - start) / 1e9;
    }

    private static double timeCircularLinkedList(int n) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        // Prepare the list - not timed
        for (int i = 0; i < n; i++) {
            list.insert(0, 0);
        }

        long start = System.nanoTime();

        list.insert(0, 0);

        long end = System.nanoTime();

        return (end - start) / 1e9;
    }

    public static void main(String[] args) {

        System.out.println("N,Vector,LinkedList,CircularLinkedList");

        for (int n = 1000; n <= 1000000; n *= 2) {

            double vectorTime = timeVector(n);
            double linkedListTime = timeLinkedList(n);
            double circularLinkedListTime = timeCircularLinkedList(n);

            System.out.println(
                n + "," +
                vectorTime + "," +
                linkedListTime + "," +
                circularLinkedListTime
            );
        }
    }
}