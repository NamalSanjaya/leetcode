import java.util.HashMap;

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowCounts = new int[m];
        int[] colCounts = new int[n];

        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int[] coords = map.get(arr[i]);
            int row = coords[0];
            int col = coords[1];

            rowCounts[row]++;
            colCounts[col]++;

            if (rowCounts[row] == n || colCounts[col] == m) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr1 = {1, 3, 4, 2};
        int[][] mat1 = {{1, 4}, {2, 3}};
        System.out.println(solution.firstCompleteIndex(arr1, mat1));

        int[] arr2 = {2, 8, 7, 4, 1, 3, 5, 6, 9};
        int[][] mat2 = {{3, 2, 5}, {1, 4, 6}, {8, 7, 9}};
        System.out.println(solution.firstCompleteIndex(arr2, mat2));

    }

}