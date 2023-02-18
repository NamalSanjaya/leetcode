package main

import "fmt"

func main() {
	// edges := [][]int{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}
	edges := [][]int{{1, 2}, {1, 3}, {2, 3}}
	fmt.Println(findRedundantConnection(edges))
}

func findRedundantConnection(edges [][]int) []int {
	ufArray := make([]int, len(edges))
	for indx := range ufArray {
		ufArray[indx] = indx + 1
	}
	var find func(x int) int
	find = func(x int) int {
		if ufArray[x-1] == x {
			return x
		}
		return find(ufArray[x-1])
	}
	var unify func(x1, x2 int) bool
	unify = func(x1, x2 int) bool {
		p1 := find(x1)
		p2 := find(x2)
		if p1 == p2 {
			return false
		}
		ufArray[p2-1] = p1
		return true
	}
	lastEdge := []int{}
	for _, edge := range edges {
		if !unify(edge[0], edge[1]) {
			lastEdge = edge
		}
	}
	return lastEdge
}
