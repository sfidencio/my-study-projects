# 📈 Best, Worst, and Average Cases

Different analysis cases help to understand how an algorithm behaves in various scenarios. 🔍

---

## Definitions

- **Best Case**: The optimal performance of the algorithm.
- **Worst Case**: The slowest possible performance.
- **Average Case**: The expected performance in most situations.

---

## Practical Example

### Linear Search
```java
public int findElement(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == target) {
            return i;
        }
    }
    return -1;
}
```

- **Best Case**: O(1) - The element is at the beginning of the array.
- **Worst Case**: O(n) - The element is at the end or not present.
- **Average Case**: O(n/2) - On average, the element is in the middle.

[⬅️ Back ](../README.md)
