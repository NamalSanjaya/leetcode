package main

import (
	"fmt"
	"sort"
)

func main() {
	// points := [][]int{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}
	points := [][]int{{3, 12}, {-2, 5}, {-4, 1}}
	fmt.Println(minCostConnectPoints(points))
	fmt.Println("done")
}

func minCostConnectPoints(points [][]int) int {
	// calculate distance of all nodes. sorted order.(lower to upper)
	size := len(points)
	minQueue := [][]int{}
	for i := 0; i < size; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < size; j++ {
			x2, y2 := points[j][0], points[j][1]
			minQueue = collectEdges(minQueue, i, j, diffAbs(x1, x2)+diffAbs(y1, y2))
		}
	}
	sort.Slice(minQueue, func(i, j int) bool { return minQueue[i][2] < minQueue[j][2] })
	ufArray := createUnionFindArray(size)
	edges := 0
	minCost := 0
	for _, tuple := range minQueue {
		if union(ufArray, tuple[0], tuple[1]) {
			edges++
			minCost += tuple[2]
			if edges == size-1 {
				return minCost
			}
		}
	}
	return minCost
}

func collectEdges(edgesList [][]int, n1, n2, cost int) [][]int {
	return append(edgesList, []int{n1, n2, cost})
}

func diffAbs(a, b int) int {
	if a > b {
		return a - b
	}
	return b - a
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

func union(mp []int, x, y int) bool {
	parentx, parenty := find(mp, []int{}, x), find(mp, []int{}, y)
	if parentx == parenty {
		return false
	}
	mp[parenty] = parentx
	return true
}

// path compression
func pathCompression(mp, targets []int, parent int) {
	for _, val := range targets {
		mp[val] = parent
	}
}
