package main

import (
	"fmt"
)

type node struct {
	name   string
	weight float64
}

// {{"a","b"},{"b","c"}}
// {2.0,3.0}
// {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}}

func main() {
	// equations := [][]string{{"a", "b"}, {"b", "c"}}
	// values := []float64{2.0, 3.0}
	// queries := [][]string{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}}

	equations := [][]string{{"a", "b"}, {"b", "c"}}
	values := []float64{2.0, 3.0}
	queries := [][]string{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}}
	fmt.Println(calcEquation(equations, values, queries))
}

func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	// build graph
	graph := map[string]*[]*node{}
	visited := map[string]bool{}
	count := 0
	for i, elem := range equations {
		tuple, ok := graph[elem[0]]
		if ok {
			*graph[elem[0]] = append(*tuple, &node{elem[1], values[i]})
		} else {
			graph[elem[0]] = &[]*node{{name: elem[1], weight: values[i]}}
			visited[elem[0]] = false
			count++
		}
		tuple, ok = graph[elem[1]]
		if ok {
			*graph[elem[1]] = append(*tuple, &node{elem[0], 1.0 / values[i]})
		} else {
			graph[elem[1]] = &[]*node{{name: elem[0], weight: 1.0 / values[i]}}
			visited[elem[1]] = false
			count++
		}
	}
	var result []float64
	for _, qry := range queries {
		src := qry[0]
		des := qry[1]
		if graph[src] == nil || graph[des] == nil {
			result = append(result, -1.0)
			continue
		}
		ans, state := dfsToDest(graph, src, des, visited, 1.0)
		if !state {
			ans = -1.0
		}
		result = append(result, ans)
		visited = make(map[string]bool, count)
	}
	return result
}

func dfsToDest(gr map[string]*[]*node, at, dest string, visited map[string]bool, ans float64) (float64, bool) {
	if at == dest {
		return ans, true
	}
	neigbs := gr[at]
	var temp float64 = 1.0
	var st bool
	for _, neig := range *neigbs {
		if visited[neig.name] {
			continue
		}
		visited[neig.name] = true
		if temp, st = dfsToDest(gr, neig.name, dest, visited, ans*neig.weight); st {
			return temp, true
		}
	}
	return ans, false
}
