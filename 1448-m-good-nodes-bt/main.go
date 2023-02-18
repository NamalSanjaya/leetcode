package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type Node struct {
	Trnode *TreeNode
	MaxVal int
}

func goodNodes(root *TreeNode) int {

	return 0
}

func treeTrvs(node *Node) {
	if node.Trnode.Left != nil {
		// go to left node
		if node.MaxVal > node.Trnode.Val {
			node.Trnode.Val = node.MaxVal
			// treeTrvs(node.Trnode.Left)
		}
	}
	if node.Trnode.Right != nil {
		// go to rigth node
	}
}
