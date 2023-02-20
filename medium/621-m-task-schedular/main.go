/* This implementation is incorrect */
package main

import (
	"fmt"
	"sort"
)

func main() {
	// tasks := []byte{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}
	// tasks := []byte{'A', 'A', 'A', 'B', 'B', 'B'}
	tasks := []byte{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'}
	n := 2
	fmt.Println(leastInterval(tasks, n))
}

func leastInterval(tasks []byte, n int) int {
	tksMp := make(map[byte]int)
	arr := []int{}
	size := 0
	if n == 0 {
		n = 1
	}
	for _, task := range tasks {
		indx, ok := tksMp[task]
		if !ok {
			arr = append(arr, 1)
			tksMp[task] = size
			size++
			continue
		}
		arr[indx] += 1
	}
	sort.Ints(arr)
	mxDur, start := 0, 1
	n++
	for i := size - 1; i >= 0; i-- {
		calDur := start + n*(arr[i]-1)
		if mxDur < calDur {
			mxDur = calDur
		}
		start++
	}
	return mxDur
}
