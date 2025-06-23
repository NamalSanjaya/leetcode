import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxArea = 0;
        int cols = matrix[0].length;
        int[] heights = new int[cols];

        for (char[] row : matrix) {
            for (int i = 0; i < cols; i++) {
                if (row[i] == '1') {
                    heights[i]++;
                } else {
                    heights[i] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int[] extendedHeights = new int[heights.length + 1];
        System.arraycopy(heights, 0, extendedHeights, 0, heights.length);

        for (int i = 0; i < extendedHeights.length; i++) {
            while (!stack.isEmpty() && extendedHeights[i] < extendedHeights[stack.peek()]) {
                int height = extendedHeights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }

        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle solver = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal Rectangle Area: " + solver.maximalRectangle(matrix));
    }
}
