package main

import "fmt"

// 12 { {1,5},{1,7},{1,2},{1,4},{3,7},{4,7},{3,5},{0,6},{0,1},{0,4},{2,6},{0,3},{0,2} }
func main() {
	n := 12
	connections := [][]int{{1, 5}, {1, 7}, {1, 2}, {1, 4}, {3, 7}, {4, 7}, {3, 5}, {0, 6}, {0, 1}, {0, 4}, {2, 6}, {0, 3}, {0, 2}}
	fmt.Println("hey-->", makeConnected(n, connections))
}

func makeConnected(n int, connections [][]int) int {
	count := 0
	// noOfParent := n
	ufArray := createUnionFindArray(n)
	for _, conn := range connections {
		ok := union(ufArray, conn[0], conn[1])
		if !ok {
			count++
			continue
		}
	}
	isolatedNodes := compressedDiff(ufArray) - 1
	if count >= isolatedNodes {
		return isolatedNodes
	}
	return -1
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

func compressedDiff(mp []int) int {
	count := 0
	m := map[int]int{}
	for j := range mp {
		find(mp, []int{}, j)
		elem := mp[j]
		if _, ok := m[elem]; !ok {
			m[elem] = 1
			count++
		}
	}
	return count
}
