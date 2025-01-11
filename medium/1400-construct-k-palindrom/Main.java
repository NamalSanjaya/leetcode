import java.util.*;

public class Main {
    public static boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        }

        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount <= k;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("annabelle", 2));
        System.out.println(canConstruct("leetcode", 3));
        System.out.println(canConstruct("true", 4));
    }
}
