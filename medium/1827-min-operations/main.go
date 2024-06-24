package main

import "fmt"

func main() {

	arr := []int{1, 5, 2, 4, 1}

	fmt.Println(minOperations(arr))

}

func minOperations(nums []int) int {

	preVal := nums[0]
	incCount := 0
	for i := 1; i < len(nums); i++ {

		if nums[i] <= preVal {
			incCount += preVal - nums[i] + 1
			preVal++
			continue
		}
		preVal = nums[i]
	}

	return incCount
}
