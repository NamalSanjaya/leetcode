package main

import "fmt"

func main() {
	sequence := "abababac"
	word := "bac"

	fmt.Println(maxRepeating(sequence, word))

}

func maxRepeating(sequence string, word string) int {
	step := len(word)

	i := 0
	count := 0
	k := 0
	for i < len(sequence)-step+1 {
		if word == sequence[i:i+step] {
			i += step
			count++
			continue
		} else if count > 0 {
			k = max(k, count)
		}
		i++
		count = 0
	}
	return k

}

func max(a, b int) int {
	if a <= b {
		return b
	}
	return a
}
