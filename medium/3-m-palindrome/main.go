package main

import (
	"fmt"
)

func main() {
	var word string = "nabcbaxx"
	fmt.Println(findPalindrome(word, 0, len(word)-1))
}

func findPalindrome(word string, i int, j int) (int, int) {
	var size int = j - i + 1
	if size <= 1 {
		return 0, 0
	}
	if size == 2 {
		if word[i] == word[j] {
			return i, j
		}
		return 0, 0
	}
	if size == 3 {
		if word[i] == word[j] {
			return i, j
		}
	}
	li, lj := findPalindrome(word, i, j-1)
	ri, rj := findPalindrome(word, i+1, j)
	// fmt.Println("li, lj = ", li, lj, "| ", i, j)
	// fmt.Println("ri, rj = ", ri, rj)

	if (lj - li) >= (rj - ri) {
		if lj == j-1 {
			if word[li] == word[lj+1] {
				return li, lj + 1
			}
			if i > li && word[li-1] == word[lj+1] {
				return li - 1, lj + 1
			}
		}
		return li, lj
	} else {
		if ri == i+1 {
			if word[i] == word[rj] {
				return i, rj
			}
			if j > rj && word[i] == word[rj+1] {
				return i, rj + 1
			}
		}
		return ri, rj
	}
}
