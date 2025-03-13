import java.util.Arrays;

public class ZeroArrayTransformationII {
    public static int minQueriesToZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        for (int k = 1; k <= queries.length; k++) {
            int[] temp = Arrays.copyOf(nums, n);
            boolean isZeroArray = applyQueries(temp, queries, k);
            if (isZeroArray) {
                return k;
            }
        }
        return -1;
    }

    private static boolean applyQueries(int[] nums, int[][] queries, int k) {
        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            for (int j = l; j <= r; j++) {
                nums[j] = Math.max(0, nums[j] - val);
            }
        }

        for (int num : nums) {
            if (num != 0) return false;
        }
        return true;
    }
}
