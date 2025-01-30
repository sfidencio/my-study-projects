
# Guia Completo sobre Generics em Java

## Introdução

Generics em Java são um recurso que permite que classes, interfaces e métodos operem sobre tipos especificados pelo usuário. Introduzidos na versão 5, os generics proporcionam maior flexibilidade e segurança de tipo, permitindo que você escreva código mais reutilizável e menos propenso a erros.

## 1. O que são Generics?

Generics permitem que você defina classes, interfaces e métodos com um ou mais parâmetros de tipo. Isso significa que você pode criar estruturas de dados que podem armazenar qualquer tipo de objeto, sem perder a segurança de tipo.

### Exemplo de Classe Genérica

```java
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

## 2. Vantagens dos Generics

- **Reutilização de Código**: Você pode escrever um único método ou classe que funcione com diferentes tipos de dados.
- **Segurança de Tipo**: Erros de tipo são detectados em tempo de compilação, em vez de em tempo de execução.
- **Melhor Legibilidade**: O uso de generics pode tornar o código mais claro e fácil de entender.

## 3. Sintaxe Básica

A sintaxe para definir generics em Java envolve o uso de um ou mais parâmetros de tipo, que são especificados entre sinais de menor e maior (`<T>`).

### Exemplo de Método Genérico

```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

## 4. Restrições de Tipo

Você pode restringir os tipos que podem ser usados como parâmetros de tipo usando a palavra-chave `extends`. Isso é útil quando você deseja garantir que o tipo fornecido seja uma subclasse de uma classe específica ou implemente uma interface.

### Exemplo de Restrições de Tipo

```java
public class NumberBox<T extends Number> {
    private T number;

    public void setNumber(T number) {
        this.number = number;
    }

    public T getNumber() {
        return number;
    }
}
```

## 5. Tipos Coringa (Wildcard Types)

Os tipos coringa são representados por `?` e permitem que você trabalhe com tipos desconhecidos. Eles são úteis em situações onde você não precisa saber o tipo exato.

### Exemplo de Tipo Coringa

```java
public void printList(List<?> list) {
    for (Object element : list) {
        System.out.println(element);
    }
}
```

## 6. Limitações dos Generics

- **Tipos Primitivos**: Você não pode usar tipos primitivos como `int`, `char`, etc. diretamente como parâmetros de tipo. Em vez disso, você deve usar suas classes wrapper, como `