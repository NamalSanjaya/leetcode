import java.util.*;

class Solution {
    int[] answer, count;
    List<List<Integer>> tree;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        answer = new int[n];
        count = new int[n];
        tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // Calculate initial distances and subtree sizes
        dfs(0, -1);

        // Calculate distances for all nodes
        dfs2(0, -1, n);

        return answer;
    }

    private void dfs(int node, int parent) {
        count[node] = 1;
        for (int neighbor : tree.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node);
                count[node] += count[neighbor];
                answer[0] += answer[neighbor] + count[neighbor];
            }
        }
    }

    private void dfs2(int node, int parent, int n) {
        for (int neighbor : tree.get(node)) {
            if (neighbor != parent) {
                answer[neighbor] = answer[node] - count[neighbor] + (n - count[neighbor]);
                dfs2(neighbor, node, n);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        System.out.println(Arrays.toString(solution.sumOfDistancesInTree(n, edges)));

        n = 1;
        edges = new int[][]{};
        System.out.println(Arrays.toString(solution.sumOfDistancesInTree(n, edges)));
    }

}
