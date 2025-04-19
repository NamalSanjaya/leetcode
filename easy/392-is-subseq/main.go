package main

import "fmt"

func main() {

	s := "abc"
	t := "mahbdgdc"

	fmt.Println(isSubsequence(s, t))

}

func isSubsequence(s string, t string) bool {

	sIndex := 0
	sLn := len(s)

	var sChar byte
	var tChar byte
	for i := 0; i < len(t) && sIndex < sLn; i++ {
		tChar = t[i]
		sChar = s[sIndex]

		if sChar == byte(tChar) {
			sIndex++
		}
	}

	return sIndex == sLn
}
