package algorithms.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Rowland on 8/23/2017.
 */

public class QuickSort {

    /* Main method */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bubble Sort Test\n");

        int n, i;
        /* Accept number of elements */
        System.out.println("Enter number of integer elements");
        n = scan.nextInt();
        /* Make array of n elements */
        int arr[] = new int[n];
        /* Accept elements */
        System.out.println("\nEnter " + n + " integer elements");
        for (i = 0; i < n; i++)
            arr[i] = scan.nextInt();
        /* Call method sort */
        sort(arr, 0, n-1);
        System.out.print(Arrays.toString(arr));
    }

    public static void sort(int A[], int start, int end) {
        /* Print sorted Array */
        //System.out.println("\nElements assortment ");
        if(start < end) {
            int pivot_pointer = partition(A,start,end);
            sort(A,start,pivot_pointer-1);
            sort(A,pivot_pointer +1, end);
        }

        /*System.out.println();
        System.out.print(Arrays.toString(A));
        System.out.println();*/
    }

    public static int partition(int[] A, int start, int end) {
        int pivot = A[end];
        // index of smaller element
        int i = start -1;

        for(int j = start; j<end; j++) {
            if(A[j] <= pivot) {
                i++;
                // All elements < pivot moved to the left
                swap(A, i, j);
            }
        }
        // move pivot to correct position
        swap(A,i+1,end);
        return i+1;
    }

    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int moveFront, int moveBack) {
        int tmp = arr[moveFront];
        arr[moveFront] = arr[moveBack];
        arr[moveBack] = tmp;
    }
}
