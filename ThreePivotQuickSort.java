import java.util.ArrayList;
import java.util.Arrays;

public class ThreePivotQuickSort {

    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13, 44, 2, 20, 50, 6};
        System.out.println("Original array: " + Arrays.toString(arr));
        int[] sorted = threePivotQuickSort(arr);
        System.out.println("Sorted array  : " + Arrays.toString(sorted));
    }

    public static int[] threePivotQuickSort(int[] arr) {
        // Base case: an array with 0 or 1 elements is already sorted.
        if(arr.length <= 1) {
            return arr;
        }
        int n = arr.length;
        // For arrays with 2 elements, sort and return.
        if(n == 2) {
            if(arr[0] > arr[1]) {
                int temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
            }
            return arr;
        }

        // Choose three pivots:
        int mid = n / 2;
        int p1 = arr[0];
        int p2 = arr[mid];
        int p3 = arr[n - 1];

        // Order the pivots: ensure p1 <= p2 <= p3.
        if (p1 > p2) {
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }
        if (p1 > p3) {
            int temp = p1;
            p1 = p3;
            p3 = temp;
        }
        if (p2 > p3) {
            int temp = p2;
            p2 = p3;
            p3 = temp;
        }

        // Partition the array into 4 parts:
        // left: elements less than p1.
        // mid1: elements >= p1 and less than p2.
        // mid2: elements >= p2 and less than p3.
        // right: elements greater than or equal to p3.
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> mid1 = new ArrayList<>();
        ArrayList<Integer> mid2 = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            // Skip the positions of the chosen pivots.
            if(i == 0 || i == mid || i == n - 1) {
                continue;
            }
            int x = arr[i];
            if(x < p1) {
                left.add(x);
            } else if(x < p2) {
                mid1.add(x);
            } else if(x < p3) {
                mid2.add(x);
            } else {
                right.add(x);
            }
        }
        
        // Recursively sort each partition.
        int[] leftArr = threePivotQuickSort(listToArray(left));
        int[] mid1Arr = threePivotQuickSort(listToArray(mid1));
        int[] mid2Arr = threePivotQuickSort(listToArray(mid2));
        int[] rightArr = threePivotQuickSort(listToArray(right));
        
        // Combine sorted parts and pivots into one result.
        int totalLength = leftArr.length + mid1Arr.length + mid2Arr.length + rightArr.length + 3;
        int[] result = new int[totalLength];
        int index = 0;
        
        // Copy left partition.
        for (int num : leftArr) {
            result[index++] = num;
        }
        // Insert first pivot.
        result[index++] = p1;
        // Copy mid1 partition.
        for (int num : mid1Arr) {
            result[index++] = num;
        }
        // Insert second pivot.
        result[index++] = p2;
        // Copy mid2 partition.
        for (int num : mid2Arr) {
            result[index++] = num;
        }
        // Insert third pivot.
        result[index++] = p3;
        // Copy right partition.
        for (int num : rightArr) {
            result[index++] = num;
        }
        
        return result;
    }
    
    // Helper method to convert an ArrayList<Integer> to an int[].
    public static int[] listToArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}