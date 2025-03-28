class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        if (dfs(root) == 0) {
            cameras++;
        }
        return cameras;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == 0 || right == 0) {
            cameras++;
            return 1;
        }

        if (left == 1 || right == 1) {
            return 2;
        }

        return 0;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(0);
        root1.left = new TreeNode(0);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(0);

        Solution solution = new Solution();
        System.out.println("Minimum cameras: " + solution.minCameraCover(root1));

        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(0);
        root2.left.left = new TreeNode(0);
        root2.left.left.left = new TreeNode(0);
        root2.left.left.left.left = new TreeNode(0);

        solution = new Solution();
        System.out.println("Minimum cameras: " + solution.minCameraCover(root2));
    }
}


