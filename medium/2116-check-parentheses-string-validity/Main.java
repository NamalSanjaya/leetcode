public class Main {
    public static boolean canBeValid(String s, String locked) {
        if (s.length() % 2 != 0) {
            return false;
        }

        int openBalance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (locked.charAt(i) == '0' || s.charAt(i) == '(') {
                openBalance++;
            } else {
                openBalance--;
            }

            if (openBalance < 0) {
                return false;
            }
        }

        int closeBalance = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (locked.charAt(i) == '0' || s.charAt(i) == ')') {
                closeBalance++;
            } else {
                closeBalance--;
            }

            if (closeBalance < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        System.out.println(canBeValid("))()))", "010100"));
        System.out.println(canBeValid("()()", "0000"));
        System.out.println(canBeValid(")", "0"));
    }
}
