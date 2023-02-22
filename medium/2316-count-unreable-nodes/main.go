package main

import "fmt"

func main() {
	n := 5
	// edges := [][]int{{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}}
	edges := [][]int{{1, 0}, {3, 1}, {0, 4}, {2, 1}}
	fmt.Println(countPairs(n, edges))
}

func countPairs(n int, edges [][]int) int64 {
	ufArray := createUnionFindArray(n)
	for _, edge := range edges {
		union(ufArray, edge[0], edge[1])
		fmt.Println(ufArray, edge[0], edge[1])
	}
	fmt.Println(ufArray)
	// make sure all path are compressed
	for _, node := range ufArray {
		find(ufArray, []int{}, node)
	}
	fmt.Println(ufArray)
	count := int64(0)
	for i := 0; i < n; i++ {
		elem1 := ufArray[i]
		for j := i; j < n; j++ {
			if elem1 != ufArray[j] {
				count++
			}
		}
	}
	return count
}

// create union find array
func createUnionFindArray(n int) []int {
	uf := make([]int, n)
	for i := 0; i < n; i++ {
		uf[i] = i
	}
	return uf
}

// return the parent
func find(mp, pathStore []int, x int) int {
	parent := mp[x]
	if parent == x {
		pathCompression(mp, pathStore, parent)
		return x
	}
	pathStore = append(pathStore, x)
	return find(mp, pathStore, parent)
}

func union(mp []int, x, y int) {
	parentx, parenty := find(mp, []int{}, x), find(mp, []int{}, y)
	if parentx == parenty {
		return
	}
	mp[parenty] = parentx
	return
}

// path compression
func pathCompression(mp, targets []int, parent int) {
	for _, val := range targets {
		mp[val] = parent
	}
}
