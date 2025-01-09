package main

import (
	"fmt"
	"math"
)

func findShortestCycle(n int, edges [][]int) int {
	graph := make([][]int, n)
	for _, edge := range edges {
		u, v := edge[0], edge[1]
		graph[u] = append(graph[u], v)
		graph[v] = append(graph[v], u)
	}

	shortestCycle := math.MaxInt32

	for start := 0; start < n; start++ {
		dist := make([]int, n)
		for i := 0; i < n; i++ {
			dist[i] = -1
		}
		dist[start] = 0

		queue := []int{start}

		for len(queue) > 0 {
			node := queue[0]
			queue = queue[1:]

			for _, neighbor := range graph[node] {
				if dist[neighbor] == -1 {
					dist[neighbor] = dist[node] + 1
					queue = append(queue, neighbor)
				} else if dist[neighbor] >= dist[node] {
					cycleLength := dist[node] + dist[neighbor] + 1
					if cycleLength < shortestCycle {
						shortestCycle = cycleLength
					}
				}
			}
		}
	}

	if shortestCycle == math.MaxInt32 {
		return -1
	}
	return shortestCycle
}

func main() {
	// Example 1
	n := 7
	edges := [][]int{
		{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}, {6, 3},
	}
	fmt.Println("Shortest Cycle Length:", findShortestCycle(n, edges))

	// Example 2
	n = 4
	edges = [][]int{
		{0, 1}, {0, 2},
	}
	fmt.Println("Shortest Cycle Length:", findShortestCycle(n, edges))
}
