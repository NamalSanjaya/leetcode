package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	trees := make([]*TreeNode, 5)
	for i := 0; i < 5; i++ {
		trees[i] = &TreeNode{Val: 0}
	}
	for _, tree := range trees {
		fmt.Println(tree.Val)
	}
}

func allPossibleFBT(n int) []*TreeNode {
	if n%2 == 0 {
		return []*TreeNode{}
	}
	trees := make([]*TreeNode, n)
	for i := 0; i < n; i++ {
		trees[i] = &TreeNode{Val: 0}
	}
	return buildFBT(trees, n)
}

func buildFBT(tuple []*TreeNode, size int) []*TreeNode {
	if size == 1 {
		return []*TreeNode{tuple[0]}
	}
	if size == 3 {
		tuple[1].Left = tuple[0]
		tuple[1].Right = tuple[2]
		return []*TreeNode{tuple[1]}
	}
	ret := []*TreeNode{}
	for i := 1; i < size; i += 2 {
		cpyTuple := make([]*TreeNode, size)
		left := buildFBT(cpyTuple[0:i], i)
		right := buildFBT(cpyTuple[i+1:], size-i-1)
		for _, l := range left {
			for _, r := range right {
				newRoot := &TreeNode{}
				newRoot = cpyTuple[i]
				newRoot.Left = l
				newRoot.Right = r
				ret = append(ret, newRoot)
			}
		}
	}
	return ret
}
