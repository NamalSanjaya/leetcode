class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long[] prefixSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        return countWhileMergeSort(prefixSum, 0, prefixSum.length, lower, upper);
    }

    private int countWhileMergeSort(long[] prefixSum, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int count = countWhileMergeSort(prefixSum, start, mid, lower, upper)
                + countWhileMergeSort(prefixSum, mid, end, lower, upper);

        int j = mid, k = mid, t = mid;
        long[] temp = new long[end - start];
        int r = 0;

        for (int i = start, p = 0; i < mid; i++, p++) {
            while (k < end && prefixSum[k] - prefixSum[i] < lower) k++;
            while (j < end && prefixSum[j] - prefixSum[i] <= upper) j++;
            while (t < end && prefixSum[t] < prefixSum[i]) temp[r++] = prefixSum[t++];
            temp[r++] = prefixSum[i];
            count += j - k;
        }

        System.arraycopy(temp, 0, prefixSum, start, r);
        return count;
    }

        public static void main(String[] args) {
            Solution solution = new Solution();

            int[] nums1 = {-2, 5, -1};
            int lower1 = -2;
            int upper1 = 2;
            System.out.println("Test Case 1: " + solution.countRangeSum(nums1, lower1, upper1));

            int[] nums2 = {0};
            int lower2 = 0;
            int upper2 = 0;
            System.out.println("Test Case 2: " + solution.countRangeSum(nums2, lower2, upper2));

            int[] nums3 = {1, 2, -3, 4, -2, -1};
            int lower3 = -2;
            int upper3 = 3;
            System.out.println("Test Case 3: " + solution.countRangeSum(nums3, lower3, upper3));

            int[] nums4 = {-1};
            int lower4 = -1;
            int upper4 = 0;
            System.out.println("Test Case 4: " + solution.countRangeSum(nums4, lower4, upper4));
        }


}
