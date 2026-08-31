package assignments.sorting;

/**
* Swap Adjacent elemenets over and over until the whole array is sorted.
*/

public class Bubblesort extends SortingAlgorithm
{

    /** 
     * sorting an array in-place using bubble sort 
     * 
     * Post-condition: 'array' is sorted in ascending order 
     * 
     * @param array an array of integers 
     */
    public void sort( Integer[] array){
        for(int k= array.length; k >= 2; k--) {            //k is the length of the sub
            for (int i=0; i < k-1; i ++) {
                if (array[1] > array[i+1]) {
                //swap adjcent items (i and i+1)
                swap(array,i,i+1);
                }
            }
        }
    }

 /** 
  * Swap two elements within an array 
  * 
  * @param array the array to swap values in
  * @param i the first index to swap
  * @param j the second index to swap
  * 
  */
private void swap(Integer[] array, int i, int j) {
    Integer temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    }
public static void main(String[] args)
{
    SortingAlgorithm.validate(new Bubblesort());
    System.out.println("Bubblesort has passed all tests");

    // fill an array with random numbers

    int N = 5;
    Integer[] array = new Integer[N];

    for(int i = 0; i < array.length; i++) {
        array[i] = (int)(N*Math.random());
    }

    //measuring runtime

    SortingAlgorithm sorter = new Bubblesort();
    long start = System.nanoTime();
    sorter.sort(array);
    long end = System.nanoTime();
    double duration = (end - start) / (1e9);

    System.out.println("Array size " +N);
    System.out.println("Total duration: "+duration);
}
}