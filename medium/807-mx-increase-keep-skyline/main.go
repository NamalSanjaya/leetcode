package main

import "fmt"

func main() {
	grid := [][]int{{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}}
	fmt.Println(maxIncreaseKeepingSkyline(grid))
}

func maxIncreaseKeepingSkyline(grid [][]int) int {
	inc := 0
	noOfRows := len(grid)
	rowMaxs := make([]int, noOfRows)
	colMaxs := make([]int, noOfRows)

	for j := 0; j < noOfRows; j++ {
		rmx := 0
		for _, val := range grid[j] {
			if rmx < val {
				rmx = val
			}
		}
		rowMaxs[j] = rmx

		cmx := 0
		for k := 0; k < noOfRows; k++ {
			if cmx < grid[k][j] {
				cmx = grid[k][j]
			}
		}
		colMaxs[j] = cmx
	}
	for i := 0; i < noOfRows; i++ {
		for j := 0; j < noOfRows; j++ {
			cell := grid[i][j]
			temp := min(rowMaxs[i], colMaxs[j])
			if temp > cell {
				inc += temp - cell
			}
		}
	}

	return inc
}

func min(a, b int) int {
	if a >= b {
		return b
	}
	return a
}
