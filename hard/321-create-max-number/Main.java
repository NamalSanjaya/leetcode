import java.util.*;

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxResult = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] maxSub1 = getMaxSubsequence(nums1, i);
            int[] maxSub2 = getMaxSubsequence(nums2, k - i);
            int[] merged = merge(maxSub1, maxSub2);

            if (greater(merged, 0, maxResult, 0)) {
                maxResult = merged;
            }
        }
        return maxResult;
    }

    private int[] getMaxSubsequence(int[] nums, int length) {
        int[] result = new int[length];
        int drop = nums.length - length;
        Stack<Integer> stack = new Stack<>();

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num && drop > 0) {
                stack.pop();
                drop--;
            }
            stack.push(num);
        }

        for (int i = 0; i < length; i++) {
            result[i] = stack.get(i);
        }
        return result;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (greater(nums1, i, nums2, j)) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        return merged;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}
