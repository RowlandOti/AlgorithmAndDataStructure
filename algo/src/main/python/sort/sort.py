
class Sorting(object):

    def bubbleSort(self, arr, n):
        for i in range(n-1):
            for j in range(n-1-i):
                if(arr[j] > arr[j+1]):
                    self.swap(arr, j, j+1)

    def selectionSort(self, arr, n):
        for i in range(n):
            # Assume min is the first element
            min = i
            # Check if there is other lower than current min
            for j in range(i+1, n-1):
                if(arr[min] > arr[j]):
                    # update min
                    min = j
            self.swap(arr,min,i)

    def insertionSort(self, arr, n):
        # Traverse through 1 to len(arr)
        for i in range(1, n):
            cursor = arr[i]
            # move elements of arr[0....i-1] (left-side), that are greater than key, to one position ahead of their current position
            j = i
            while (j >=0 and cursor < arr[j-1]):
                arr[j] = arr[j-1]
                j = j - 1
            # move current cursor to its correct position
            arr[j] = cursor

    def swap(self, arr, first, last):
        temp = arr[first]
        arr[first]= arr[last]
        arr[last] = temp

sort = Sorting()

arr = [12, 11, 13, 5, 6, 1, 7, 9, 3, 10]
print("********Bubble Sort**********")
sort.bubbleSort(arr, len(arr))
print(arr)
print("********Selection Sort**********")
sort.selectionSort(arr, len(arr))
print(arr)
print("********Insertion Sort**********")
sort.insertionSort(arr, len(arr))
print(arr)