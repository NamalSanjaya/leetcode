import java.util.*;

class Solution {
    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] result = new int[n];

        for (int start = 0; start < n; start++) {
            result[start] = bfsTime(graph, n, start);
        }

        return result;
    }

    private int bfsTime(List<List<Integer>> graph, int n, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] time = new int[n];
        Arrays.fill(time, -1);

        queue.offer(start);
        time[start] = 0;

        int maxTime = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentTime = time[node];

            for (int neighbor : graph.get(node)) {
                if (time[neighbor] == -1) {
                    if (neighbor % 2 == 0) {
                        time[neighbor] = currentTime + 2;
                    } else {
                        time[neighbor] = currentTime + 1;
                    }
                    queue.offer(neighbor);
                    maxTime = Math.max(maxTime, time[neighbor]);
                }
            }
        }

        return maxTime;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 7;
        int[][] edges = {
                {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}
        };

        int[] result = solution.timeTaken(n, edges);
        System.out.println("Time Taken: " + Arrays.toString(result));
    }
}
