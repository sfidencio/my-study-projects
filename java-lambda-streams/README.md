# Java - Lambda Expressions and Streams

## Streams e algumas características

- Streams são uma nova abstração introduzida no Java 8 para manipular coleções de dados de forma mais eficiente.
- Streams permitem que você execute operações em coleções de dados de forma paralela e concorrente.
- Streams são uma maneira mais eficiente de manipular coleções de dados do que as coleções tradicionais.
- Recebe outras funções como parâmetros.
    - Exemplo: `filter`, `map`, `reduce`, `forEach`.
    - Isso permite que você escreva código mais conciso e legível.
    - Isso também permite que você escreva código mais genérico e reutilizável.
- Streams são lazy, o que significa que eles não são executados até que uma operação terminal seja chamada.
- Streams são fluent, o que significa que você pode encadear várias operações em um único stream.
- Streams são imutáveis, o que significa que eles não podem ser modificados após a criação.
- Sobre efeito colateral: Streams não têm efeitos colaterais, o que significa que eles não alteram o estado dos objetos
  que estão sendo manipulados.
- As funções passadas para um stream são chamadas de funções lambda ou funções puras.
- O que é função pura?
    - Uma função pura é uma função que não tem efeitos colaterais e sempre retorna o mesmo resultado para o mesmo
      conjunto de argumentos.

## Operações intermediárias - Stream API

- filter
- map
- flatMap
- distinct
- sorted
- peek
    - peek é usado para depurar o stream. Ele permite que você inspecione os elementos do stream sem alterá-los.
    - Exemplo: `peek(s -> System.out.println(s))`.
- limit
- skip
- takeWhile
    - takeWhile é usado para pegar elementos do stream enquanto a condição especificada for verdadeira.
    - Exemplo: `takeWhile(s -> s.compareTo("c") < 0)`.
    - Qual a diferença entre takeWhile e filter?
        - takeWhile é usado para pegar elementos do stream enquanto a condição especificada for verdadeira.
        - filter é usado para filtrar elementos do stream com base em uma condição especificada.
- dropWhile
    - dropWhile é usado para descartar elementos do stream enquanto a condição especificada for verdadeira.

## Qual a diferença entre operações intermediárias e terminais?

- Operações intermediárias são aquelas que retornam um novo stream. Elas são usadas para construir uma sequência de
  operações a serem realizadas no stream.
- Operações terminais são aquelas que retornam um resultado final. Elas são usadas para finalizar a sequência de
  operações no stream.
- Operações intermediárias são lazy, ou seja, elas não são executadas até que uma operação terminal seja chamada.
- Operações terminais são eager, ou seja, elas são executadas imediatamente.
- Operações intermediárias são avaliadas em ordem, da primeira para a última.
- Operações terminais são avaliadas em ordem, da última para a primeira.
- Operações intermediárias são avaliadas uma vez.
- Operações terminais são avaliadas uma vez.

### filter

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
List<String> filteredList = list.stream()
        .filter(s -> s.compareTo("b") > 0)
        .collect(Collectors.toList());
```

### map

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
List<String> mappedList = list.stream()
        .map(s -> s.toUpperCase())
        .collect(Collectors.toList());
```

### flatMap

- flatMap é usado para transformar um stream de elementos em um stream de elementos diferentes.
- flatMap é útil quando você tem um stream de streams e deseja transformá-lo em um único stream.

```java
List<List<String>> list = Arrays.asList(
        Arrays.asList("a", "b"),
        Arrays.asList("c", "d"),
        Arrays.asList("e", "f")
);
List<String> flatMappedList = list.stream()
        .flatMap(l -> l.stream())
        .collect(Collectors.toList());
```

### distinct

```java
List<String> list = Arrays.asList("a", "b", "c", "a", "b", "c");
List<String> distinctList = list.stream()
        .distinct()
        .collect(Collectors.toList());
```

### skip

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
List<String> skippedList = list.stream()
        .skip(2)
        .collect(Collectors.toList());
