package main

import (
	"fmt"
)

func main() {
	piles := []int{4, 3, 6, 7}
	k := 3
	fmt.Println(minStoneSum(piles, k))
}

func minStoneSum(piles []int, k int) int {
	total := 0
	size := len(piles)
	for j := 0; j < size; j++ {
		total += piles[j]
	}
	maxHeap := buildHeap(piles)
	var curMaxNode int
	for i := 0; i < k; i++ {
		curMaxNode, maxHeap = delete(maxHeap)
		remove := curMaxNode / 2
		curMaxNode -= remove
		total -= remove
		maxHeap = heapInsert(maxHeap, curMaxNode)
	}
	return total
}

func adjustHeap(hp []int, node, indx int) []int {
	parent := hp[indx/2-1]
	if parent >= node {
		return hp
	}
	hp[indx/2-1] = node
	hp[indx-1] = parent
	if indx/2 == 1 {
		return hp
	}
	return adjustHeap(hp, node, indx/2)
}

func buildHeap(arr []int) []int {
	size := len(arr)
	hp := make([]int, size)
	hp[0] = arr[0]
	for i := 1; i < size; i++ {
		hp[i] = arr[i]
		hp = adjustHeap(hp, arr[i], i+1)
	}
	return hp
}

func heapInsert(hp []int, node int) []int {
	hp = append(hp, node)
	if len(hp) <= 1 {
		return hp
	}
	return adjustHeap(hp, node, len(hp))
}

func adjustDeletion(hp []int, size int) {
	for i := 0; i < size/2; {
		node := hp[i]
		leftIndx := 2*i + 2
		rightIndx := 2*i + 3
		if leftIndx >= size {
			return
		}
		left := hp[leftIndx-1]
		if rightIndx > size {
			if left > node {
				hp[leftIndx-1] = node
				hp[i] = left
			}
			return
		}
		right := hp[rightIndx-1]
		if left > right {
			if node >= left {
				return
			}
			hp[leftIndx-1] = node
			hp[i] = left
			i = leftIndx - 1
			continue
		}
		if node >= right {
			return
		}
		hp[rightIndx-1] = node
		hp[i] = right
		i = rightIndx - 1
	}
}

func delete(hp []int) (int, []int) {
	size := len(hp)
	firstElement := hp[0]
	hp[0] = hp[size-1]
	hp = hp[:size-1]
	adjustDeletion(hp, size-1)
	return firstElement, hp
}
