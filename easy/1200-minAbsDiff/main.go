package main

import (
	"fmt"
	"sort"
)

func main() {

	arr := []int{3, 8, -10, 23, 19, -4, -14, 27}
	res := minimumAbsDifference(arr)

	fmt.Println(res)
}

func minimumAbsDifference(arr []int) [][]int {

	sort.Ints(arr)

	minVal := 2000000
	res := [][]int{}
	for i := 0; i < len(arr)-1; i++ {
		curMin := arr[i+1] - arr[i]
		if curMin == minVal {
			res = append(res, []int{arr[i], arr[i+1]})
		} else if curMin < minVal {
			res = [][]int{{arr[i], arr[i+1]}}
			minVal = curMin
		}
	}

	return res

}
