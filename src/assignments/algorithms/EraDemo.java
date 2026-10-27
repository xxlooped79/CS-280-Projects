package assignments.algorithms;

import java.util.Random;
import adt.PriorityQueue;
import assignments.datastructures.PriorityEnqueuer;
import assignments.datastructures.PriorityDequeuer;
import assignments.datastructures.MinHeap;

/**
 * Empirical Runtime Analysis for priority queue dequeue.
 *
 * Tests how long it takes to dequeue one element
 * from a priority queue containing N elements.
 */
public class EraDemo {

    /**
     * Measures the time needed to dequeue one value.
     *
     * @param queue the priority queue being tested
     * @param n the number of elements
     * @param random the random number generator
     * @return dequeue runtime in seconds
     */
    public static double timeDequeue(
            PriorityQueue<Integer> queue,
            int n, Random random) {

        // Fill the queue before starting the timer.
        for (int i = 0; i < n; i++) {
            int value = random.nextInt(n) + 1;
            queue.enqueue(value);
        }

        // Only measure the dequeue operation.
        long start = System.nanoTime();

        queue.dequeue();

        long end = System.nanoTime();

        return (end - start) / 1_000_000_000.0;
    }

    /**
     * Runs the empirical runtime analysis.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Random random = new Random();

        int[] sizes = {
            100, 500, 1000, 2000,
            4000, 8000, 16000,
            32000, 64000, 128000
        };

        System.out.println(
            "N,PriorityEnqueuer,PriorityDequeuer,MinHeap"
        );

        for (int n : sizes) {

            // Create a fresh queue for each measurement.
            double enqueuer = timeDequeue(
                new PriorityEnqueuer<Integer>(),
                n, random
            );

            double dequeuer = timeDequeue(
                new PriorityDequeuer<Integer>(),
                n, random
            );

            double heap = timeDequeue(
                new MinHeap<Integer>(),
                n, random
            );

            System.out.println(
                n + "," + enqueuer + ","
                + dequeuer + "," + heap
            );
        }
    }
}
