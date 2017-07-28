package algorithms.search;

import java.util.Arrays;

public class BinarySearchArray {

    public static void main(String[] args) {

        int primes[] = {};

        for (int i = 2; i <= 100; ++i) {
            if (isPrime(i)) {
                primes = addOneIntToArray(primes, i);
                System.out.println("PRIME NO. " + i);
            }
        }

        int culprit = 50;

        System.out.println("");
        System.out.println("BINARY SEARCH");

        // sort the array
        Arrays.sort(primes);
        long startTime = System.nanoTime();
        // Implementation is Faster
        int searchIndex = binarySearchIndexOf(primes, culprit);
        long stopTime = System.nanoTime();

        System.out.println("OUR EXECUTION TIME IS " + ((stopTime - startTime) / 1000) + "ms");
        System.out.println("FOR VALUE " + primes[searchIndex]);
        System.out.println("AND INDEX " + searchIndex);

        System.out.println("");
        System.out.println("BINARY SEARCH JAVA");

        startTime = System.nanoTime();
        // Default implementation less faster - sometimes even by half
        searchIndex = Arrays.binarySearch(primes, culprit);
        stopTime = System.nanoTime();

        System.out.println("OUR EXECUTION TIME IS "
                + ((stopTime - startTime) / 1000) + "ms");
        System.out.println("FOR VALUE " + primes[searchIndex]);
        System.out.println("AND INDEX " + searchIndex);

        System.out.println("");
        System.out.println("LINEAR SEARCH");

        startTime = System.nanoTime();
        // Fast -depends on size of array or position of element
        searchIndex = linearSearchIndexOf(primes, culprit);
        stopTime = System.nanoTime();

        System.out.println("OUR EXECUTION TIME IS " + ((stopTime - startTime) / 1000) + "ms");
        System.out.println("FOR VALUE " + primes[searchIndex]);
        System.out.println("AND INDEX " + searchIndex);
    }

    public static int binarySearchIndexOf(int array[], int culprit) {
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        while (firstIndex <= culprit) {
            int mediumIndex = (firstIndex + lastIndex) / 2;
            if (culprit > array[mediumIndex]) {
                firstIndex = mediumIndex + 1;
            } else if (culprit < array[mediumIndex]) {
                lastIndex = mediumIndex - 1;
            } else {
                return mediumIndex;
            }
        }
        return -1;
    }

    public static int binarySearchIndexOf(int[] arr, int firstIndex, int lastIndex, int culprit) {
        if (lastIndex >= firstIndex) {
            int mid = (firstIndex + lastIndex) / 2;
            if (arr[mid] < culprit)
                return binarySearchIndexOf(arr, mid + 1, lastIndex, culprit);
            if (arr[mid] > culprit)
                return binarySearchIndexOf(arr, firstIndex, mid - 1, culprit);
            if (arr[mid] == culprit)
                return mid;
        }
        return -1;
    }

    public static int linearSearchIndexOf(int array[], int culprit) {
        for (int firstIndex = 0; array[firstIndex] <= culprit; firstIndex++) {
            if (array[firstIndex] == culprit) {
                return firstIndex;
            }
        }
        return -1;
    }

    public static int linearSearchIndexOf(int array[], int n, int key) {
        if (n < 0)
            return -1;
        if (array[n] == key)
            return n;
        return linearSearchIndexOf(array, n - 1, key);
    }

    public static int linearSearchIndexOfShortHand(int array[], int n, int key) {
        return (n < 0 || array[n] == key) ? n : linearSearchIndexOfShortHand(array, n - 1, key);
    }

    /**
     * Checks is a positive integer is a prime number
     */
    public static boolean isPrime(int number) {
        for (int check = 2; check < number; ++check) {
            if (number % check == 0) {
                return false;
            }
        }
        return true;
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

}
