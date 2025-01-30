package main

type Product struct {
	Description string
	Price       float64
}

func main() {

	p := Product{"Arroz", 10.5}
	println(p.Description, p.Price)

}
