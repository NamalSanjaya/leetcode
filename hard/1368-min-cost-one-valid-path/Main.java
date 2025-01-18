import java.util.*;

public class MinimumCostPath {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] cost = new int[m][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);

        deque.offer(new int[]{0, 0});
        cost[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int x = current[0], y = current[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + directions[i][0];
                int newY = y + directions[i][1];
                int newCost = cost[x][y] + (grid[x][y] == i + 1 ? 0 : 1);

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && newCost < cost[newX][newY]) {
                    cost[newX][newY] = newCost;
                    if (grid[x][y] == i + 1) {
                        deque.offerFirst(new int[]{newX, newY});
                    } else {
                        deque.offerLast(new int[]{newX, newY});
                    }
                }
            }
        }

        return cost[m - 1][n - 1]; // Minimum cost to reach bottom-right
    }

    public static void main(String[] args) {
        MinimumCostPath solution = new MinimumCostPath();

        int[][] grid1 = {
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {1, 1, 1, 1},
                {2, 2, 2, 2}
        };
        System.out.println(solution.minCost(grid1));

        int[][] grid2 = {
                {1, 1, 3},
                {3, 2, 2},
                {1, 1, 4}
        };
        System.out.println(solution.minCost(grid2));

        int[][] grid3 = {
                {1, 2},
                {4, 3}
        };
        System.out.println(solution.minCost(grid3));
    }
}
