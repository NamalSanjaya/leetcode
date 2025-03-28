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
}


