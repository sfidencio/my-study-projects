# 🔄 Control Structures

Control structures are fundamental to the logic of any program. In Go, they are simple yet powerful, allowing you to control the flow of execution in a clear and efficient way.

---

## 📋 Summary

- [🔀 If and Else](#-if-and-else)
- [🔄 Switch](#-switch)
- [🔁 For](#-for)
- [⚠️ Tips and Best Practices](#️-tips-and-best-practices)

---

## 🔀 If and Else

`if` and `else` are used to execute code blocks conditionally.

```go
if condition {
    // Code executed if the condition is true
} else {
    // Code executed if the condition is false
}
```

### 📝 Example
```go
package main

import "fmt"

func main() {
    age := 18

    if age >= 18 {
        fmt.Println("You are an adult!")
    } else {
        fmt.Println("You are a minor!")
    }
}
```

### 📝 Example in Java
```java
public class Main {
    public static void main(String[] args) {
        int age = 18;

        if (age >= 18) {
            System.out.println("You are an adult!");
        } else {
            System.out.println("You are a minor!");
        }
    }
}
```

### 💡 Highlights
- **No mandatory parentheses:** Unlike other languages, parentheses around the condition are optional.
- **Short declaration:** You can declare variables directly in the `if`.

```go
if age := 20; age >= 18 {
    fmt.Println("Adult!")
}
```

### 💡 Highlights in Java
- **Mandatory parentheses:** Unlike Go, parentheses around the condition are required.
- **No short declaration:** You cannot declare variables directly in the `if`.

---

## 🔄 Switch

`switch` is an alternative to `if-else` when there are multiple conditions. It is more readable and efficient.

```go
switch variable {
case value1:
    // Code for case value1
case value2:
    // Code for case value2
default:
    // Default code
}
```

### 📝 Example
```go
package main

import "fmt"

func main() {
    day := "Monday"

    switch day {
    case "Monday":
        fmt.Println("Start of the week!")
    case "Friday":
        fmt.Println("Almost weekend!")
    default:
        fmt.Println("Regular day.")
    }
}
```

### 📝 Example in Java
```java
public class Main {
    public static void main(String[] args) {
        String day = "Monday";

        switch (day) {
            case "Monday":
                System.out.println("Start of the week!");
                break;
            case "Friday":
                System.out.println("Almost weekend!");
                break;
            default:
                System.out.println("Regular day.");
        }
    }
}
```

### 💡 Highlights
- **No `break` needed:** `break` is implicit in Go.
- **Multiple cases:** You can combine cases.

```go
switch day {
case "Saturday", "Sunday":
    fmt.Println("Weekend!")
}
```

### 💡 Highlights in Java
- **`break` required:** Unlike Go, `break` is needed to prevent fallthrough.
- **Multiple cases:** You can also combine cases.

```java
switch (day) {
    case "Saturday":
    case "Sunday":
        System.out.println("Weekend!");
        break;
}
```

---

## 🔁 For

`for` is the only loop in Go, but it is extremely flexible.

```go
for initialization; condition; post {
    // Code executed while the condition is true
}
```

### 📝 Example
```go
package main

import "fmt"

func main() {
    for i := 0; i < 5; i++ {
        fmt.Println(i)
    }
}
```

### 📝 Example in Java
```java
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}
```

### 💡 Highlights
- **No mandatory parentheses:** As with `if`.
- **Simplified form:** Can be used as a `while`.

```go
i := 0
for i < 5 {
    fmt.Println(i)
    i++
}
```

- **Infinite loop:** Use `for` with no conditions to create an infinite loop.

```go
for {
    fmt.Println("Infinite loop!")
}
```

### 💡 Highlights in Java
- **Mandatory parentheses:** As with `if`, parentheses are required.
- **No simplified form:** There is no native way to use `for` as `while` without explicitly using `while`.

```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
```

- **Infinite loop:** Use `while (true)` to create an infinite loop.

```java
while (true) {
    System.out.println("Infinite loop!");
}
```

---

## ⚠️ Tips and Best Practices

1. **Avoid unnecessary complexity:** Prefer simple and clear conditions.
2. **Use `switch` for multiple conditions:** It is more readable than several `if-else`.
3. **Beware of infinite loops:** Make sure you have an exit condition.
4. **Short declarations:** Use short declarations in `if` and `for` to make code more concise.

---

Explore control structures and see how they can simplify your program's logic! 🚀
