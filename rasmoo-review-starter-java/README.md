# Criando uma Aplicação Java na Linha de Comando e Empacotando em um .jar

Este guia descreve como criar uma aplicação Java simples que pode ser executada a partir da linha de comando e como empacotá-la em um arquivo `.jar`.

## 1. Criar a Aplicação Java

Vamos criar um exemplo simples. Suponha que você queira criar uma aplicação que imprime "Hello, World!". Crie um arquivo chamado `Main.java`.

### Código do `Main.java`

```java  
public class Main {  
    public static void main(String[] args) {  
        System.out.println("Hello, World!");  
    }  
}
```

## 2. Compilar o código
```bash
javac Main.java
```

## 3. Criar arquivo manifesto na mesma pasta

```text
Manifest-Version: 1.0  
Main-Class: Main
```

## 4. Empacotar
```bash
jar cfm HelloWorld.jar MANIFEST.MF Main.class
```


## 5. Executar
```java
java -jar HelloWorld.jar
```


