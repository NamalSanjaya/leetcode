import java.util.*;

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] bobPath = new int[n];
        Arrays.fill(bobPath, -1);
        findBobPath(graph, bob, -1, 0, bobPath);

        return dfs(graph, 0, -1, 0, amount, bobPath);
    }

    private boolean findBobPath(List<Integer>[] graph, int node, int parent, int time, int[] bobPath) {
        bobPath[node] = time;
        if (node == 0) {
            return true;
        }
        for (int child : graph[node]) {
            if (child != parent && findBobPath(graph, child, node, time + 1, bobPath)) {
                return true;
            }
        }
        bobPath[node] = -1;
        return false;
    }

    private int dfs(List<Integer>[] graph, int node, int parent, int time, int[] amount, int[] bobPath) {
        int res = Integer.MIN_VALUE;
        int currAmount = 0;

        if (bobPath[node] == -1 || bobPath[node] > time) {
            currAmount = amount[node];
        } else if (bobPath[node] == time) {
            currAmount = amount[node] / 2;
        }

        boolean isLeaf = true;
        for (int child : graph[node]) {
            if (child != parent) {
                isLeaf = false;
                res = Math.max(res, dfs(graph, child, node, time + 1, amount, bobPath));
            }
        }

        if (isLeaf) {
            return currAmount;
        }

        return currAmount + res;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int bob1 = 3;
        int[] amount1 = {-2, 4, 2, -4, 6};
        System.out.println("Test 1: " + new Solution().mostProfitablePath(edges1, bob1, amount1));

        int[][] edges2 = {{0, 1}};
        int bob2 = 1;
        int[] amount2 = {-7280, 2350};
        System.out.println("Test 2: " + new Solution().mostProfitablePath(edges2, bob2, amount2));
    }
}