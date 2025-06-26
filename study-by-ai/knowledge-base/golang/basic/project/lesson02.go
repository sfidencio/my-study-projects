package main

func main() {

	//inferencia de tipo
	var idade = 10
	var nome = "GoLang"
	var altura = 1.80
	var ativo = true
	println(idade, nome, altura, ativo)
	//saltar linha
	println()

	// declaracao de variaveis sem inferencia de tipo
	var peso float32 = 70.5
	var cidade string = "São Paulo"

	ativo = false
	println(peso, cidade)
	println()

	//declaração curta
	// := é uma forma de declarar variáveis sem o var
	salario := 1000.50
	println(salario)
	println()

	//ordem de declaracao
	var cliente, endereco, limiteCredito = "GoLang", "Rua 1", 1000.50
	println(cliente, endereco, limiteCredito)

	var (
		ligado  bool = true
		largura int  = 10
	)
	println(ligado, largura)
	//saltar linha
	println()

	//Multiplas declaracoes
	x, y := 10, 20
	println(x, y)
	//saltar linha
	println()

}
