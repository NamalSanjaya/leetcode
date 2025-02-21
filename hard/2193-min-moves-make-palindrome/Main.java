public class MinMovesToMakePalindrome {
    public int minMovesToMakePalindrome(String s) {
        char[] arr = s.toCharArray();
        int moves = 0, left = 0, right = arr.length - 1;

        while (left < right) {
            int l = left, r = right;

            while (l < r && arr[l] != arr[r]) {
                r--;
            }

            if (l == r) {
                swap(arr, r, r + 1);
                moves++;
                continue;
            } else {
                for (int i = r; i < right; i++) {
                    swap(arr, i, i + 1);
                    moves++;
                }
            }
            left++;
            right--;
        }
        return moves;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Example Usage
    public static void main(String[] args) {
        MinMovesToMakePalindrome solution = new MinMovesToMakePalindrome();
        System.out.println(solution.minMovesToMakePalindrome("aabb"));
        System.out.println(solution.minMovesToMakePalindrome("letelt"));
    }
}
