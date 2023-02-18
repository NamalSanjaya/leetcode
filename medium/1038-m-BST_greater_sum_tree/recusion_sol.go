package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	l41 := &TreeNode{3, nil, nil}
	l42 := &TreeNode{8, nil, nil}

	l31 := &TreeNode{0, nil, nil}
	l32 := &TreeNode{2, nil, l41}
	l33 := &TreeNode{5, nil, nil}
	l34 := &TreeNode{7, nil, l42}

	l21 := &TreeNode{1, l31, l32}
	l22 := &TreeNode{6, l33, l34}

	root := &TreeNode{4, l21, l22}
	var t int = 0
	greaterSumTree(root, &t)
	fmt.Println("end total : ", t)
	fmt.Println("root: ", root.Val)

}

func greaterSumTree(node *TreeNode, total *int) {
	var st bool
	if node.Right == nil {
		*total += node.Val
		node.Val = *total
		st = false
	} else {
		greaterSumTree(node.Right, total)
		st = true
	}
	if st {
		*total += node.Val
		node.Val = *total
	}
	if node.Left == nil {
		return
	}
	greaterSumTree(node.Left, total)
}
