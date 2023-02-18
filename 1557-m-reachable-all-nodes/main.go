package main

import "fmt"

func main() {
	n := 6
	edges := [][]int{{0, 1}, {0, 2}, {2, 5}, {3, 4}, {4, 2}}

	fmt.Println(findSmallestSetOfVertices(n, edges))
}

func findSmallestSetOfVertices(n int, edges [][]int) []int {
	var notr []bool
	var unr []int
	for i := 0; i < n; i++ {
		notr = append(notr, true)
	}
	for _, edge := range edges {
		notr[edge[1]] = false
	}
	for indx, st := range notr {
		if st {
			unr = append(unr, indx)
		}
	}
	return unr
}
