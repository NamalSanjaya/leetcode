package main

import (
	"fmt"
	"math"
	"strconv"
)

func main() {
	cookies := []int{6, 1, 3, 2, 2, 4, 1, 2}
	k := 3
	fmt.Println(distributeCookies(cookies, k))
	fmt.Println("done")
}

func distributeCookies(cookies []int, k int) int {
	ln := len(cookies)
	upto := int(math.Pow(float64(k), float64(ln)))
	digits := validDigits(k)
	min := int(8 * 1e5)
	for i := 1; i < upto-1; i++ {
		unfair := maxUnfair(cookies, i, k, digits, ln)
		if min > unfair {
			min = unfair
		}
	}
	return min
}

func validDigits(base int) []byte {
	ret := make([]byte, base)
	for i := 48; i < 48+base; i++ {
		ret[i-48] = byte(i)
	}
	return ret
}

func maxUnfair(cookies []int, num, base int, digits []byte, reqSize int) int {
	pt := strconv.FormatInt(int64(num), base)
	ln := len(pt)
	if ln < reqSize {
		zeros := "0"
		for t := 1; t < reqSize-ln; t++ {
			zeros += "0"
		}
		pt = zeros + pt
	}
	logic := make(map[byte]int, base)
	for j := 0; j < len(pt); j++ {
		char := pt[j]
		logic[char] += cookies[j]
	}
	mx := 0
	for _, d := range digits {
		val, ok := logic[d]
		if !ok {
			return int(8 * 1e5)
		}
		if val > mx {
			mx = val
		}
	}
	return mx
}
