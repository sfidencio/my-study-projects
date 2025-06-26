# 📘 Introduction to BigDecimal

BigDecimal is a class in Java used for high-precision calculations with decimal numbers.

It is widely used in financial and scientific applications where precision is essential.

## ✨ Key Features
- Represents decimal numbers with arbitrary precision.
- Avoids rounding issues common with types like `double` and `float`.
- Supports advanced mathematical operations.

## 🛠️ When to Use?
- Financial calculations (e.g., interest rates, monetary values).
- Operations requiring high precision and control over rounding.

## 🔄 Conversion: Internal API & BigDecimal vs float/double

### 🔁 How to Convert Between Types in Java

- **From String/float/double to BigDecimal:**
  ```java
  // From String (recommended for precision)
  BigDecimal bd1 = new BigDecimal("123.45");
  // From double (⚠️ not recommended, may introduce precision errors)
  BigDecimal bd2 = BigDecimal.valueOf(123.45);
  // From float (⚠️ not recommended)
  BigDecimal bd3 = new BigDecimal(123.45f);
  ```

- **From BigDecimal to double/float/String:**
  ```java
  double d = bd1.doubleValue();
  float f = bd1.floatValue();
  String s = bd1.toString();
  ```

### 🏦 Why Use BigDecimal Instead of float/double?

| Feature         | float/double ⚡ | BigDecimal 💎 |
|-----------------|----------------|--------------|
| Precision       | Limited, binary floating point (can lose accuracy) | Arbitrary, decimal (exact for financial) |
| Rounding        | Imprecise, can introduce errors | Controlled, explicit rounding modes |
| Usage           | Fast, good for scientific/engineering | Essential for money, banking, invoices |
| Example Error   | `0.1 + 0.2 != 0.3` | `BigDecimal("0.1").add(BigDecimal("0.2")).equals(new BigDecimal("0.3"))` is true |

#### ⚠️ Example: Precision Problem with double
```java
System.out.println(0.1 + 0.2); // Output: 0.30000000000000004 😱
```
#### ✅ Example: Precision with BigDecimal
```java
BigDecimal a = new BigDecimal("0.1");
BigDecimal b = new BigDecimal("0.2");
System.out.println(a.add(b)); // Output: 0.3 🎯
```

> 💡 **Tip:** Always use `BigDecimal(String)` or `BigDecimal.valueOf(double)` for conversions. Avoid `new BigDecimal(double)` due to floating-point errors!

---

## 📚 Basic Example
```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("123.45");
        System.out.println("Value: " + value);
    }
}
```

### Output
```
Value: 123.45
```

## 🔢 Additional Examples

### Example 1: Adding Two BigDecimal Values
```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("100.25");
        BigDecimal value2 = new BigDecimal("50.75");
        BigDecimal sum = value1.add(value2);
        System.out.println("Sum: " + sum);
    }
}
```

#### Output
```
Sum: 151.00
```

### Example 2: Subtracting BigDecimal Values
```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("200.50");
        BigDecimal value2 = new BigDecimal("75.25");
        BigDecimal difference = value1.subtract(value2);
        System.out.println("Difference: " + difference);
    }
}
```

#### Output
```
Difference: 125.25
```

### Example 3: Multiplying BigDecimal Values
```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("10.5");
        BigDecimal value2 = new BigDecimal("4.2");
        BigDecimal product = value1.multiply(value2);
        System.out.println("Product: " + product);
    }
}
```

#### Output
```
Product: 44.10
```

### Example 4: Dividing BigDecimal Values
```java
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("100.00");
        BigDecimal value2 = new BigDecimal("3.00");
        BigDecimal result = value1.divide(value2, 2, RoundingMode.HALF_UP);
        System.out.println("Result: " + result);
    }
}
```

#### Output
```
Result: 33.33
```

[⬅️ Back](../README.md)
