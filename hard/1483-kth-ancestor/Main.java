import java.util.Arrays;

class TreeAncestor {
    private int[][] dp;
    private int maxSteps;

    public TreeAncestor(int n, int[] parent) {
        maxSteps = (int) (Math.log(n) / Math.log(2)) + 1;
        dp = new int[n][maxSteps];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = parent[i];
        }

        for (int j = 1; j < maxSteps; j++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][j - 1] != -1) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < maxSteps; j++) {
            if ((k & (1 << j)) != 0) {
                node = dp[node][j];
                if (node == -1) {
                    return -1;
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
        int n = 7;
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};

        TreeAncestor treeAncestor = new TreeAncestor(n, parent);

        System.out.println(treeAncestor.getKthAncestor(3, 1)); // Output: 1
        System.out.println(treeAncestor.getKthAncestor(5, 2)); // Output: 0
    }
}
