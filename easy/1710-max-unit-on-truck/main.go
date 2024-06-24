package main

import (
	"fmt"
	"sort"
)

func main() {

	arr := [][]int{
		{1, 3},
		{5, 5},
		{2, 5},
		{4, 2},
		{4, 1},
		{3, 1},
		{2, 2},
		{1, 3},
		{2, 5},
		{3, 2},
	}

	fmt.Println(maximumUnits(arr, 35))

}

func maximumUnits(boxTypes [][]int, truckSize int) int {

	sort.Slice(boxTypes, func(i, j int) bool {
		return boxTypes[i][1] > boxTypes[j][1]
	})

	remaining := truckSize
	totalUnits := 0
	for i := 0; remaining > 0 && i < len(boxTypes); i++ {
		if remaining >= boxTypes[i][0] {
			totalUnits += boxTypes[i][0] * boxTypes[i][1]
			remaining -= boxTypes[i][0]
			continue
		}
		totalUnits += remaining * boxTypes[i][1]
		remaining = 0
	}

	return totalUnits
}
