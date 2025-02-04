public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }

        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int down = Math.max(1, dp[i + 1][j] - dungeon[i][j]);
                int right = Math.max(1, dp[i][j + 1] - dungeon[i][j]);
                dp[i][j] = Math.min(down, right);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonGame solver = new DungeonGame();

        int[][] dungeon1 = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(solver.calculateMinimumHP(dungeon1));

        // Test case 2
        int[][] dungeon2 = {{0}};
        System.out.println(solver.calculateMinimumHP(dungeon2));
    }
}
