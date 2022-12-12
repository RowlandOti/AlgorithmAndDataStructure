package algorithms.permutation;

public class HeapsIntegerPermutation {

	public static void main(String[] args) {

		int[] intChar = parseIntToArray(134);
		System.out.println("Integer dimension size " +intChar.length);
		int z = calculateNoOfPermutations(intChar.length);
		System.out.println("No. of Permutations "+ z);
		int k = factorial(12);
		System.out.println("Factorial "+ intChar.length);

		generate(intChar.length, intChar);
	}

	public static int[] parseIntToArray(int n) {
		int[] intChar = new int[0];
		int i = 0;
		while (n > 0) {
			int d = n / 10;
			int k = n - d * 10;
			n = d;
			System.out.println(k);
			intChar = addOneIntToArray(intChar, k);
			i++;
		}
		return intChar;
	}

	/**
	 * Create new array after adding new element
	 */
	public static int[] addOneIntToArray(int[] initialArray, int newValue) {

		int[] newArray = new int[initialArray.length + 1];
		for (int index = 0; index < initialArray.length; index++) {
			newArray[index] = initialArray[index];
		}

		newArray[newArray.length - 1] = newValue;
		return newArray;
	}

	public static int calculateNoOfPermutations(int numIntChar) {
		return factorial(numIntChar);
	}

	public static int factorial(int n) {
		int k = (1 > n) ? 1 : n * factorial(n - 1);
		return k;
	}

	public static void generate(int n, int[] array) {
		if (n == 1)
			System.out.println(array.length);

			for (int i = 0; i < n - 1; i++) {
				generate(n - 1, array);
				if (n % 2 == 0) {
					array = swap(i, n - 1, array);
				} else {
					array = swap(0, n - 1, array);
				}
			}
			// generate(n - 1, array);
	}

	public static int[] swap(int i, int j,int[] array) {
		  int tmp = array[i];
		  array[i] = array[j];
		  array[j] = tmp;
		  return array;
		}

}
