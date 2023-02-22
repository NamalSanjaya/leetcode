package main

import "fmt"

func main() {
	richer := [][]int{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}}
	quiet := []int{3, 2, 5, 4, 6, 1, 7, 0}
	fmt.Println(loudAndRich(richer, quiet))
	fmt.Println("-- done --")
}

func loudAndRich(richer [][]int, quiet []int) []int {
	noOfNodes := len(quiet)
	gr := createAdjcencyList(noOfNodes, richer)
	cached := make([][]int, noOfNodes) // person -> [quiety_person, minQuietLevel]
	ans := make([]int, noOfNodes)
	for person := 0; person < noOfNodes; person++ {
		visited := make([]bool, noOfNodes)
		qty, _ := dfsForMinQuiet(gr, quiet, cached, person, visited, person, quiet[person])
		ans[person] = qty
	}
	return ans
}

func createAdjcencyList(noOfNodes int, edges [][]int) map[int][]int {
	gr := make(map[int][]int, noOfNodes)
	for _, edge := range edges {
		gr[edge[1]] = append(gr[edge[1]], edge[0])
	}
	return gr
}

func dfsForMinQuiet(gr map[int][]int, quiet []int, cached [][]int, cur int, visited []bool, quiety, minQuiet int) (int, int) {
	if len(cached[cur]) > 1 {
		cachedQuiet := cached[cur][1]
		if cachedQuiet < minQuiet {
			quiety = cached[cur][0]
			minQuiet = cachedQuiet
			return quiety, minQuiet
		}
	}
	visited[cur] = true
	neigbs := gr[cur]
	for _, neigb := range neigbs {
		if visited[neigb] {
			continue
		}
		if quiet[neigb] < minQuiet {
			quiety = neigb
			minQuiet = quiet[neigb]
		}
		quiety, minQuiet = dfsForMinQuiet(gr, quiet, cached, neigb, visited, quiety, minQuiet)
	}
	return quiety, minQuiet
}
