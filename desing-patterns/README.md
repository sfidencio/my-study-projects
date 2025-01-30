# Entendendo os Design Patterns

## O que são Design Patterns?

Design Patterns são soluções generalistas para problemas recorrentes durante o desenvolvimento de software. Eles não são um framework ou um código pronto, mas sim uma definição de alto nível de como um problema comum pode ser resolvido em diferentes situações.

## Por que utilizar Design Patterns?

- **São soluções comprovadas**: Design Patterns são soluções comprovadas para problemas reais. Eles foram utilizados e testados por muitos desenvolvedores ao longo do tempo.

### Singleton

- **Introdução**: Singleton é um padrão de projeto criacional que tem a intenção de garantir que uma classe tenha somente uma instância e forneça um ponto global de acesso a ela.
- Exemplo de uso: 
```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
