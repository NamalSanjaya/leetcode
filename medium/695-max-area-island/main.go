package main

import "fmt"

func main() {
	grid := [][]int{
		{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
		{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
		{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
	}
	m := 8
	n := 13
	maxArea := 0
	trvsGrid(grid, m, n, 0, 0, &maxArea)
	fmt.Println(maxArea)
}

func trvsGrid(g [][]int, rows, cols, i, j int, maxArea *int) {
	if rows <= i || cols <= j {
		return
	}
	fmt.Printf("(%d,%d) %d, maxArea: %d\n", i, j, g[i][j], *maxArea)
	fmt.Println(g)
	fmt.Println("---------------------------------------")
	if g[i][j] == 1 {
		total := 0
		trvsIsland(g, rows, cols, i, j, &total)
		if total > *maxArea {
			*maxArea = total
		}
	}
	if j == cols-1 {
		if i == rows-1 {
			return
		}
		trvsGrid(g, rows, cols, i+1, 0, maxArea)
		return
	}
	trvsGrid(g, rows, cols, i, j+1, maxArea)
}

func trvsIsland(g [][]int, rows, cols, i, j int, total *int) {
	if rows < i || cols < j {
		return
	}
	if g[i][j] == 0 {
		return
	}
	g[i][j] = 1
	*total++
	trvsIsland(g, rows, cols, i, j+1, total)
	trvsIsland(g, rows, cols, i+1, j, total)
}
