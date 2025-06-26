# 🔍 Analyzing Algorithms

Analyzing algorithms involves evaluating the efficiency of an algorithm in terms of time and space. It is an essential skill for solving complex problems. 🛠️

---

## Steps to Analyze an Algorithm

1. **Identify the main operations**: Determine which parts of the code consume the most time or memory.
2. **Calculate time complexity**: Evaluate how the runtime grows with input size.
3. **Calculate space complexity**: Evaluate the use of additional memory.

---

## Practical Example

### Code
```java
public int sumArray(int[] array) {
    int sum = 0; // O(1)
    for (int i = 0; i < array.length; i++) { // O(n)
        sum += array[i]; // O(1)
    }
    return sum; // O(1)
}
```

### Analysis
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)

[⬅️ Back ](../README.md)
