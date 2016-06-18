package algorithms.permutation;

import java.util.Arrays;

public class HeapPermute {

	public void permute(int[] intDigitsArray, int intDigitsArrayLength) {
		if (intDigitsArrayLength == 1) {
			System.out.println(Arrays.toString(intDigitsArray));
		} else {
			for (int i = 0; i < intDigitsArrayLength; i++) {
				permute(intDigitsArray, intDigitsArrayLength - 1);
				if (intDigitsArrayLength % 2 == 1) {
					swap(intDigitsArray, 0, intDigitsArrayLength-1);
				} else {
					swap(intDigitsArray, i, intDigitsArrayLength-1);
				}
			}
		}
	}

	private static void swap(int[] v, int i, int j) {
		int temp = v[i];
		v[i] = v[j];
		v[j] = temp;
	}

	public static void main(String[] args) {
		int[] ns = {1, 2, 3, 4};
		new HeapPermute().permute(ns, ns.length);
	}


}
