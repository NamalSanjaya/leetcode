// not correct

package main

import "fmt"

func main() {
	richer := [][]int{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}}
	quiet := []int{3, 2, 5, 4, 6, 1, 7, 0}
	loudAndRich(richer, quiet)
	fmt.Println("done")
}

func loudAndRich(richer [][]int, quiet []int) {
	n := len(quiet)
	gr := createAdjcencyList(n, richer)
	richOrder, indexMap := topsort(gr, n)
	ret := make([]int, n)
	fmt.Println(richOrder, indexMap)
	for person := 0; person < 1; person++ {
		richIndex := indexMap[person]
		quiety := person
		minQuietLevel := quiet[person]
		for i := 0; i < richIndex; i++ {
			rqt := quiet[richOrder[i]]
			fmt.Println(person, richIndex, quiety, minQuietLevel, rqt, richOrder[i])
			if rqt < minQuietLevel {
				quiety = richOrder[i]
				minQuietLevel = rqt
			}
		}
		ret[person] = quiety
	}
	fmt.Println(ret)
}

func createAdjcencyList(noOfNodes int, edges [][]int) map[int][]int {
	gr := make(map[int][]int, noOfNodes)
	for _, edge := range edges {
		gr[edge[0]] = append(gr[edge[0]], edge[1])
	}
	return gr
}

func topsort(gr map[int][]int, noOfNodes int) ([]int, map[int]int) {
	indexMap := make(map[int]int, noOfNodes) // number -> index
	ordering := make([]int, noOfNodes)
	visited := make([]bool, noOfNodes)
	i := noOfNodes - 1
	for node := range gr {
		if visited[node] {
			continue
		}
		visited[node] = true
		i = dfsTopsort(gr, node, visited, ordering, indexMap, i)
	}
	return ordering, indexMap
}

func dfsTopsort(gr map[int][]int, cur int, visited []bool, ordering []int, result map[int]int, resultAt int) int {
	neigbs := gr[cur]
	for _, neigb := range neigbs {
		if visited[neigb] {
			continue
		}
		visited[neigb] = true
		resultAt = dfsTopsort(gr, neigb, visited, ordering, result, resultAt)
	}
	result[cur] = resultAt
	ordering[resultAt] = cur
	return resultAt - 1
}
