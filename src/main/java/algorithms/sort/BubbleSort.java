package algorithms.sort;

import java.util.Scanner;

import static algorithms.sort.HeapSort.swap;

/**
 * Created by Rowland on 12/6/2016.
 */
public class BubbleSort {

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
        sort(arr, n);
        /* Print sorted Array */
        System.out.println("\nElements after sorting ");
        for (i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int A[], int n) {

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {
                if (A[j] > A[j+1]) {
                    swap(A, j, j+1);
                }
            }
        }
    }

    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int moveFront, int moveBack) {
        int tmp = arr[moveFront];
        arr[moveFront] = arr[moveBack];
        arr[moveBack] = tmp;
    }
}
