package algorithms.sort;

import java.util.Scanner;

public class SelectionSort {

	/* Main method */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Selection Sort Test\n");

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

	// Selection sort method
	public static void sort(int[] list, int n) {
		for (int i = 0; i < n - 1; i++) {
			int currentMin = list[i];
			int currentMinIndex = i;
			for (int j = i; j < n; j++) {
				if (currentMin > list[j]) {
					currentMin = list[j];
					currentMinIndex = j;
				}
			}

			if (currentMinIndex != i) {
				/*list[currentMinIndex] = list[i];
				list[i] = currentMin;*/
				swap(list, i,currentMinIndex);
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