```

### peek

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
List<String> peekedList = list.stream()
        .peek(s -> System.out.println(s))
        .collect(Collectors.toList());
```

## Operações terminais - Stream API

- forEach
    - forEach é usado para iterar sobre os elementos do stream.
- collect
    - collect é usado para coletar os elementos do stream em uma coleção.
- toList
    - Converte o stream em uma lista.
- reduce
    - reduce é usado para combinar os elementos de um stream em um único valor.
        - Ex: soma, multiplicação, concatenação.
            - Exemplo: `reduce(0, (a, b) -> a + b)`.
            - Exemplo: `reduce(1, (a, b) -> a * b)`.
- count
    - Retorna o número de elementos no stream.
- min
    - Retorna o menor elemento do stream.
- max
    - Retorna o maior elemento do stream.
- anyMatch
    - Retorna true se algum elemento do stream satisfaz a condição especificada.
- allMatch
    - Retorna true se todos os elementos do stream satisfazem a condição especificada.
- noneMatch
    - Retorna true se nenhum elemento do stream satisfaz a condição especificada.
- findFirst
    - Retorna o primeiro elemento do stream.
- findAny
    - Retorna qualquer elemento do stream.
- toArray
    - Converte o stream em um array.
- sum
    - Retorna a soma de todos os elementos do stream.
- average
    - Retorna a média de todos os elementos do stream.
- summaryStatistics
    - Retorna um objeto que contém várias estatísticas, como a soma, a média, o mínimo, o máximo e o número de elementos
      no
      stream.
- groupingBy
    - Agrupa os elementos do stream com base em uma função de agrupamento.
- partitioningBy
    - Divide os elementos do stream em dois grupos com base em uma condição booleana.
        - Exemplo: `partitioningBy(s -> s.length() == 1)`.
        - Exemplo: `partitioningBy(s -> s.length() == 1, Collectors.toList())`.
        - Exemplo com Map:
          `partitioningBy(s -> s.length() == 1, Collectors.mapping(s -> s.toUpperCase(), Collectors.toList()))`.
        - Exemplo completo com Map:
            -
            `Map<Boolean, List<String>> partitionedMap = list.stream().collect(Collectors.partitioningBy(s -> s.length() == 1));`.
- joining
    - Concatena os elementos do stream em uma única string.
- counting
    - Retorna o número de elementos no stream.
- summingInt
- Retorna a soma dos valores inteiros no stream.
    - averagingInt
- Retorna a média dos valores inteiros no stream.


### forEach

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
list.stream()
        .forEach(s -> System.out.println(s));
```

### collect

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
List<String> collectedList = list.stream()
        .collect(Collectors.toList());
```

### reduce

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
int sum = list.stream()
        .reduce(0, (a, b) -> a + b);
```

### count

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
long count = list.stream()
        .count();
```

### min

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
int min = list.stream()
        .min(Comparator.naturalOrder())
        .get();
```

### max

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
int max = list.stream()
        .max(Comparator.naturalOrder())
        .get();
```

### anyMatch

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
boolean anyMatch = list.stream()
        .anyMatch(s -> s.equals("c"));
```

### allMatch

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
boolean allMatch = list.stream()
        .allMatch(s -> s.length() == 1);
```

### noneMatch

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
boolean noneMatch = list.stream()
        .noneMatch(s -> s.equals("f"));
```

### findFirst

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
String first = list.stream()
        .findFirst()
        .get();
```

### findAny

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
String any = list.stream()
        .findAny()
        .get();
```

### toArray

```java
List<String> list = Arrays.asList("a", "b", "c", "d", "e");
    
String[] array = list.stream()
        .toArray(String[]::new);
```

## Lambda Expressions

- Lambda expressions são uma nova característica introduzida no Java 8 que permite escrever código mais conciso e
  legível.
  - Exemplo: `(a, b) -> a + b`.
  - Exemplo: `() -> System.out.println("Hello, World!")`.
  - Exemplo: `(int a) -> a * a`.
  - Exemplo: `(String s) -> s.toUpperCase()`.
- Lambda expressions são usadas para criar funções anônimas.