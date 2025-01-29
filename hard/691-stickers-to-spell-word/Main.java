import java.util.*;

public class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < (1 << n); i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            for (String sticker : stickers) {
                int mask = i;
                for (char c : sticker.toCharArray()) {
                    for (int j = 0; j < n; j++) {
                        if (((mask >> j) & 1) == 0 && target.charAt(j) == c) {
                            mask |= (1 << j);
                            break;
                        }
                    }
                }
                if (dp[mask] > dp[i] + 1) {
                    dp[mask] = dp[i] + 1;
                }
            }
        }

        return dp[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : dp[(1 << n) - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] stickers1 = {"with", "example", "science"};
        String target1 = "thehat";
        System.out.println(solution.minStickers(stickers1, target1));

        String[] stickers2 = {"notice", "possible"};
        String target2 = "basicbasic";
        System.out.println(solution.minStickers(stickers2, target2));
    }
}
