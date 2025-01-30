import java.util.*;

class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        int total = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                // Collect all nodes in the current component
                List<Integer> component = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                component.add(i);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    for (int v : adj.get(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            component.add(v);
                            q.add(v);
                        }
                    }
                }

                // Check if the component is bipartite
                int[] color = new int[n + 1];
                Arrays.fill(color, -1);
                boolean isBipartite = true;
                Queue<Integer> q2 = new LinkedList<>();
                int start = component.get(0);
                color[start] = 0;
                q2.add(start);
                while (!q2.isEmpty() && isBipartite) {
                    int u = q2.poll();
                    for (int v : adj.get(u)) {
                        if (color[v] == -1) {
                            color[v] = color[u] ^ 1;
                            q2.add(v);
                        } else if (color[v] == color[u]) {
                            isBipartite = false;
                            break;
                        }
                    }
                }
                if (!isBipartite) {
                    return -1;
                }

                // Compute maximum BFS depth for this component
                int maxDepth = 0;
                for (int u : component) {
                    int[] level = new int[n + 1];
                    Arrays.fill(level, -1);
                    Queue<Integer> q3 = new LinkedList<>();
                    q3.add(u);
                    level[u] = 0;
                    int currentMax = 0;
                    while (!q3.isEmpty()) {
                        int node = q3.poll();
                        for (int v : adj.get(node)) {
                            if (level[v] == -1) {
                                level[v] = level[node] + 1;
                                currentMax = Math.max(currentMax, level[v]);
                                q3.add(v);
                            }
                        }
                    }
                    maxDepth = Math.max(maxDepth, currentMax);
                }
                total += maxDepth + 1;
            }
        }
        return total;
    }
}