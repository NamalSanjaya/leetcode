import java.util.*;

public class LexicographicallySmallestArray {
    public int[] makeLexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= limit) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> indices = new ArrayList<>();
                List<Integer> values = new ArrayList<>();
                dfs(i, graph, visited, indices, values, nums);

                Collections.sort(indices);
                Collections.sort(values);

                for (int k = 0; k < indices.size(); k++) {
                    nums[indices.get(k)] = values.get(k);
                }
            }
        }

        return nums;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> indices, List<Integer> values, int[] nums) {
        visited[node] = true;
        indices.add(node);
        values.add(nums[node]);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, indices, values, nums);
            }
        }
    }

    public static void main(String[] args) {
        LexicographicallySmallestArray obj = new LexicographicallySmallestArray();

        int[] nums1 = {1, 5, 3, 9, 8};
        int limit1 = 2;
        System.out.println(Arrays.toString(obj.makeLexicographicallySmallestArray(nums1, limit1)));

        int[] nums2 = {1, 7, 6, 18, 2, 1};
        int limit2 = 3;
        System.out.println(Arrays.toString(obj.makeLexicographicallySmallestArray(nums2, limit2)));

        int[] nums3 = {1, 7, 28, 19, 10};
        int limit3 = 3;
        System.out.println(Arrays.toString(obj.makeLexicographicallySmallestArray(nums3, limit3)));
    }
}
