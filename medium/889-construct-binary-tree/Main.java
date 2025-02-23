import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructMainBinaryTree {
    private HashMap<Integer, Integer> postIndexMap;
    private int preIndex;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        postIndexMap = new HashMap<>();
        preIndex = 0;

        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }

        return constructTree(preorder, postorder, 0, postorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int[] postorder, int left, int right) {
        if (left > right) return null;

        TreeNode root = new TreeNode(preorder[preIndex++]);

        if (left == right) return root;

        int postLeftIndex = postIndexMap.get(preorder[preIndex]);

        root.left = constructTree(preorder, postorder, left, postLeftIndex);
        root.right = constructTree(preorder, postorder, postLeftIndex + 1, right - 1);

        return root;
    }

    public void printTree(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        ConstructBinaryTree solution = new ConstructBinaryTree();
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root = solution.constructFromPrePost(preorder, postorder);
        solution.printTree(root);
    }
}
