package main

import (
	"fmt"
	"sort"
)

func main() {

	arr := []int{1, 2, 3, 1}

	fmt.Println(containsDuplicate(arr))

}

func containsDuplicate(nums []int) bool {
	visited := make(map[int]bool)

	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})

	for _, val := range nums {
		if visited[val] {
			return true
		}
		visited[val] = true
	}
	return false
}
