package main

import (
	"fmt"
)

func main() {
	arr := []int{2, 5, 6, 7, 11, 13, 21, 24, 27}
	target := 25

	res := searchInsert(arr, target)

	fmt.Println(res)

	fmt.Println("--done--")
}

func searchInsert(nums []int, target int) int {
	return search(nums, target, 0, len(nums)-1)
}

func search(nums []int, target, i, j int) int {
	if i == j {
		val := nums[i]
		if val == target {
			return i
		}
		if target < val {
			if i == 0 {
				return 0
			}
			return i - 1
		}
		return i + 1
	}
	c := (i + j + 1) / 2
	mid := nums[c]
	if mid == target {
		return c
	}

	if target < mid {
		return search(nums, target, i, c-1)
	}

	return search(nums, target, c, j)
}
