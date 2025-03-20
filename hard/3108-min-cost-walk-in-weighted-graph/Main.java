// NOTE: THIS SOLUTION IS NOT PASSED ALL TEST CASES. THIS HAS MEMROY EXCEEDED ISSUES FOR
// CERTAIN TEST CASES

import java.util.*;

public class MinimumCostWalk {
    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int[][] minAndCost = new int[n][n];
        for (int[] row : minAndCost) Arrays.fill(row, -1);

        for (int start = 0; start < n; start++) {
            bfs(graph, n, start, minAndCost);
        }

        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0], t = query[i][1];
            result[i] = minAndCost[s][t];
        }
        return result;
    }

    private static void bfs(Map<Integer, List<int[]>> graph, int n, int start, int[][] minAndCost) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1});
        boolean[] visited = new boolean[n];
        visited[start] = true;
        minAndCost[start][start] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], currAnd = curr[1];

            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0], edgeWeight = neighbor[1];
                int newAnd = (currAnd == -1) ? edgeWeight : (currAnd & edgeWeight);

                if (!visited[nextNode] || minAndCost[start][nextNode] > newAnd) {
                    visited[nextNode] = true;
                    minAndCost[start][nextNode] = newAnd;
                    queue.offer(new int[]{nextNode, newAnd});
                }
            }
        }
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[][] edges1 = {{0, 1, 7}, {1, 3, 7}, {1, 2, 1}, {2, 1, 1}};
        int[][] query1 = {{0, 3}, {3, 4}};
        System.out.println(Arrays.toString(minimumCost(n1, edges1, query1)));

        int n2 = 3;
        int[][] edges2 = {{0, 2, 7}, {0, 1, 15}, {1, 2, 6}, {1, 2, 1}};
        int[][] query2 = {{1, 2}};
        System.out.println(Arrays.toString(minimumCost(n2, edges2, query2)));
    }

}
