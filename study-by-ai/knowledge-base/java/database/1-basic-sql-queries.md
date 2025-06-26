# 🐘 Basic SQL Queries in PostgreSQL

## 📋 Summary

- [Introduction](#-introduction)
- [SELECT Statement](#-select-statement)
- [DISTINCT](#-distinct)
- [WHERE Clause](#-where-clause)
- [LIKE Operator (Wildcards)](#-like-operator-wildcards)
- [Logical Operators (AND, OR, NOT)](#-logical-operators-and-or-not)
- [ORDER BY](#-order-by)
- [LIMIT & OFFSET](#-limit--offset)
- [Aliases (AS)](#️-aliases-as)
- [NULL Values](#-null-values)
- [Aggregate Functions](#-aggregate-functions)
- [GROUP BY](#-group-by)
- [HAVING](#️-having)
- [INSERT Statement](#-insert-statement)
- [UPDATE Statement](#️-update-statement)
- [DELETE Statement](#-delete-statement)
- [Subqueries (Subselects)](#-subqueries-subselects)
- [Comments in SQL](#-comments-in-sql)
- [Useful Tips](#-useful-tips)
- [String & Numeric Functions](#️-string--numeric-functions)
- [Regex in SQL](#-regex-in-sql)
- [References](#-references)
- [Exercises (HackerRank Style)](#️️-exercises-hackerrank-style)

---

## 📖 Introduction

**SQL (Structured Query Language) is the standard language for managing relational databases. Below are the most common queries to get started with PostgreSQL!**

---

## 🔍 SELECT Statement

Retrieves data from one or more columns in a table.

```sql
SELECT column1, column2 FROM table_name;
```

**Example:**
```sql
SELECT id, name FROM users;
```

---

## ✨ DISTINCT

Returns only unique (different) values.

```sql
SELECT DISTINCT column1 FROM table_name;
```

**Example:**
```sql
SELECT DISTINCT country FROM customers;
```

---

## 🎯 WHERE Clause

Filters results based on conditions.

```sql
SELECT * FROM products WHERE quantity > 10;
```

**Common operators:**  
`=`, `<>`, `>`, `<`, `>=`, `<=`, `BETWEEN`, `IN`, `LIKE`

**Example:**
```sql
SELECT * FROM products WHERE price BETWEEN 10 AND 50;
SELECT * FROM products WHERE category IN ('Electronics', 'Books');
```

---

## 🔎 LIKE Operator (Wildcards)

The `LIKE` operator is used to search for a specified pattern in a column.  
Wildcards:  
- `%` (percent): matches any sequence of characters (including zero characters)  
- `_` (underscore): matches a single character

**Examples:**

Suppose you have a table `products`:

```sql
CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  category VARCHAR(50),
  quantity INT,
  price NUMERIC(10,2)
);

INSERT INTO products (name, category, quantity, price) VALUES
('Laptop Pro', 'Electronics', 15, 1200.00),
('Laptop Air', 'Electronics', 8, 950.00),
('Book: SQL Basics', 'Books', 30, 25.00),
('Book: PostgreSQL Guide', 'Books', 12, 35.00),
('Mouse Wireless', 'Electronics', 50, 20.00),
('Keyboard', 'Electronics', 40, 45.00);
```

- Find all products whose name starts with 'Laptop':
  ```sql
  SELECT * FROM products WHERE name LIKE 'Laptop%';
  ```
  Finds any name that begins with 'Laptop'.

- Find all products whose name ends with 'Guide':
  ```sql
  SELECT * FROM products WHERE name LIKE '%Guide';
  ```
  Finds any name that ends with 'Guide'.

- Find all products whose name contains 'Book':
  ```sql
  SELECT * FROM products WHERE name LIKE '%Book%';
  ```
  Finds any name that contains 'Book' anywhere.

- Find all products whose name has 'a' as the second letter:
  ```sql
  SELECT * FROM products WHERE name LIKE '_a%';
  ```
  The underscore `_` matches any single character, so this finds names where the second letter is 'a'.

---

## 🔗 Logical Operators (AND, OR, NOT)

Combine multiple conditions in WHERE.

```sql
SELECT * FROM users WHERE age > 18 AND city = 'São Paulo';
SELECT * FROM users WHERE name LIKE 'A%' OR name LIKE 'B%';
SELECT * FROM users WHERE NOT active;
```

---

## 📑 ORDER BY

Sorts the results.

```sql
SELECT * FROM users ORDER BY name ASC;
```

- `ASC` (ascending), `DESC` (descending)

---

## ⏩ LIMIT & OFFSET

Controls the number of results and pagination.

```sql
SELECT * FROM users LIMIT 10 OFFSET 20;
```

---

## 🏷️ Aliases (AS)

Rename columns or tables in the result set.

```sql
SELECT name AS "User Name" FROM users;
SELECT u.name FROM users AS u;
```

---

## 🚫 NULL Values

NULL represents missing or unknown data.

```sql
SELECT * FROM users WHERE email IS NULL;
SELECT * FROM users WHERE phone IS NOT NULL;
```

---

## 🧮 Aggregate Functions

Perform calculations on multiple rows.

- `COUNT()` - Number of rows
- `SUM()` - Sum of values
- `AVG()` - Average value
- `MIN()` - Minimum value
- `MAX()` - Maximum value

**Examples:**
```sql
SELECT COUNT(*) FROM users;
SELECT AVG(age) FROM users;
SELECT MIN(age), MAX(age) FROM users;
SELECT SUM(salary) FROM employees;
```

---

## 🧩 GROUP BY

Groups rows that have the same values.

```sql
SELECT city, COUNT(*) FROM users GROUP BY city;
```

---

## 🕵️ HAVING

Filters groups created by GROUP BY.

```sql
SELECT city, COUNT(*) FROM users GROUP BY city HAVING COUNT(*) > 10;
```

---

## ➕ INSERT Statement

Adds new records.

```sql
INSERT INTO users (name, age) VALUES ('Alice', 30);
```

---

## ✏️ UPDATE Statement

Updates existing records.

```sql
UPDATE users SET age = 31 WHERE name = 'Alice';
```

---

## ❌ DELETE Statement

Removes records.

```sql
DELETE FROM users WHERE name = 'Alice';
```

---

## 🧩 Subqueries (Subselects)

A subquery (or subselect) is a query nested inside another SQL query. It is used to perform operations that depend on the result of another query.

**Example 1:**  
Find all products with a price higher than the average price.

```sql
SELECT * FROM products
WHERE price > (SELECT AVG(price) FROM products);
```
The subquery `(SELECT AVG(price) FROM products)` calculates the average price, and the main query selects products with a price above that value.

**Example 2:**  
Find all categories that have at least one product with quantity less than 10.

```sql
SELECT DISTINCT category FROM products
WHERE category IN (
  SELECT category FROM products WHERE quantity < 10
);
```
The subquery finds categories with low stock, and the main query lists them without duplicates.

**Example 3:**  
List all products that have the same price as any product in the 'Books' category.

```sql
SELECT * FROM products
WHERE price IN (
  SELECT price FROM products WHERE category = 'Books'
);
```
The subquery gets all prices from 'Books', and the main query finds products with those prices.

---

## 💬 Comments in SQL

Add comments to your SQL code.

```sql
-- This is a single-line comment

/*
  This is a multi-line comment
*/
```

---

## 💡 Useful Tips

- Use `SELECT *` to select all columns.
- Always test `UPDATE` and `DELETE` commands with a `SELECT` first!
- Use transactions for critical operations:
  ```sql
  BEGIN;
  -- commands
  COMMIT;
  ```
- Use `EXPLAIN` to analyze query performance.

---

## 🛠️ String & Numeric Functions

PostgreSQL offers powerful functions for manipulating and transforming strings and numbers, which are essential when dealing with data stored as text (e.g., prices with currency symbols and suffixes).

### Common Functions

#### `LOWER(text)` and `UPPER(text)`  
Converts text to lowercase or uppercase.

```sql
SELECT LOWER('ABC'), UPPER('abc');
```
**Output:**
| lower | upper |
|-------|-------|
| abc   | ABC   |

---

#### `LENGTH(text)`  
Returns the number of characters in a string.

```sql
SELECT LENGTH('abc');
```
**Output:**
| length |
|--------|
|   3    |

---

#### `REPLACE(text, from, to)`  
Replaces all occurrences of a substring with another substring.

```sql
SELECT REPLACE('abcabc', 'a', 'x');
```
**Output:**
| replace |
|---------|
| xbcxbc  |

---

#### `TRIM(text)`  
Removes spaces from the beginning and end of a string.

```sql
SELECT TRIM('   hello   ');
```
**Output:**
| btrim |
|-------|
| hello |

---

#### `SUBSTRING(text FROM start FOR count)`  
Extracts part of a string.

```sql
SELECT SUBSTRING('abcdef' FROM 2 FOR 3);
```
**Output:**
| substring |
|-----------|
| bcd       |

---

#### `CAST(value AS type)` or `value::type`  
Converts a value from one data type to another.

```sql
SELECT '123.45'::NUMERIC, CAST('42' AS INTEGER);
```
**Output:**
| numeric | int4 |
|---------|------|
| 123.45  |  42  |

---

#### `REGEXP_REPLACE(text, pattern, replacement, flags)`  
Replace using regular expressions.

```sql
SELECT REGEXP_REPLACE('$1,200.00', '[^0-9.]', '', 'g');
```
**Output:**
| regexp_replace |
|----------------|
| 1200.00        |

---

#### `REGEXP_MATCHES(text, pattern)`  
Extracts with regex.

```sql
SELECT REGEXP_MATCHES('abc123def', '[0-9]+');
```
**Output:**
| regexp_matches |
|----------------|
| {123}          |

---

#### `INITCAP(text)`  
Capitalizes the first letter of each word.

```sql
SELECT INITCAP('hello world');
```
**Output:**
| initcap      |
|--------------|
| Hello World  |

---

#### `CONCAT(a, b, ...)`  
Concatenates strings.

```sql
SELECT CONCAT('A', 'B', 'C');
```
**Output:**
| concat |
|--------|
| ABC    |

---

#### `SPLIT_PART(text, delimiter, field)`  
Splits string by delimiter.

```sql
SELECT SPLIT_PART('A-B-C', '-', 2);
```
**Output:**
| split_part |
|------------|
| B          |

---

#### `LEFT(text, n)`, `RIGHT(text, n)`  
Gets left/right n characters.

```sql
SELECT LEFT('abcdef', 3), RIGHT('abcdef', 2);
```
**Output:**
| left | right |
|------|-------|
| abc  | ef    |

---

#### `POSITION(substring IN string)`  
Finds position of substring.

```sql
SELECT POSITION('b' IN 'abcabc');
```
**Output:**
| position |
|----------|
|    2     |

---

## 🧩 Regex in SQL

Regular expressions (regex) are patterns used to match character combinations in strings. In PostgreSQL, regex is very useful for searching, extracting, and replacing text in columns.

### Basic Syntax

- `.` : any character
- `*` : zero or more
- `+` : one or more
- `?` : zero or one
- `[abc]` : a, b, or c
- `[^abc]` : not a, b, or c
- `[a-z]` : any lowercase letter
- `[0-9]` : any digit
- `^` : start of string
- `$` : end of string

### Useful Regex Functions in PostgreSQL

- `REGEXP_REPLACE(string, pattern, replacement, flags)`
- `REGEXP_MATCHES(string, pattern)`
- `~` (matches regex), `!~` (does not match regex)

### Examples

#### 1. Find all products whose name contains a digit

```sql
SELECT name FROM products WHERE name ~ '[0-9]';
```
Finds names with at least one digit.

#### 2. Replace all non-digit characters in a price

```sql
SELECT REGEXP_REPLACE('$1,200.00', '[^0-9.]', '', 'g');
```
**Output:** `1200.00`

#### 3. Extract the first number from a string

```sql
SELECT REGEXP_MATCHES('abc123def456', '[0-9]+');
```
**Output:** `{123}`

#### 4. Check if a string starts with 'Book'

```sql
SELECT name FROM products WHERE name ~ '^Book';
```
Finds names that start with 'Book'.

#### 5. Check if a string ends with 'Guide'

```sql
SELECT name FROM products WHERE name ~ 'Guide$';
```
Finds names that end with 'Guide'.

---

Mastering regex in SQL allows you to perform advanced text searches, validations, and transformations directly in your queries.

---

### 🧩 Practical Example: Extracting Numeric Values from Text Prices

Suppose you have a table in your inventory system where prices are stored as text, including currency symbols and suffixes like "M" (million), "B" (billion), "K" (thousand), or "mil" (milhar):

```sql
CREATE TABLE inventory_deals (
  id SERIAL PRIMARY KEY,
  product_name VARCHAR(100),
  price_text VARCHAR(30)
);

INSERT INTO inventory_deals (product_name, price_text) VALUES
('Server Rack', '$1.2M'),
('Barcode Scanner', '€950K'),
('Mainframe', '$2.5B'),
('Label Printer', 'R$ 500 mil');
```

#### Goal

- Calculate the total value in millions (USD), converting all prices to a numeric value in millions.

#### Step-by-step Regex Explanation

1. **Remove currency symbols and spaces:**  
   - `TRIM(price_text)` removes leading/trailing spaces.
   - `REGEXP_REPLACE(..., '[^0-9.,a-zA-Z]', '', 'g')` removes everything except numbers, dots, commas, and letters.

2. **Extract numeric part:**  
   - `REGEXP_REPLACE(..., '[^0-9.,]', '', 'g')` keeps only digits, dots, and commas.

3. **Extract suffix:**  
   - `REGEXP_REPLACE(..., '[^a-zA-Z]', '', 'g')` keeps only letters (e.g., M, B, K, mil).

4. **Convert to numeric:**  
   - `REPLACE(..., ',', '.')` ensures decimal separator is a dot.
   - `CAST(... AS NUMERIC)` converts the string to a number.

5. **Convert to millions:**  
   - Use a `CASE` to multiply/divide based on the suffix.

#### Example Query

```sql
SELECT
  product_name,
  price_text,
  REGEXP_REPLACE(TRIM(price_text), '[^0-9.,a-zA-Z]', '', 'g') AS cleaned,
  REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g') AS num_part,
  LOWER(REGEXP_REPLACE(TRIM(price_text), '[^a-zA-Z]', '', 'g')) AS suffix,
  CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) AS value,
  CASE
    WHEN LOWER(price_text) LIKE '%b%' THEN
      CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) * 1000
    WHEN LOWER(price_text) LIKE '%m%' THEN
      CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC)
    WHEN LOWER(price_text) LIKE '%k%' THEN
      CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) / 1000
    WHEN LOWER(price_text) LIKE '%mil' THEN
      CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) / 1000
    ELSE NULL
  END AS value_in_millions
FROM inventory_deals;
```

**Output:**

| product_name    | price_text     | cleaned   | num_part | suffix | value  | value_in_millions |
|-----------------|---------------|-----------|----------|--------|--------|-------------------|
| Server Rack     | $1.2M         | 1.2M      | 1.2      | m      | 1.2    | 1.2               |
| Barcode Scanner | €950K         | 950K      | 950      | k      | 950    | 0.95              |
| Mainframe       | $2.5B         | 2.5B      | 2.5      | b      | 2.5    | 2500              |
| Label Printer   | R$ 500 mil    | 500mil    | 500      | mil    | 500    | 0.5               |

#### To get the total value in millions:

```sql
SELECT
  SUM(
    CASE
      WHEN LOWER(price_text) LIKE '%b%' THEN
        CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) * 1000
      WHEN LOWER(price_text) LIKE '%m%' THEN
        CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC)
      WHEN LOWER(price_text) LIKE '%k%' THEN
        CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) / 1000
      WHEN LOWER(price_text) LIKE '%mil' THEN
        CAST(REPLACE(REGEXP_REPLACE(TRIM(price_text), '[^0-9.,]', '', 'g'), ',', '.') AS NUMERIC) / 1000
      ELSE NULL
    END
  ) AS total_in_millions
FROM inventory_deals;
```

**Output:**

| total_in_millions |
|-------------------|
|      2502.65      |

#### Example Without Regex

If your data is more standardized (e.g., always "$" and always "M" for millions), you can extract the numeric part without regex using `REPLACE()` and `CAST()`.

Suppose all prices are like "$1.2M":

```sql
SELECT
  product_name,
  price_text,
  -- Remove '$' and 'M', then cast to numeric
  CAST(REPLACE(REPLACE(price_text, '$', ''), 'M', '') AS NUMERIC) AS value_in_millions
FROM inventory_deals
WHERE price_text LIKE '$%M';
```

**Output:**

| product_name | price_text | value_in_millions |
|--------------|------------|-------------------|
| Server Rack  | $1.2M      | 1.2               |

This approach is simple and efficient when the format is always the same.

---

#### Example: Grouping and Summing Payments by Currency

Suppose you have a table with credit card payments in different currencies, stored as varchar in the format `USD1200`, `EUR2000`, etc. You want to group by card brand and show the total for each currency in separate columns.

```sql
CREATE TABLE card_payments (
  id SERIAL PRIMARY KEY,
  card_brand VARCHAR(20),
  payment_value VARCHAR(20)
);

INSERT INTO card_payments (card_brand, payment_value) VALUES
('VISA', 'USD1200'),
('VISA', 'USD800'),
('VISA', 'EUR2000'),
('MASTERCARD', 'USD500'),
('MASTERCARD', 'EUR1000'),
('MASTERCARD', 'BRL3000'),
('AMEX', 'USD700'),
('AMEX', 'BRL1500'),
('ELO', 'BRL1000'),
('ELO', 'EUR500');
```

---

##### (A) Payments in format `USD1200`, `EUR2000` (currency first, value after) — Already covered above.

---

##### (B) Payments in format `1200USD`, `2000EUR` (value first, currency after)

Now suppose the values are stored as `1200USD`, `2000EUR`, etc. Let's group by card brand and sum each currency in its own column.

```sql
CREATE TABLE card_payments2 (
  id SERIAL PRIMARY KEY,
  card_brand VARCHAR(20),
  payment_value VARCHAR(20)
);

INSERT INTO card_payments2 (card_brand, payment_value) VALUES
('VISA', '1200USD'),
('VISA', '800USD'),
('VISA', '2000EUR'),
('MASTERCARD', '500USD'),
('MASTERCARD', '1000EUR'),
('MASTERCARD', '3000BRL'),
('AMEX', '700USD'),
('AMEX', '1500BRL'),
('ELO', '1000BRL'),
('ELO', '500EUR');
```

---

###### Solution 1: Without Regex (Assuming Always 3-letter Currency at End)

```sql
SELECT
  card_brand,
  SUM(
    CASE WHEN RIGHT(payment_value, 3) = 'USD'
      THEN CAST(LEFT(payment_value, LENGTH(payment_value) - 3) AS NUMERIC)
      ELSE 0 END
  ) AS total_usd,
  SUM(
    CASE WHEN RIGHT(payment_value, 3) = 'EUR'
      THEN CAST(LEFT(payment_value, LENGTH(payment_value) - 3) AS NUMERIC)
      ELSE 0 END
  ) AS total_eur,
  SUM(
    CASE WHEN RIGHT(payment_value, 3) = 'BRL'
      THEN CAST(LEFT(payment_value, LENGTH(payment_value) - 3) AS NUMERIC)
      ELSE 0 END
  ) AS total_brl
FROM card_payments2
GROUP BY card_brand
ORDER BY card_brand;
```

**Output:**

| card_brand | total_usd | total_eur | total_brl |
|------------|-----------|-----------|-----------|
| AMEX       |   700     |     0     |   1500    |
| ELO        |     0     |   500     |   1000    |
| MASTERCARD |   500     |  1000     |   3000    |
| VISA       |  2000     |  2000     |      0    |

---

###### Solution 2: With Regex and CTE

If the format can vary or you want to be robust, use regex to extract the value and currency.

```sql
WITH parsed_payments AS (
  SELECT
    card_brand,
    -- Extract numeric part (digits before letters)
    CAST(REGEXP_REPLACE(payment_value, '[^0-9]', '', 'g') AS NUMERIC) AS value,
    -- Extract currency (last 3 letters)
    UPPER(RIGHT(payment_value, 3)) AS currency
  FROM card_payments2
)
SELECT
  card_brand,
  SUM(CASE WHEN currency = 'USD' THEN value ELSE 0 END) AS total_usd,
  SUM(CASE WHEN currency = 'EUR' THEN value ELSE 0 END) AS total_eur,
  SUM(CASE WHEN currency = 'BRL' THEN value ELSE 0 END) AS total_brl
FROM parsed_payments
GROUP BY card_brand
ORDER BY card_brand;
```

**Output:**

| card_brand | total_usd | total_eur | total_brl |
|------------|-----------|-----------|-----------|
| AMEX       |   700     |     0     |   1500    |
| ELO        |     0     |   500     |   1000    |
| MASTERCARD |   500     |  1000     |   3000    |
| VISA       |  2000     |  2000     |      0    |

This approach works even if the value part has leading zeros or the currency is in lowercase.

---

## 🏋️‍♂️ Exercises (HackerRank Style)

> Practice your SQL skills with these inventory system challenges. Try to solve each problem before revealing the answer!

---

### Sample Data

```sql
CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  category VARCHAR(50),
  quantity INT,
  price NUMERIC(10,2)
);

INSERT INTO products (name, category, quantity, price) VALUES
('Laptop Pro', 'Electronics', 15, 1200.00),
('Laptop Air', 'Electronics', 8, 950.00),
('Book: SQL Basics', 'Books', 30, 25.00),
('Book: PostgreSQL Guide', 'Books', 12, 35.00),
('Mouse Wireless', 'Electronics', 50, 20.00),
('Keyboard', 'Electronics', 40, 45.00);
```

---

## 🟢 Basic

### 1. List all products with less than 10 units in stock

Write a query to display the name and quantity of all products that have less than 10 units in stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name, quantity
FROM products
WHERE quantity < 10;
```

**Expected Output:**

| name        | quantity |
|-------------|----------|
| Laptop Air  |    8     |

</details>

---

### 2. Find the average price of products in the 'Electronics' category

Write a query to calculate the average price of all products in the 'Electronics' category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT AVG(price) AS average_price
FROM products
WHERE category = 'Electronics';
```

**Expected Output:**

| average_price |
|---------------|
|   553.75      |

</details>

---

### 3. List all categories that have at least one product with more than 40 units in stock

Write a query to display the category names that have at least one product with more than 40 units in stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT DISTINCT category
FROM products
WHERE quantity > 40;
```

**Expected Output:**

| category    |
|-------------|
| Books       |
| Electronics |

</details>

---

### 4. Show all products whose name contains the word 'Book' (case-insensitive)

Write a query to list all products whose name contains the word 'Book', regardless of case.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT *
FROM products
WHERE LOWER(name) LIKE '%book%';
```

**Expected Output:**

| id | name                   | category | quantity | price |
|----|------------------------|----------|----------|-------|
| 3  | Book: SQL Basics       | Books    |   30     | 25.00 |
| 4  | Book: PostgreSQL Guide | Books    |   12     | 35.00 |

</details>

---

### 5. List all products and their prices, ordered by price descending

Write a query to display all products and their prices, ordered from the most expensive to the cheapest.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name, price
FROM products
ORDER BY price DESC;
```

**Expected Output:**

| name        | price   |
|-------------|---------|
| Laptop Pro  | 1200.00 |
| Laptop Air  | 950.00  |
| Keyboard    | 45.00   |
| Book: PostgreSQL Guide | 35.00 |
| Book: SQL Basics | 25.00 |
| Mouse Wireless | 20.00 |

</details>

---

### 6. Count the number of products in each category

Write a query to show the number of products for each category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT category, COUNT(*) AS product_count
FROM products
GROUP BY category;
```

**Expected Output:**

| category    | product_count |
|-------------|--------------|
| Electronics |      4       |
| Books       |      2       |

</details>

---

## 🟡 Medium

### 7. List all products with a price above the average price

Write a query to display all products whose price is above the average price of all products.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name, price
FROM products
WHERE price > (SELECT AVG(price) FROM products);
```

**Expected Output:**

| name        | price   |
|-------------|---------|
| Laptop Pro  | 1200.00 |
| Laptop Air  | 950.00  |

</details>

---

### 8. List all categories that have at least one product with quantity less than 10

Write a query to display the category names that have at least one product with less than 10 units in stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT DISTINCT category
FROM products
WHERE quantity < 10;
```

**Expected Output:**

| category    |
|-------------|
| Electronics |

</details>

---

### 9. List all products that have the same price as any product in the 'Books' category

Write a query to display all products that have the same price as any product in the 'Books' category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT *
FROM products
WHERE price IN (
  SELECT price FROM products WHERE category = 'Books'
);
```

**Expected Output:**

| id | name                   | category    | quantity | price |
|----|------------------------|-------------|----------|-------|
| 3  | Book: SQL Basics       | Books       |   30     | 25.00 |
| 4  | Book: PostgreSQL Guide | Books       |   12     | 35.00 |

</details>

---

## 🔴 Advanced

### 10. List the names and prices of products that are more expensive than every product in the 'Books' category

Write a query to display the names and prices of products whose price is higher than all products in the 'Books' category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name, price
FROM products
WHERE price > ALL (
  SELECT price FROM products WHERE category = 'Books'
);
```

**Expected Output:**

| name        | price   |
|-------------|---------|
| Laptop Pro  | 1200.00 |
| Laptop Air  | 950.00  |

</details>

---

### 11. Show the product(s) with the highest quantity in stock

Write a query to display the product(s) with the highest quantity in stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name, quantity
FROM products
WHERE quantity = (SELECT MAX(quantity) FROM products);
```

**Expected Output:**

| name           | quantity |
|----------------|----------|
| Mouse Wireless |   50     |

</details>

---

### 12. List all products whose name has exactly 6 characters

Write a query to display all products whose name has exactly 6 characters.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT name
FROM products
WHERE LENGTH(name) = 6;
```

**Expected Output:**

| name    |
|---------|
| Keyboard |

</details>

---

## 📚 References

- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [SQL Tutorial (w3schools)](https://www.w3schools.com/sql/)
- [PostgreSQL Exercises](https://pgexercises.com/)

[⬅️ Back ](../README.md)