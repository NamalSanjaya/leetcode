class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        int[][] testCases = {
                {5, 7, 7, 8, 8, 10},
                {5, 7, 7, 8, 8, 10},
                {},
                {1},
                {1},
                {2, 2, 2, 2, 2},
        };

        int[] targets = {8, 6, 0, 1, 2, 2};

        for (int i = 0; i < testCases.length; i++) {
            int[] result = solution.searchRange(testCases[i], targets[i]);
            System.out.println(targets[i] + " -> " + "[" + result[0] + " , " + result[1] + "]");
        }
    }
}
