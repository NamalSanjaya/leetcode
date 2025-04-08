package main

import (
	"fmt"
	"strconv"
)

func main() {
	x := -121
	fmt.Println(isPalindrome(x))
}

func isPalindrome(x int) bool {
	str := strconv.Itoa(x)
	ln := len(str)

	for i, j := 0, ln-1; i < ln/2; i, j = i+1, j-1 {
		if str[i] != str[j] {
			return false
		}
	}
	return true
}
