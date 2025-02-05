public class OneSwapToMakeEqual {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;

        int first = -1, second = -1;
        int count = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2) return false;

                if (first == -1) first = i;
                else second = i;
            }
        }

        return count == 2 &&
                s1.charAt(first) == s2.charAt(second) &&
                s1.charAt(second) == s2.charAt(first);
    }

    public static void main(String[] args) {
        OneSwapToMakeEqual solution = new OneSwapToMakeEqual();
        System.out.println(solution.areAlmostEqual("bank", "kanb"));
        System.out.println(solution.areAlmostEqual("attack", "defend"));
        System.out.println(solution.areAlmostEqual("kelb", "kelb"));
    }
}
