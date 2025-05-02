package main

import (
	"fmt"
)

func main() {

	st := strStr("mysadorr", "sad")

	fmt.Println(st)
}

func strStr(haystack string, needle string) int {

	lnNeedle := len(needle)

	for i := range len(haystack) - lnNeedle + 1 {
		sub := haystack[i : i+lnNeedle]
		if needle == sub {
			return i
		}
		fmt.Println(i)
	}
	return -1
}
