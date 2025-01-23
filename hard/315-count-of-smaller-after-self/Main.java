import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] counts = new Integer[n];
        int[] indices = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.fill(counts, 0);
        mergeSort(nums, indices, counts, 0, n - 1);
        return Arrays.asList(counts);
    }

    private void mergeSort(int[] nums, int[] indices, Integer[] counts, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, counts, left, mid);
        mergeSort(nums, indices, counts, mid + 1, right);
        merge(nums, indices, counts, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, Integer[] counts, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                temp[k++] = indices[j++];
                rightCount++;
            } else {
                counts[indices[i]] += rightCount;
                temp[k++] = indices[i++];
            }
        }

        while (i <= mid) {
            counts[indices[i]] += rightCount;
            temp[k++] = indices[i++];
        }

        while (j <= right) {
            temp[k++] = indices[j++];
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            indices[i] = temp[k];
        }
    }

    public static void main(String[] args) {
        CountSmallerNumbersAfterSelf solver = new CountSmallerNumbersAfterSelf();
        int[] nums = {5, 2, 6, 1};
        System.out.println(solver.countSmaller(nums));
    }
}
