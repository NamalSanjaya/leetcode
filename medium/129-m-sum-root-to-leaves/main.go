package main

import (
	"math"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func main() {
	r21 := &TreeNode{9, nil, nil}

	l21 := &TreeNode{5, nil, nil}
	l22 := &TreeNode{1, nil, nil}
	l23 := &TreeNode{8, nil, r21}
	l24 := &TreeNode{3, nil, nil}

	l11 := &TreeNode{9, l21, l22}
	l12 := &TreeNode{0, l23, l24}
	root := &TreeNode{4, l11, l12}
	sumNumbers(root)
}

func sumNumbers(root *TreeNode) int {
	return valueFirstSearch(root, []int{}, 0)
}

func valueFirstSearch(cur *TreeNode, digits []int, total int) int {
	left := cur.Left
	right := cur.Right
	if left == nil && right == nil {
		total += digitsToDecimal(append(digits, cur.Val))
		return total
	}
	if left != nil {
		total = valueFirstSearch(left, append(digits, cur.Val), total)
	}
	if right != nil {
		total = valueFirstSearch(right, append(digits, cur.Val), total)
	}
	return total
}

func digitsToDecimal(arr []int) int {
	size := len(arr)
	val := 0
	k := 0
	for i := size - 1; i >= 0; i-- {
		val += arr[i] * int(math.Pow10(k))
		k++
	}
	return val
}
