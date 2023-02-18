package main

import (
	"fmt"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	l3 := &TreeNode{Val: 8, Left: nil, Right: nil}
	r3 := &TreeNode{Val: 6, Left: nil, Right: nil}

	l21 := &TreeNode{Val: 3, Left: nil, Right: nil}
	l22 := &TreeNode{Val: 7, Left: l3, Right: nil}

	r21 := &TreeNode{Val: 4, Left: nil, Right: nil}
	r22 := &TreeNode{Val: 5, Left: nil, Right: r3}

	l1 := &TreeNode{Val: 1, Left: l21, Right: l22}
	r1 := &TreeNode{Val: 2, Left: r21, Right: r22}

	root := &TreeNode{Val: 0, Left: l1, Right: r1}
	fmt.Println(deepestLeavesSum(root))
}

func deepestLeavesSum(root *TreeNode) int {
	_, sum := dfsDeepSum(root, 0, 0, 0)
	return sum
}

// maxDepth, sum
func dfsDeepSum(at *TreeNode, sum int, depth int, mxDepth int) (int, int) {
	var notLeaf bool
	if at.Left != nil {
		mxDepth, sum = dfsDeepSum(at.Left, sum, depth+1, mxDepth)
		notLeaf = true
	}
	if at.Right != nil {
		mxDepth, sum = dfsDeepSum(at.Right, sum, depth+1, mxDepth)
		notLeaf = true
	}
	if notLeaf {
		return mxDepth, sum
	}
	if depth > mxDepth {
		return depth, at.Val
	}
	if depth == mxDepth {
		return depth, sum + at.Val
	}
	return mxDepth, sum
}
