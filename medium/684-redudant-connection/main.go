package main

import "fmt"

func main() {
	edges := [][]int{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}
	// edges := [][]int{{1, 2}, {1, 3}, {2, 3}}
	fmt.Println(findRedundantConnection(edges))
}

func findRedundantConnection(edges [][]int) []int {
	ufArray := make([]int, len(edges))
	for indx := range ufArray {
		ufArray[indx] = indx + 1
	}
	lastEdge := []int{}
	for _, edge := range edges {
		if !unify(ufArray, edge[0], edge[1]) {
			lastEdge = edge
		}
	}
	return lastEdge
}

func find(ufArray []int, preNodes []int, x int) int {
	if ufArray[x-1] == x {
		pathCompression(ufArray, preNodes, x)
		return x
	}
	preNodes = append(preNodes, x)
	return find(ufArray, preNodes, ufArray[x-1])
}

func unify(ufArray []int, x1, x2 int) bool {
	p1 := find(ufArray, []int{}, x1)
	p2 := find(ufArray, []int{}, x2)
	if p1 == p2 {
		return false
	}
	ufArray[p2-1] = p1
	return true
}

func pathCompression(ufArray []int, nodes []int, parent int) {
	for _, node := range nodes {
		ufArray[node-1] = parent
	}
}
