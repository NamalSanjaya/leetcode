package main

import "fmt"

func main() {

	num1 := []int{7, 9, 15, 17, 0, 0, 0, 0}
	m := 4

	num2 := []int{2, 8, 11, 19}
	n := 4

	merge(num1, m, num2, n)
	fmt.Println(num1)
}

func merge(nums1 []int, m int, nums2 []int, n int) {
	for i := 0; i < n; i++ {
		nums1[m+i] = nums2[i]
		for j := m + i; j > 0; j-- {
			if nums1[j] >= nums1[j-1] {
				break
			}
			nums1[j], nums1[j-1] = nums1[j-1], nums1[j]
		}
	}
}
