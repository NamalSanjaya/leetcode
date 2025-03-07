class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0, right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSplit(int[] nums, int k, int maxSum) {
        int subarrays = 1, currentSum = 0;

        for (int num : nums) {
            currentSum += num;

            if (currentSum > maxSum) {
                subarrays++;
                currentSum = num;

                if (subarrays > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {7, 2, 5, 10, 8};
        int k1 = 2;
        System.out.println(solution.splitArray(nums1, k1));

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        System.out.println(solution.splitArray(nums2, k2));
    }
}
