package main

import (
	"fmt"
	"unicode"
)

func main() {
	res := isPalindrome("race a car")

	fmt.Println(res)

}

func getAlphaNumericChars(str string) string {
	result := make([]rune, 0, len(str))

	for _, r := range str {
		if unicode.IsLetter(r) || unicode.IsDigit(r) {
			result = append(result, unicode.ToLower(r))
		}
	}

	return string(result)
}

func isPalindrome(s string) bool {
	cleanedStr := getAlphaNumericChars(s)

	ln := len(cleanedStr)
	midLeft := ln / 2

	for i, leftStr := range cleanedStr[0:midLeft] {
		rightStr := rune(cleanedStr[ln-i-1])
		if leftStr != rightStr {
			return false
		}
	}

	return true
}
