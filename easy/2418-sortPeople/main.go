package main

import "fmt"

func main() {

	res := sortPeople([]string{
		"180", "220", "210", "220", "230",
	}, []int{180, 220, 210, 220, 230})

	fmt.Println(res)

	fmt.Println("--done--")
}

func sortPeople(names []string, heights []int) []string {

	for i := 1; i < len(heights); i++ {
		refVal := heights[i]
		refName := names[i]
		for j := i - 1; j >= 0; j-- {
			preVal := heights[j]
			if refVal > preVal {
				heights[j] = refVal
				heights[j+1] = preVal

				names[j+1] = names[j]
				names[j] = refName
			}
		}
	}
	return names
}
