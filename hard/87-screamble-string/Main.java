import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        String key = s1 + "," + s2;
        if (memo.containsKey(key)) return memo.get(key);

        int n = s1.length();

        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                memo.put(key, false);
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        ScrambleString solver = new ScrambleString();

        System.out.println(solver.isScramble("great", "rgeat"));
        System.out.println(solver.isScramble("abcde", "caebd"));
        System.out.println(solver.isScramble("a", "a"));
    }
}
