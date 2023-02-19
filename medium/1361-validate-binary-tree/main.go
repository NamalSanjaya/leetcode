package main

import "fmt"

// n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]

func main() {
	n := 6
	leftChild := []int{1, -1, -1, 4, -1, -1}
	rightChild := []int{2, -1, -1, 5, -1, -1}
	// n := 2
	// leftChild := []int{1, -0}
	// rightChild := []int{-1, -1}
	fmt.Println(validateBinaryTreeNodes(n, leftChild, rightChild))
}

func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	visited := make([]bool, n)
	for i := 0; i < n; i++ {
		left := leftChild[i]
		if left != -1 {
			if visited[left] {
				return false
			}
			visited[left] = true
		}
		right := rightChild[i]
		if right != -1 {
			if visited[right] {
				return false
			}
			visited[right] = true
		}
	}
	falseCount := 0
	for _, val := range visited {
		if !val {
			falseCount++
		}
	}
	if falseCount != 1 {
		return false
	}
	return true
}
