// DID NOT PASS ALL TEST CASES
import java.util.*;

public class DistinctColors {
    public static int[] countDistinctColors(int limit, int[][] queries) {
        Map<Integer, Integer> ballColors = new HashMap<>();
        Set<Integer> distinctColors = new HashSet<>();
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            if (ballColors.containsKey(ball)) {
                int prevColor = ballColors.get(ball);
                distinctColors.remove(prevColor);
            }

            ballColors.put(ball, color);
            distinctColors.add(color);

            result[i] = distinctColors.size();
        }

        return result;
    }

    public static void main(String[] args) {
        int limit = 4;
        int[][] queries = { {1,4}, {2,5}, {1,3}, {3,4} };
        System.out.println(Arrays.toString(countDistinctColors(limit, queries)));
    }
}
