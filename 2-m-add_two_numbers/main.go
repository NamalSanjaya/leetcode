package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	fmt.Println(l1.Next)
	return l1
}

func main() {
	// 64
	var l1_1 *ListNode = &ListNode{6, nil}
	var l1_2 *ListNode = &ListNode{4, l1_1}

	// 999 999
	var l2_1 *ListNode = &ListNode{9, nil}
	var l2_2 *ListNode = &ListNode{9, l2_1}
	var l2_3 *ListNode = &ListNode{9, l2_2}
	var l2_4 *ListNode = &ListNode{9, l2_3}
	var l2_5 *ListNode = &ListNode{9, l2_4}
	var l2_6 *ListNode = &ListNode{9, l2_5}

	var l1 *ListNode = l1_2
	var l2 *ListNode = l2_6
	var c int = 0
	var temp_sum = 0
	for {
		temp_sum = l1.Val + l2.Val + c
		l1.Val = temp_sum % 10
		c = temp_sum / 10

		if l1.Next == nil && l2.Next == nil {
			if c > 0 {
				l1.Next = &ListNode{c, nil}
			}
			break
		}

		if l1.Next == nil {
			l1.Next = &ListNode{0, nil}
		} else if l2.Next == nil {
			l2.Next = &ListNode{0, nil}
		}
		l1 = l1.Next
		l2 = l2.Next

	}

	// addTwoNumbers(l1, l2)

	var pnode *ListNode = l1_2
	for pnode != nil {
		fmt.Println(pnode.Val)
		pnode = pnode.Next
	}
	fmt.Println("done")
}
