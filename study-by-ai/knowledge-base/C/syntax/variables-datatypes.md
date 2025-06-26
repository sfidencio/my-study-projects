# 🧑‍💻 Variables & Data Types in C/C++

## 🚀 Getting Started: Installing a Compiler

### 🐧 Linux

```bash
# GCC (GNU Compiler Collection)
sudo apt update
sudo apt install build-essential
# Check version
gcc --version
g++ --version
```

### 🪟 Windows

- **Option 1: MinGW-w64**
  1. Download [MinGW-w64](https://www.mingw-w64.org/downloads/)
  2. Install and add `bin` folder to your PATH.
  3. Test in terminal:
     ```cmd
     gcc --version
     g++ --version
     ```

- **Option 2: WSL (Windows Subsystem for Linux)**
  1. Install [WSL](https://docs.microsoft.com/en-us/windows/wsl/)
  2. Follow Linux steps above inside WSL terminal.

---

## 📝 What is a Variable?

A variable is a named storage location for data.

```c
int age = 30;
float salary = 1234.56;
char grade = 'A';
```

---

## 🧬 Primitive Data Types

| C/C++ Type | Example | Size (bytes) | Description |
|------------|---------|--------------|-------------|
| `int`      | `int x = 10;` | 4 | Integer |
| `float`    | `float y = 3.14f;` | 4 | Floating point |
| `double`   | `double z = 2.718;` | 8 | Double precision |
| `char`     | `char c = 'A';` | 1 | Character |
| `short`    | `short s = 100;` | 2 | Short integer |
| `long`     | `long l = 100000L;` | 4/8 | Long integer |
| `bool` (C++) | `bool b = true;` | 1 | Boolean |

> 💡 **Note:** C does not have a built-in `bool` type (use `<stdbool.h>`), but C++ does.

---

## 🏷️ Declaring and Initializing Variables

```c
int a;        // Declaration only
a = 5;        // Assignment

float b = 2.5; // Declaration + Initialization
```

---

## 🛡️ Constants

```c
const int DAYS_IN_WEEK = 7;
#define PI 3.14159
```

---

## 🧠 Type Modifiers

- `signed`, `unsigned`, `short`, `long`
- Example:
  ```c
  unsigned int u = 42;
  long long big = 123456789LL;
  ```

---

## 📏 Sizeof Operator

```c
printf("Size of int: %zu bytes\n", sizeof(int));
```

---

## 🧪 Exercises (HackerRank Style)

### 1. Print Values and Sizes of Primitive Types

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>
#include <stdbool.h>

int main() {
    int i = 10;
    float f = 3.14f;
    double d = 2.718;
    char c = 'Z';
    bool b = true;
    printf("int: %d (%zu bytes)\n", i, sizeof(i));
    printf("float: %f (%zu bytes)\n", f, sizeof(f));
    printf("double: %lf (%zu bytes)\n", d, sizeof(d));
    printf("char: %c (%zu bytes)\n", c, sizeof(c));
    printf("bool: %d (%zu bytes)\n", b, sizeof(b));
    return 0;
}
```
</details>

**Sample Output:**
```
int: 10 (4 bytes)
float: 3.140000 (4 bytes)
double: 2.718000 (8 bytes)
char: Z (1 bytes)
bool: 1 (1 bytes)
```

---

### 2. Assign a float to an int variable. What happens?

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    float f = 3.99f;
    int i = f; // Implicit conversion (truncates decimal part)
    printf("i = %d\n", i); // Output: i = 3
    return 0;
}
```
</details>

**Sample Output:**
```
i = 3
```

---

### 3. Declare Constants with `const` and `#define` and Print Their Values

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    const int DAYS = 7;
    #define PI 3.14159
    printf("DAYS = %d\n", DAYS);
    printf("PI = %f\n", PI);
    return 0;
}
```
</details>

**Sample Output:**
```
DAYS = 7
PI = 3.141590
```

---

### 4. Read Two Integers and Print Their Sum

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    int a, b;
    scanf("%d %d", &a, &b);
    printf("%d\n", a + b);
    return 0;
}
```
</details>

**Sample Input:**
```
3 7
```
**Sample Output:**
```
10
```

---

### 5. Assign a Negative Value to `unsigned int`. What is the Result?

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    unsigned int u = -10;
    printf("%u\n", u); // Output: large positive number (wraps around)
    return 0;
}
```
</details>

**Sample Output:**
```
4294967286
```

---

### 6. Print the Size of All Primitive Types Using `sizeof`

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>
#include <stdbool.h>

int main() {
    printf("int: %zu\n", sizeof(int));
    printf("float: %zu\n", sizeof(float));
    printf("double: %zu\n", sizeof(double));
    printf("char: %zu\n", sizeof(char));
    printf("short: %zu\n", sizeof(short));
    printf("long: %zu\n", sizeof(long));
    printf("long long: %zu\n", sizeof(long long));
    printf("bool: %zu\n", sizeof(bool));
    return 0;
}
```
</details>

**Sample Output:**
```
int: 4
float: 4
double: 8
char: 1
short: 2
long: 8
long long: 8
bool: 1
```

---

### 7. Demonstrate Type Casting from int to float and vice versa

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    int x = 7;
    float y = (float)x / 2;
    printf("y = %f\n", y); // Output: y = 3.500000

    float z = 5.75f;
    int w = (int)z;
    printf("w = %d\n", w); // Output: w = 5
    return 0;
}
```
</details>

**Sample Output:**
```
y = 3.500000
w = 5
```

---

### 8. Read a Character and Print Its ASCII Value

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    char ch;
    scanf(" %c", &ch);
    printf("ASCII: %d\n", ch);
    return 0;
}
```
</details>

**Sample Input:**
```
A
```
**Sample Output:**
```
ASCII: 65
```

---

### 9. Demonstrate Overflow with `short` Type

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>
#include <limits.h>

int main() {
    short s = SHRT_MAX;
    printf("SHRT_MAX: %d\n", s);
    s = s + 1;
    printf("After overflow: %d\n", s);
    return 0;
}
```
</details>

**Sample Output:**
```
SHRT_MAX: 32767
After overflow: -32768
```

---

### 10. Use `scanf` to Read a Float and Print It with 2 Decimal Places

<details>
  <summary><strong>Show Solution</strong></summary>

```c
#include <stdio.h>

int main() {
    float f;
    scanf("%f", &f);
    printf("%.2f\n", f);
    return 0;
}
```
</details>

**Sample Input:**
```
3.14159
```
**Sample Output:**
```
3.14
```

---

## 🧑‍🔬 Advanced: Type Casting

```c
int x = 10;
float y = (float)x / 3; // Explicit cast
```

---

## 📚 References

- [C Programming - Variables](https://www.programiz.com/c-programming/c-variables-constants)
- [C++ Data Types](https://cplusplus.com/doc/tutorial/variables/)
- [GCC Documentation](https://gcc.gnu.org/)
- [MinGW-w64](https://www.mingw-w64.org/)

---

> 🏁 **Keep practicing!**

[⬅️ Back](../README.md)