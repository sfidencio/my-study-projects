# 🕒 Time Complexity

Time complexity measures how long an algorithm takes to run based on input size. It is an essential metric for evaluating algorithm efficiency. 🚀

---

## Common Examples of Time Complexity

- **O(1)**: Constant - The runtime does not depend on input size.
- **O(n)**: Linear - The runtime grows proportionally with input size.
- **O(n²)**: Quadratic - The runtime grows exponentially with input size.

---

## Practical Examples

### Example 1: Linear Search
```java
// O(n)
public int findElement(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == target) {
            return i;
        }
    }
    return -1;
}
```

### Example 2: Binary Search
```java
// O(log n)
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

[⬅️ Back to Roadmap](../README.md)
