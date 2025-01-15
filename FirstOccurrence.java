public class FirstOccurrence {
    public static int findFirstOccurrence(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                result = mid;
                right = mid - 1;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4, 5};
        int x = 3;
        System.out.println("First occurrence of " + x + " is at index: " + findFirstOccurrence(arr, x));
    }
}
