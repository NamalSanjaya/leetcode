class Main {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] tFreq = new int[128];
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }

        int[] windowFreq = new int[128];
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, start = 0;
        int required = t.length();

        while (right < s.length()) {
            char rChar = s.charAt(right);
            if (tFreq[rChar] > 0) {
                if (windowFreq[rChar] < tFreq[rChar]) {
                    required--;
                }
                windowFreq[rChar]++;
            }
            right++;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char lChar = s.charAt(left);
                if (tFreq[lChar] > 0) {
                    if (windowFreq[lChar] <= tFreq[lChar]) {
                        required++;
                    }
                    windowFreq[lChar]--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("a", "aa"));
    }
}
