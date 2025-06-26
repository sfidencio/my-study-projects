# 📘 BigDecimal Operations

BigDecimal provides a wide range of mathematical operations for precise calculations.

## 🔢 Basic Operations

### Addition
```java
BigDecimal a = new BigDecimal("10.5");
BigDecimal b = new BigDecimal("5.3");
BigDecimal result = a.add(b);
System.out.println("Sum: " + result);
```
#### Output
```
Sum: 15.8
```

### Subtraction
```java
BigDecimal result = a.subtract(b);
System.out.println("Subtraction: " + result);
```
#### Output
```
Subtraction: 5.2
```

### Multiplication
```java
BigDecimal result = a.multiply(b);
System.out.println("Multiplication: " + result);
```
#### Output
```
Multiplication: 55.65
```

### Division
```java
BigDecimal result = a.divide(b, RoundingMode.HALF_UP);
System.out.println("Division: " + result);
```
#### Output
```
Division: 1.98
```

## 🧮 Advanced Operations
- `pow(int n)`: Raises the number to a power.
- `remainder(BigDecimal divisor)`: Calculates the remainder of the division.

[⬅️ Back](../README.md)
