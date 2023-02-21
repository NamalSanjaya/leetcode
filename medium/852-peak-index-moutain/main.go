package main

import "fmt"

func main() {
	arr := []int{3, 5, 6, 7, 8, 10, 9}
	fmt.Println(peakIndexInMountainArray(arr))
}

func peakIndexInMountainArray(arr []int) int {
	size := len(arr)
	l := 1
	u := size - 2
	for {
		mid := (l + u) / 2
		fmt.Println(l, mid, u)
		leftLogic := arr[mid] > arr[mid-1]
		if leftLogic && arr[mid] > arr[mid+1] {
			return mid
		}
		if leftLogic {
			l = mid + 1
			continue
		}
		u = mid - 1
	}
}

func bSearch(bt []int, size, l, u int) int {
	mid := (l + u) / 2
	leftLogic := bt[mid] > bt[mid-1]

	if leftLogic && bt[mid] > bt[mid+1] {
		return mid
	}
	if leftLogic {
		return bSearch(bt, size, mid+1, u)
	}
	return bSearch(bt, size, l, mid-1)
}
