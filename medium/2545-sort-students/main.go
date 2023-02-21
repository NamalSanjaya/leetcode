package main

func main() {
	score := [][]int{{10, 6, 9, 1}, {7, 5, 11, 2}, {4, 8, 3, 15}}
	k := 2
	sortTheStudents(score, k)
}

func sortTheStudents(score [][]int, k int) [][]int {
	noOfRows := len(score)
	cols := make([]int, noOfRows)
	colsMap := make(map[int]int, noOfRows)
	for i := 0; i < noOfRows; i++ {
		cols[i] = score[i][k]
		colsMap[score[i][k]] = i
	}
	cols = mergeSort(cols, 0, noOfRows-1)
	newMatx := make([][]int, noOfRows)
	for j, val := range cols {
		newMatx[j] = score[colsMap[val]]
	}
	return newMatx
}

func mergeSort(arr []int, l, h int) []int {
	if l < h {
		mid := (l + h) / 2
		left := mergeSort(arr, l, mid)
		right := mergeSort(arr, mid+1, h)
		return merge(left, right)
	}
	return []int{arr[l]}
}

func merge(arr1, arr2 []int) []int {
	result := []int{}
	i1, i2 := 0, 0
	for i1 < len(arr1) && i2 < len(arr2) {
		if arr1[i1] > arr2[i2] {
			result = append(result, arr1[i1])
			i1++
			continue
		}
		if arr1[i1] <= arr2[i2] {
			result = append(result, arr2[i2])
			i2++
			continue
		}
	}
	if i1 >= len(arr1) {
		return append(result, arr2[i2:]...)
	}
	return append(result, arr1[i1:]...)
}
