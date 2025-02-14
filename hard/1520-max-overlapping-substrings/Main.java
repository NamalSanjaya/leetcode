import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26], last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (first[c - 'a'] == -1) first[c - 'a'] = i;
            last[c - 'a'] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            int l = first[i], r = last[i];

            for (int j = l; j <= r; j++) {
                l = Math.min(l, first[s.charAt(j) - 'a']);
                r = Math.max(r, last[s.charAt(j) - 'a']);
            }

            boolean valid = true;
            for (int j = l; j <= r; j++) {
                if (first[s.charAt(j) - 'a'] < l || last[s.charAt(j) - 'a'] > r) {
                    valid = false;
                    break;
                }
            }

            if (valid) intervals.add(new int[]{l, r});
        }

        Collections.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        List<String> result = new ArrayList<>();
        int end = -1;

        for (int[] interval : intervals) {
            if (interval[0] > end) {
                result.add(s.substring(interval[0], interval[1] + 1));
                end = interval[1];
            }
        }

        return result;
    }
}
