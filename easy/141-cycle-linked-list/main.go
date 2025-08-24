package main

import (
	"fmt"
)

func main() {

	// [-21, 10, 5, 9, -21, ]

	fmt.Println("--done--")
}

type ListNode struct {
	Val  int
	Next *ListNode
}

func hasCycle(head *ListNode) bool {

	if head == nil {
		return false
	}

	visited := make(map[*ListNode]bool)
	curNode := head
	for curNode.Next != nil {

		if visited[curNode] {
			return true
		}
		visited[curNode] = true
		curNode = curNode.Next
	}

	return false
}
