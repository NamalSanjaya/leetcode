package main

import "fmt"

func main() {
	times := [][]int{{2, 7, 63}, {4, 3, 60}, {1, 3, 53}, {5, 6, 100}, {1, 4, 40}, {4, 7, 95}, {4, 6, 97}, {3, 4, 68}, {1, 7, 75}, {2, 6, 84}, {1, 6, 27}, {5, 3, 25}, {6, 2, 2}, {3, 7, 57}, {5, 4, 2}, {7, 1, 53}, {5, 7, 35}, {4, 1, 60}, {5, 2, 95}, {3, 5, 28}, {6, 1, 61}, {2, 5, 28}}
	n, k := 7, 3

	fmt.Println(networkDelayTime(times, n, k))
	fmt.Println("done")
}

func networkDelayTime(times [][]int, n int, k int) int {
	graph := make(map[int][][]int, n)
	for _, edge := range times {
		graph[edge[0]] = append(graph[edge[0]], edge[1:3])
	}

	fmt.Println(graph)
	return dijkstrasShortestPath(graph, k, n)
}

func dijkstrasShortestPath(gr map[int][][]int, startNode, n int) int {
	distances := make(map[int]int, n)
	for i := 1; i <= n; i++ {
		distances[i] = 101
	}
	distances[startNode] = 0
	pQue := map[int]int{startNode: 0}
	for len(pQue) != 0 {
		src, upToCost := pop(pQue)
		neigbs := gr[src]
		for _, neigb := range neigbs {
			to, cost := neigb[0], neigb[1]
			if distances[to] > upToCost+cost {
				distances[to] = upToCost + cost
				add(pQue, to, distances[to])
			}
		}
	}
	minCost := 0
	fmt.Println(distances)
	for _, val := range distances {
		if val == 101 {
			return -1
		}
		if minCost < val {
			minCost = val
		}
	}
	return minCost
}

func add(queue map[int]int, at, val int) {
	preVal, ok := queue[at]
	if !ok {
		queue[at] = val
		return
	}
	if preVal > val {
		queue[at] = val
	}
}

// pop next smallest value
func pop(queue map[int]int) (int, int) {
	least := 101 // max(edges cost) <= 1000
	leastKey := 0
	for k, v := range queue {
		if least > v {
			least = v
			leastKey = k
		}
	}
	delete(queue, leastKey)
	return leastKey, least
}
