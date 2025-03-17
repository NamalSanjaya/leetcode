import java.util.*;

class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> seenSums = new HashSet<>();
        seenSums.add(0);
        int prefixSum = 0, count = 0;

        for (int num : nums) {
            prefixSum += num;

            if (seenSums.contains(prefixSum - target)) {
                count++;
                seenSums.clear();
                prefixSum = 0;
                seenSums.add(0);
            } else {
                seenSums.add(prefixSum);
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 2;
        System.out.println(solution.maxNonOverlapping(nums1, target1));

        int[] nums2 = {-1, 3, 5, 1, 4, 2, -9};
        int target2 = 6;
        System.out.println(solution.maxNonOverlapping(nums2, target2));
    }
}
