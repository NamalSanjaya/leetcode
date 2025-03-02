import java.util.*;

public class EscapeLargeMaze {

    private static final int LIMIT = 200;
    private static final int GRID_SIZE = 1000000;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(b[0] + "," + b[1]);
        }

        return bfs(source, target, blockedSet) && bfs(target, source, blockedSet);
    }

    private boolean bfs(int[] start, int[] end, Set<String> blockedSet) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start[0] + "," + start[1]);

        int count = 0;
        while (!queue.isEmpty() && count < LIMIT) {
            int[] cur = queue.poll();
            count++;
            for (int[] dir : DIRECTIONS) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                String key = x + "," + y;
                if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE &&
                        !blockedSet.contains(key) && visited.add(key)) {
                    if (x == end[0] && y == end[1]) {
                        return true;
                    }
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return count >= LIMIT;
    }

    public static void main(String[] args) {
        EscapeLargeMaze solver = new EscapeLargeMaze();
        System.out.println(solver.isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println(solver.isEscapePossible(new int[][]{}, new int[]{0, 0}, new int[]{999999, 999999}));
    }

}
