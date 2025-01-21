import java.util.Arrays;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int[] dp = new int[envelopes.length];
        int length = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];

            int index = Arrays.binarySearch(dp, 0, length, height);
            if (index < 0) {
                index = -(index + 1);
            }

            dp[index] = height;
            if (index == length) {
                length++;
            }
        }

        return length;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes solution = new RussianDollEnvelopes();

        int[][] envelopes1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(solution.maxEnvelopes(envelopes1));

        int[][] envelopes2 = {{1, 1}, {1, 1}, {1, 1}};
        System.out.println(solution.maxEnvelopes(envelopes2));
    }
}
