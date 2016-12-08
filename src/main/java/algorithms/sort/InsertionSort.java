package algorithms.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Rowland on 12/6/2016.
 */
public class InsertionSort {

    /* Main method */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insertion Sort Test\n");

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
        sort(arr, n);
    }

    // Selection sort method
    public static void sort(int[] A, int n) {
        /* Print sorted Array */
        System.out.println("\nElements assortment ");
        for (int i = 0; i < n; i++) {

            int tmp = A[i];
            int j = i;

            while(j>0 && tmp < A[j -1]) {
                A[j] = A[j-1];
                j = j-1;
            }

            A[j] = tmp;

            System.out.println();
            System.out.print(Arrays.toString(A));
            System.out.println();
        }
    }

    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int moveFront, int moveBack) {
        int tmp = arr[moveFront];
        arr[moveFront] = arr[moveBack];
        arr[moveBack] = tmp;
    }

}
