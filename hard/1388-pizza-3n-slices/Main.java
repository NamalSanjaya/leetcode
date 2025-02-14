class Solution {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length / 3;
        return Math.max(getMaxSlices(slices, 0, slices.length - 2, n),
                getMaxSlices(slices, 1, slices.length - 1, n));
    }

    private int getMaxSlices(int[] slices, int start, int end, int n) {
        int len = end - start + 1;
        int[][] dp = new int[len + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    dp[i][j] = slices[start];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j],
                            (i >= 2 ? dp[i - 2][j - 1] : 0) + slices[start + i - 1]);
                }
            }
        }
        return dp[len][n];
    }
}
