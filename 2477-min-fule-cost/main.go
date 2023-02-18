package main

import "fmt"

type nodeProps struct {
	visited    bool
	subTrNodes int
}

func main() {
	edges := [][]int{{0, 1}, {0, 2}, {0, 3}}
	// edges := [][]int{{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}}
	// edges := [][]int{}
	seats := 2
	fmt.Println(minimumFuelCost(edges, seats))
}

func dfs(gr map[int][]int, at, parent int, noOfSeats int, totalCost *int64) int {
	count := 1
	for _, node := range gr[at] {
		if node == parent {
			continue
		}
		count += dfs(gr, node, at, noOfSeats, totalCost)
	}
	if at == 0 {
		return count
	}
	*totalCost += int64((count + noOfSeats - 1) / noOfSeats)
	return count
}

func minimumFuelCost(roads [][]int, seats int) int64 {
	if len(roads) == 0 {
		return 0
	}
	graph := make(map[int][]int)
	for _, edge := range roads {
		a, b := edge[0], edge[1]
		graph[a] = append(graph[a], b)
		graph[b] = append(graph[b], a)
	}
	var totalCost int64 = 0
	dfs(graph, 0, -1, seats, &totalCost)
	return totalCost
}
