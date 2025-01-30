# Guia Básico de Go (Golang)

Este guia apresenta os conceitos e funcionalidades básicas da linguagem de programação Go.

---

## Índice

1. [Instalação](#instalação)
2. [Sua Primeira Aplicação](#sua-primeira-aplicação)
3. [Estrutura de um Programa](#estrutura-de-um-programa)
4. [Tipos de Dados](#tipos-de-dados)
5. [Variáveis e Constantes](#variáveis-e-constantes)
6. [Funções](#funções)
7. [Estruturas de Controle](#estruturas-de-controle)
8. [Estruturas de Dados](#estruturas-de-dados)
9. [Pacotes](#pacotes)
10. [Conclusão](#conclusão)

---

## Instalação

1. Baixe o Go em [https://golang.org/dl](https://golang.org/dl).
2. Instale seguindo as instruções específicas do sistema operacional.
3. Verifique a instalação com:

    ```bash
    go version
    ```

---

## Sua Primeira Aplicação

Crie um arquivo chamado `main.go` e adicione o seguinte código:

```go
package main

import "fmt"

func main() {
	fmt.Println("Hello, World!")
}
```

Execute o programa com:

```bash
go run main.go
```

---

## Estrutura de um Programa

Todo programa Go possui os seguintes elementos básicos:

1. **Pacote principal (main)**: Todo programa Go começa no pacote `main`.
2. **Função principal (main)**: É o ponto de entrada do programa.
3. **Importações**: Pacotes externos que o programa utiliza.

Exemplo:

```go
package main

import "fmt"

func main() {
    fmt.Println("Go é incrível!")
}
```

---

## Tipos de Dados

Go possui tipos de dados básicos organizados em categorias: números, strings, booleanos, entre outros.

```go
// Tipos básicos
var inteiro int = 42
var decimal float64 = 3.14
var texto string = "Olá, Go!"
var verdade bool = true
```

---

## Variáveis e Constantes

### Declaração de Variáveis

As variáveis podem ser declaradas explicitamente ou inferidas.

```go
var nome string = "Maria" // Declaração explícita
idade := 30              // Declaração com inferência de tipo
```

### Constantes

Valores constantes não podem ser modificados durante a execução do programa.

```go
const PI = 3.14
```

---

## Funções

Funções encapsulam blocos de código reutilizáveis.

```go
// Declaração de função
func saudacao(nome string) string {
    return "Olá, " + nome + "!"
}

func main() {
    mensagem := saudacao("Carlos")
    fmt.Println(mensagem)
}
```

---

## Estruturas de Controle

Controlam o fluxo de execução dos programas.

### Condicionais

```go
if idade := 20; idade >= 18 {
    fmt.Println("Maior de idade")
} else {
    fmt.Println("Menor de idade")
}
```

### Laços

```go
for i := 0; i < 5; i++ {
    fmt.Println(i)
}
```

---

## Estruturas de Dados

Go oferece arrays, slices, maps e structs.

### Slices

```go
numeros := []int{1, 2, 3, 4, 5}
fmt.Println(numeros)
```

### Maps

```go
idades := map[string]int{"Ana": 25, "Bruno": 30}
fmt.Println(idades["Ana"])
```

### Structs

```go
type Pessoa struct {
    Nome string
    Idade int
}

func main() {
    pessoa := Pessoa{"João", 40}
    fmt.Println(pessoa.Nome)
}
```

---

## Pacotes

Organize o código em pacotes para facilitar a modularidade e reutilização.

### Criando um Pacote

```go
// arquivo: saudacao/saudacao.go
package saudacao

func Saudacao(nome string) string {
    return "Olá, " + nome + "!"
}
```

### Usando Pacotes

```go
package main

import (
    "fmt"
    "seuprojeto/saudacao"
)

func main() {
    mensagem := saudacao.Saudacao("Carlos")
    fmt.Println(mensagem)
}
```

---

## Conclusão

Go é uma linguagem simples, rápida e eficiente. Pratique os conceitos apresentados aqui para avançar em seu aprendizado!