# 🗄️ Space Complexity

Space complexity measures the amount of additional memory an algorithm needs to process the input. It is important to ensure the algorithm is efficient in terms of memory usage. 💾

---

## Common Examples of Space Complexity

- **O(1)**: Constant - Uses a fixed amount of memory, regardless of input size.
- **O(n)**: Linear - Uses memory proportional to input size.

---

## Practical Examples

### Example 1: Algorithm with O(1)
```java
// O(1) - Apenas variáveis fixas são usadas
public int sum(int a, int b) {
    return a + b;
}
```

### Example 2: Algorithm with O(n)
```java
// O(n) - Cria um array adicional proporcional ao tamanho da entrada
public int[] doubleArray(int[] array) {
    int[] result = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        result[i] = array[i] * 2;
    }
    return result;
}
```

[⬅️ Back to Roadmap](../README.md)
