import java.util.*;

public class CountCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                Set<Integer> componentNodes = new HashSet<>();
                int[] edgeCount = new int[]{0};

                dfs(node, graph, visited, componentNodes, edgeCount);

                int size = componentNodes.size();
                int requiredEdges = size * (size - 1) / 2;
                if (edgeCount[0] / 2 == requiredEdges) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited,
                     Set<Integer> componentNodes, int[] edgeCount) {
        visited[node] = true;
        componentNodes.add(node);

        for (int neighbor : graph.get(node)) {
            edgeCount[0]++;
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, componentNodes, edgeCount);
            }
        }
    }

    public static void main(String[] args) {
        CountCompleteComponents solution = new CountCompleteComponents();

        int n1 = 6;
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 2}, {3, 4}};
        System.out.println(solution.countCompleteComponents(n1, edges1));

        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}};
        System.out.println(solution.countCompleteComponents(n2, edges2));

    }

}
