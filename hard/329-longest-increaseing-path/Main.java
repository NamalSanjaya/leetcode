class Solution {
    private int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private int[][] memo;
    private int rows, cols;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        rows = matrix.length;
        cols = matrix[0].length;
        memo = new int[rows][cols];
        int maxLen = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j));
            }
        }

        return maxLen;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];

        int maxLen = 1;

        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && matrix[x][y] > matrix[i][j]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, x, y));
            }
        }

        memo[i][j] = maxLen;
        return maxLen;
    }
}
