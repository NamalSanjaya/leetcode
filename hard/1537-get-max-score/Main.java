import java.util.*;

public class MaximumScorePath {
    public int maxSum(int[] nums1, int[] nums2) {
        int MOD = 1_000_000_007;
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0, result = 0;

        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && (j == nums2.length || nums1[i] < nums2[j])) {
                sum1 += nums1[i++];
            } else if (j < nums2.length && (i == nums1.length || nums1[i] > nums2[j])) {
                sum2 += nums2[j++];
            } else {
                result += Math.max(sum1, sum2) + nums1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        result += Math.max(sum1, sum2);
        return (int) (result % MOD);
    }

    public static void main(String[] args) {
        MaximumScorePath msp = new MaximumScorePath();
        System.out.println(msp.maxSum(new int[]{2,4,5,8,10}, new int[]{4,6,8,9}));
        System.out.println(msp.maxSum(new int[]{1,3,5,7,9}, new int[]{3,5,100}));
        System.out.println(msp.maxSum(new int[]{1,2,3,4,5}, new int[]{6,7,8,9,10}));
    }
}
