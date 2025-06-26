package main

import 
(
	"fmt"
)

var x int = 10
var p *int = &x


func main() {
	fmt.Println("Print the value of p: ", *p);
	fmt.Println(("Print the address of p: "), &p);
}