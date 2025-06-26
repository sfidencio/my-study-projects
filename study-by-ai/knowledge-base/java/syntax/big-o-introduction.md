# 📊 Big O Notation Introduction

Big O notation is a way to describe the performance of algorithms, especially in terms of **time** and **space**. It is an essential tool for any developer who wants to write efficient and scalable code. 🚀

---

## 🧐 What is Big O?

Big O notation measures how the runtime or memory usage of an algorithm grows as the input size increases. It ignores constants and minor factors to focus on the overall behavior.

For example:
- **O(1)**: Constant - The runtime does not change with input size.
- **O(n)**: Linear - The runtime grows proportionally with input size.
- **O(log n)**: Logarithmic - The runtime grows slowly as input size increases.

---

## 🕒 Time Complexity

Time complexity describes how long an algorithm takes to run based on input size.

### Common Examples:
- **O(1)**: Accessing an element in an array.
- **O(n)**: Iterating through all elements of a list.
- **O(n²)**: Comparing all pairs of elements in a list.

---

## 🗄️ Space Complexity

Space complexity measures the amount of additional memory an algorithm needs to process the input.

### Common Examples:
- **O(1)**: Uses a fixed amount of memory, regardless of input size.
- **O(n)**: Uses memory proportional to input size.

---

## 📚 Common Cases

- **Best Case**: The optimal performance of the algorithm.
- **Worst Case**: The slowest possible performance.
- **Average Case**: The expected performance in most situations.

---

## 🔍 Why is Big O important?

- Helps compare different algorithms.
- Ensures your code is scalable.
- Identifies performance bottlenecks.

---

## 🛠️ Practical Examples

### Example 1: Search in an Array
```java
// Linear search - O(n)
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
// Binary search - O(log n)
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

---

## 🌟 Conclusion

Big O notation is a powerful tool for understanding and optimizing algorithms. Understanding how it works will help you write more efficient code and solve complex problems more effectively. 💡

[⬅️ Back to Roadmap](../README.md)
