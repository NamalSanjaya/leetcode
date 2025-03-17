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

}
