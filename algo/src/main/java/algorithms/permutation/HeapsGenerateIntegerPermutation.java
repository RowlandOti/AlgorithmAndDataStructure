package algorithms.permutation;

import java.util.Arrays;
import java.util.Scanner;

public class HeapsGenerateIntegerPermutation {

	public static void main(String[] args) {

		System.out.println("Enter the number sequence: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

		int[] v = parseIntToArray(n);
		Arrays.sort(v);

		permute(v, v.length);
	}

	/**
	 * Create an array of individual int digits
	 */
	static int[] parseIntToArray(int n) {
		int[] intChar = new int[0];
		int i = 0;
		while (n > 0) {
			int d = n / 10;
			int k = n - d * 10;
			n = d;
			intChar = addOneIntToArray(intChar, k);
			i++;
		}
		return intChar;
	}

	/**
	 * Create new array after adding new element
	 */
	static int[] addOneIntToArray(int[] initialArray, int newValue) {
		int[] newArray = new int[initialArray.length + 1];

		for (int index = 0; index < initialArray.length; index++) {
			newArray[index] = initialArray[index];
		}

		newArray[newArray.length - 1] = newValue;
		return newArray;
	}

	/**
	 * Calculate the No. of permutations
	 *
	 * @return
	 */
	static void permute(int[] intDigitsArray, int intDigitsArrayLength) {
		if (intDigitsArrayLength == 1) {
			System.out.println(Arrays.toString(intDigitsArray));
		} else {
			for (int i = 0; i < intDigitsArrayLength; i++) {
				permute(intDigitsArray, intDigitsArrayLength - 1);
				if (intDigitsArrayLength % 2 == 1) {
					intDigitsArray = swap(intDigitsArray, intDigitsArrayLength -1, i);
				} else {
					intDigitsArray = swap(intDigitsArray, intDigitsArrayLength -1, 0);
				}
			}
		}
	}

	static int[] swap(int[] a, int n, int i) {
		int temp = a[n];
		a[n] = a[i];
		a[i] = temp;
		return a;
	}
}
