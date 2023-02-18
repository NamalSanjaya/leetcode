package main

import (
	"fmt"
)

func main() {
	store := &[]string{}
	genParenthses("(", 1, 0, store, 3)
	fmt.Println(*store)
}

func genParenthses(pattern string, l int, r int, store *[]string, n int) {
	if r > l || l > n {
		return
	}

	if l == n && r == n {
		*store = append(*store, pattern)
		return
	}

	if l < n {
		genParenthses(pattern+"(", l+1, r, store, n)
	}
	if r < l {
		genParenthses(pattern+")", l, r+1, store, n)
	}
}
