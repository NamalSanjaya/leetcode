class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    private int[] traverse(TreeNode node) {
        if (node == null) return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        int[] left = traverse(node.left);
        int[] right = traverse(node.right);

        if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
            int sum = left[3] + right[3] + node.val;
            maxSum = Math.max(maxSum, sum);

            return new int[]{1, Math.min(node.val, left[1]), Math.max(node.val, right[2]), sum};
        } else {
            return new int[]{0, 0, 0, 0};
        }
    }

}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating a sample binary tree for the test case
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);

        Solution sol = new Solution();
        int result = sol.maxSumBST(root);

        System.out.println("Max sum: " + result);
    }
}
