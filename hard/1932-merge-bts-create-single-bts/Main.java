import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class MergeBSTs {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> roots = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (TreeNode tree : trees) {
            roots.put(tree.val, tree);
            count.put(tree.val, count.getOrDefault(tree.val, 0) + 1);
            if (tree.left != null) count.put(tree.left.val, count.getOrDefault(tree.left.val, 0) + 1);
            if (tree.right != null) count.put(tree.right.val, count.getOrDefault(tree.right.val, 0) + 1);
        }

        TreeNode root = null;
        for (TreeNode tree : trees) {
            if (count.get(tree.val) == 1) {
                if (root != null) return null;
                root = tree;
            }
        }

        if (root == null) return null;

        return merge(root, roots, Integer.MIN_VALUE, Integer.MAX_VALUE) && roots.size() == 1 ? root : null;
    }

    private boolean merge(TreeNode node, Map<Integer, TreeNode> roots, int min, int max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;

        if (roots.containsKey(node.val) && roots.get(node.val) != node) {
            TreeNode mergeTree = roots.get(node.val);
            node.left = mergeTree.left;
            node.right = mergeTree.right;
            roots.remove(node.val);
        }

        return merge(node.left, roots, min, node.val) && merge(node.right, roots, node.val, max);
    }

    public static void main(String[] args) {
        MergeBSTs solver = new MergeBSTs();

        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(1);

        TreeNode t2 = new TreeNode(3);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(5);

        TreeNode t3 = new TreeNode(5);
        t3.left = new TreeNode(4);

        List<TreeNode> trees = Arrays.asList(t1, t2, t3);
        TreeNode result = solver.canMerge(trees);

        System.out.println(result != null ? "Merged BST Root: " + result.val : "Cannot merge into valid BST");
    }
}
