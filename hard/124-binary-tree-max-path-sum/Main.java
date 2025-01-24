class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateMaxPathSum(root);
        return maxSum;
    }

    private int calculateMaxPathSum(TreeNode node) {
        if (node == null) return 0;

        int leftMax = Math.max(0, calculateMaxPathSum(node.left));
        int rightMax = Math.max(0, calculateMaxPathSum(node.right));

        maxSum = Math.max(maxSum, leftMax + rightMax + node.val);

        return Math.max(leftMax, rightMax) + node.val;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        Solution solution = new Solution();
        System.out.println(solution.maxPathSum(root1));

        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        System.out.println(solution.maxPathSum(root2));
    }
}
