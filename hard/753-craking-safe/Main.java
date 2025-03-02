import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe {
    public String crackSafe(int n, int k) {
        StringBuilder result = new StringBuilder();
        Set<String> visited = new HashSet<>();
        String start = "0".repeat(n - 1);
        dfs(start, k, visited, result);
        result.append(start);
        return result.toString();
    }

    private void dfs(String node, int k, Set<String> visited, StringBuilder result) {
        for (int i = 0; i < k; i++) {
            String newPassword = node + i;
            if (!visited.contains(newPassword)) {
                visited.add(newPassword);
                dfs(newPassword.substring(1), k, visited, result);
                result.append(i);
            }
        }
    }

    public static void main(String[] args) {
        CrackingTheSafe solver = new CrackingTheSafe();
        System.out.println(solver.crackSafe(1, 2));
        System.out.println(solver.crackSafe(2, 2));
    }
}
