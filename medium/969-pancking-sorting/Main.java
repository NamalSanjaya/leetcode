import java.util.*;

public class PancakeSorting {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();

        for (int size = arr.length; size > 1; size--) {
            int maxIndex = findMaxIndex(arr, size);

            if (maxIndex != size - 1) {
                if (maxIndex != 0) {
                    flip(arr, maxIndex + 1);
                    result.add(maxIndex + 1);
                }

                flip(arr, size);
                result.add(size);
            }
        }

        return result;
    }

    private int findMaxIndex(int[] arr, int n) {
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void flip(int[] arr, int k) {
        int left = 0, right = k - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        PancakeSorting ps = new PancakeSorting();
        System.out.println(ps.pancakeSort(new int[]{3, 2, 4, 1}));
        System.out.println(ps.pancakeSort(new int[]{1, 2, 3}));
    }
}
