import java.util.*;

class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int MAX_STEPS = 20000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockedSet = new HashSet<>();
        for (int[] block : blocked) {
            blockedSet.add(block[0] + "," + block[1]);
        }

        return bfs(source, target, blockedSet) && bfs(target, source, blockedSet);
    }

    private boolean bfs(int[] start, int[] end, Set<String> blockedSet) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start[0] + "," + start[1]);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                if (current[0] == end[0] && current[1] == end[1] || steps > MAX_STEPS) {
                    return true;
                }
                for (int[] dir : DIRECTIONS) {
                    int x = current[0] + dir[0];
                    int y = current[1] + dir[1];
                    String key = x + "," + y;
                    if (x >= 0 && x < 1e6 && y >= 0 && y < 1e6 && !blockedSet.contains(key) && !visited.contains(key)) {
                        visited.add(key);
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            steps++;
        }
        return false;
    }
}