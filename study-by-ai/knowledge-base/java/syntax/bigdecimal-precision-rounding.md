# 📘 BigDecimal Precision & Rounding

BigDecimal allows you to control the precision and rounding of calculations, ensuring reliable results.

## 🎯 Precision Control
Precision defines the total number of significant digits in a number.

```java
BigDecimal value = new BigDecimal("123.456789");
BigDecimal rounded = value.setScale(2, RoundingMode.HALF_UP);
System.out.println("Rounded: " + rounded);
```
#### Output
```
Rounded: 123.46
```

## 🔄 Rounding Modes
- `RoundingMode.HALF_UP`: Rounds to the nearest neighbor, ties round up.
- `RoundingMode.HALF_DOWN`: Rounds to the nearest neighbor, ties round down.
- `RoundingMode.CEILING`: Rounds towards positive infinity.
- `RoundingMode.FLOOR`: Rounds towards negative infinity.

## 🛠️ Practical Example
```java
BigDecimal value = new BigDecimal("10.12345");
BigDecimal rounded = value.setScale(3, RoundingMode.CEILING);
System.out.println("Value with precision: " + rounded);
```
#### Output
```
Value with precision: 10.124
```

[⬅️ Back](../README.md)
