// not - completed
package main

import (
	"fmt"
	"sort"
)

func main() {
	n := 8
	edges := [][]int{{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}}
	// edges := [][]int{{3, 6}, {2, 4}, {8, 6}, {7, 4}, {1, 4}, {2, 1}, {7, 2}, {0, 4}, {5, 0}, {4, 6}, {3, 2}, {5, 6}, {1, 6}}
	fmt.Println(getAncestors(n, edges))
}

func getAncestors(n int, edges [][]int) [][]int {
	ref := make([][]int, n)
	for _, edge := range edges {
		from := edge[0]
		to := edge[1]
		ref[to] = append(ref[to], from)
	}
	checked := make([]bool, n)
	for i, tuple := range ref {
		visited := make([]bool, n)
		ref[i] = buildAncestors(ref, tuple, visited, checked)
		checked[i] = true
	}
	return ref
}

func buildAncestors(ref [][]int, tuple []int, visited []bool, checked []bool) []int {
	res := []int{}
	for _, node := range tuple {
		if visited[node] {
			continue
		}
		visited[node] = true
		if checked[node] {
			res = withoutDup(res, ref[node], node, visited)
			continue
		}
		res = append(res, node)
		res = append(res, buildAncestors(ref, ref[node], visited, checked)...)
		checked[node] = true
	}
	sort.Ints(res)
	return res
}

func withoutDup(container, arr []int, self int, visited []bool) []int {
	container = append(container, self)
	for _, each := range arr {
		if visited[each] {
			continue
		}
		visited[each] = true
		container = append(container, each)
	}
	return container
}

/*
* nodeArray = arr[index] -> [direct anscestor]. index is the node number
* go over map (order of node ids) and do following stuffs
  - merge all anscestor arrarys
  - asending order
* need a ds
  - sorted, no duplicates


*/
