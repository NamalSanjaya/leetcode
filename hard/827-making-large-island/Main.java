import java.util.*;

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] ids = new int[n][n];
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int id = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && ids[i][j] == 0) {
                    int size = bfs(grid, i, j, ids, id);
                    sizeMap.put(id, size);
                    id++;
                }
            }
        }

        int max = sizeMap.values().stream().max(Integer::compare).orElse(0);

        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int sum = 0;
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                            int neighborId = ids[ni][nj];
                            if (!seen.contains(neighborId)) {
                                sum += sizeMap.get(neighborId);
                                seen.add(neighborId);
                            }
                        }
                    }
                    int current = sum + 1;
                    max = Math.max(max, current);
                }
            }
        }

        return max;
    }

    private int bfs(int[][] grid, int i, int j, int[][] ids, int id) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        ids[i][j] = id;
        int size = 0;
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            size++;
            for (int[] dir : dirs) {
                int ni = cell[0] + dir[0];
                int nj = cell[1] + dir[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1 && ids[ni][nj] == 0) {
                    ids[ni][nj] = id;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        return size;
    }
}