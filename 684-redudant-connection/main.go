package main

import "fmt"

func main() {
	edges := [][]int{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}
	tree := make(map[int][][]int)

	for indx, tuple := range edges {
		a, b := tuple[0], tuple[1]
		tree[a] = append(tree[a], []int{b, indx})
		tree[b] = append(tree[b], []int{a, indx})
	}
	fmt.Println(tree)

}

// func findRedundantConnection(edges [][]int) []int {

// }

func dfs(tr map[int][][]int, at int, visited []bool) {
	for _, node := range tr[at] {
		if visited[node[0]] {

		}
	}
}
