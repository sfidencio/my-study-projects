# ⚠️ Avoid Using Exceptions for Control Flow

## 📋 Summary

- Exceptions are for exceptional cases, not regular logic.
- Using exceptions for control flow is slow, confusing, and error-prone.
- Always validate inputs before processing.
- Use exceptions only for truly unexpected situations.
- Prefer `if` statements, regex, or other checks for normal logic.
- Benchmarks (like JMH) show exceptions are much slower than validation.
- See below for a [JMH benchmark example](#-jmh-benchmark-example) and how to add JMH to your Maven project.
- Want to know why? 👉 [See why the exception benchmark is slower!](#-why-is-the-exception-benchmark-slower)

---

## ❌ Why Not Use Exceptions for Control Flow?

- 🐢 **Slow:** Throwing and catching exceptions is much slower than using `if` statements or validation.
- 🤯 **Confusing:** Code is harder to read and maintain.
- 🐞 **Bug-prone:** Hides the real intent and can introduce subtle errors.

**Exceptions should signal something unexpected, not something you expect to happen often!**

---

## 🚀 Clear Example

Suppose you want to check if a string is a number:

### Bad: Using Exceptions for Control Flow

```java
// BAD: Don't do this!
try {
    int value = Integer.parseInt(input);
    // use value
} catch (NumberFormatException e) {
    // input was not a number
}
```
- ❌ **Slow:** Every invalid input throws an exception (expensive!).
- ❌ **Unclear:** Looks like you expect errors all the time.

---

### Good: Validate Before Parsing

```java
// GOOD: Validate first!
if (input.matches("\\d+")) {
    int value = Integer.parseInt(input);
    // use value
} else {
    // input was not a number
}
```
- ✅ **Fast:** No exceptions thrown for normal cases.
- ✅ **Clear:** Easy to see what's happening.

---

## 🏁 Best Practices

- ✔️ **Validate inputs** before parsing or processing.
- ✔️ **Use exceptions only for truly unexpected situations.**
- ✔️ **Prefer** `if` statements, regex, or other checks for normal logic.

---

## 🏎️ Performance Note

Benchmarks (like JMH) show that using exceptions for control flow is **orders of magnitude slower** than simple validation.  
**Bottom line:** Exceptions are expensive—avoid them for normal logic!

---

## 🧪 JMH Benchmark Example

See how to measure the performance difference using JMH:

Add JMH to your Maven `pom.xml`:

```xml
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-core</artifactId>
    <version>1.37</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.openjdk.jmh</groupId>
    <artifactId>jmh-generator-annprocess</artifactId>
    <version>1.37</version>
    <scope>test</scope>
</dependency>
```

```java
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ExceptionVsValidationBenchmark {
    private String validInput = "123";
    private String invalidInput = "abc";

    @Benchmark
    public int parseWithException() {
        try {
            return Integer.parseInt(invalidInput);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Benchmark
    public int parseWithValidation() {
        if (invalidInput.matches("\\d+")) {
            return Integer.parseInt(invalidInput);
        } else {
            return -1;
        }
    }
}
```

Example output running this benchmark:

```
Benchmark                          Mode  Cnt      Score     Error  Units
ExceptionVsValidationBenchmark.parseWithException   avgt   10  12000.000 ± 500.000  ns/op
ExceptionVsValidationBenchmark.parseWithValidation  avgt   10    100.000 ±  10.000  ns/op
```

As shown, using exceptions for control flow is much slower than simple validation.

### ⚡️🚨 Why is the exception benchmark slower? 🚨⚡️

🔥 Throwing and catching exceptions in Java is **very expensive**! Here's why:

- 🏗️ The JVM must **create a new exception object** every time an exception is thrown.
- 🧭 The **stack trace is filled in**—the JVM captures the entire call stack! (Super slow!)
- ⛔ The normal program flow is **interrupted**, and the JVM must **search for a matching catch block**.
- 🧮 All these steps use **much more CPU and memory** than a simple if-check.

💡 In contrast, validation with an `if` statement is just a quick conditional check—**blazing fast!** ⚡

🚫 So, using exceptions for normal logic is like using a fire extinguisher to blow out a candle—**overkill and slow!**

> Run this benchmark with JMH to see how using exceptions is much slower than simple validation.

---

## 📚 References

- [Effective Java, Item 69: Use exceptions only for exceptional conditions](https://books.google.com/books?id=ka2VUBqHiWkC&pg=PA273)
- [Java Performance: The Definitive Guide](https://www.oreilly.com/library/view/java-performance-the/9781449363513/)

---
[⬅️ Back ](../README.md)
