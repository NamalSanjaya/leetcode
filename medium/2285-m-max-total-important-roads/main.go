package main

import (
	"fmt"
	"sort"
)

func main() {
	n := 5
	roads := [][]int{{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}}
	fmt.Println(maximumImportance(n, roads))
}

func maximumImportance(n int, roads [][]int) int64 {
	var total int64 = 0
	cities := make([]int, n)
	for _, edge := range roads {
		cities[edge[0]] += 1
		cities[edge[1]] += 1
	}
	fmt.Println(cities)
	sort.Ints(cities)
	for i := n - 1; i >= 0; i-- {
		total += int64(cities[i] * (i + 1))
	}
	return total
}
