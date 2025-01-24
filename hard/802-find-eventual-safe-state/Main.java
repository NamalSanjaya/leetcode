import java.util.*;

public class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] outDegree = new int[n];
        Map<Integer, List<Integer>> reverseGraph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                reverseGraph.putIfAbsent(neighbor, new ArrayList<>());
                reverseGraph.get(neighbor).add(i);
            }
            outDegree[i] = graph[i].length;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);
            if (!reverseGraph.containsKey(node)) continue;
            for (int parent : reverseGraph.get(node)) {
                outDegree[parent]--;
                if (outDegree[parent] == 0) {
                    queue.offer(parent);
                }
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] graph1 = {
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
                {}
        };
        System.out.println(solution.eventualSafeNodes(graph1));
    }
}
