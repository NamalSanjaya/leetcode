// DID NOT PASS ALL TEST CASES.

import java.util.Stack;

public class MinimumLengthString {
    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public static void main(String[] args) {
        MinimumLengthString solver = new MinimumLengthString();

        System.out.println(solver.minLength("abaacbcb"));
        System.out.println(solver.minLength("aa"));
        System.out.println(solver.minLength("abc"));
        System.out.println(solver.minLength("aabba"));
    }
}
