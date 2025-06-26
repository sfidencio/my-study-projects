# 🔗 Joins & Subqueries in PostgreSQL

## 📋 Summary

- [Introduction](#-introduction)
- [What is a JOIN?](#-what-is-a-join)
- [Types of Joins](#-types-of-joins)
  - [INNER JOIN](#inner-join)
  - [LEFT JOIN (LEFT OUTER JOIN)](#left-join-left-outer-join)
  - [RIGHT JOIN (RIGHT OUTER JOIN)](#right-join-right-outer-join)
  - [FULL JOIN (FULL OUTER JOIN)](#full-join-full-outer-join)
  - [CROSS JOIN](#cross-join)
- [Practical Examples (Inventory System)](#-practical-examples-inventory-system)
- [What is a Subquery?](#️-what-is-a-subquery)
- [Subquery Types](#-subquery-types)
  - [Scalar Subquery](#scalar-subquery)
  - [Column Subquery](#column-subquery)
  - [Row Subquery](#row-subquery)
  - [Correlated Subquery](#correlated-subquery)
- [Useful Tips](#-useful-tips)
- [References](#-references)
- [Exercises (HackerRank Style)](#exercises-hackerrank-style)

---

## 📖 Introduction

Joins and subqueries are essential for combining and filtering data from multiple tables in SQL.

---

## ❓ What is a JOIN?

A JOIN combines rows from two or more tables based on a related column.

---

## 🔀 Types of Joins

### INNER JOIN

Returns only rows with matching values in both tables.

```sql
SELECT p.name, c.name AS category
FROM products p
INNER JOIN categories c ON p.category_id = c.id;
```

---

### LEFT JOIN (LEFT OUTER JOIN)

Returns all rows from the left table and matched rows from the right table.

```sql
SELECT p.name, c.name AS category
FROM products p
LEFT JOIN categories c ON p.category_id = c.id;
```

---

### RIGHT JOIN (RIGHT OUTER JOIN)

Returns all rows from the right table and matched rows from the left table.

```sql
SELECT p.name, c.name AS category
FROM products p
RIGHT JOIN categories c ON p.category_id = c.id;
```

---

### FULL JOIN (FULL OUTER JOIN)

Returns all rows when there is a match in either table.

```sql
SELECT p.name, c.name AS category
FROM products p
FULL JOIN categories c ON p.category_id = c.id;
```

---

### CROSS JOIN

Returns the Cartesian product of both tables (all combinations).

```sql
SELECT p.name, w.location
FROM products p
CROSS JOIN warehouses w;
```

---

## 📦 Practical Examples (Inventory System)

Suppose you have these tables:

```sql
CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  category_id INT REFERENCES categories(id),
  quantity INT,
  price NUMERIC(10,2)
);

CREATE TABLE warehouses (
  id SERIAL PRIMARY KEY,
  location VARCHAR(100)
);

CREATE TABLE inventory (
  product_id INT REFERENCES products(id),
  warehouse_id INT REFERENCES warehouses(id),
  stock INT,
  PRIMARY KEY (product_id, warehouse_id)
);
```

**Sample Data:**

```sql
INSERT INTO categories (name) VALUES ('Electronics'), ('Books');
INSERT INTO products (name, category_id, quantity, price) VALUES
('Laptop Pro', 1, 15, 1200.00),
('Book: SQL Basics', 2, 30, 25.00),
('Mouse Wireless', 1, 50, 20.00);

INSERT INTO warehouses (location) VALUES ('New York'), ('Los Angeles');
INSERT INTO inventory (product_id, warehouse_id, stock) VALUES
(1, 1, 10), (1, 2, 5), (2, 1, 20), (3, 2, 50);
```

- List all products with their category name:

```sql
SELECT p.name, c.name AS category
FROM products p
INNER JOIN categories c ON p.category_id = c.id;
```

- List all products and their stock in each warehouse:

```sql
SELECT p.name, w.location, i.stock
FROM inventory i
INNER JOIN products p ON i.product_id = p.id
INNER JOIN warehouses w ON i.warehouse_id = w.id;
```

- List all products, even those not in inventory:

```sql
SELECT p.name, w.location, i.stock
FROM products p
LEFT JOIN inventory i ON p.id = i.product_id
LEFT JOIN warehouses w ON i.warehouse_id = w.id;
```

---

## 🕵️ What is a Subquery?

A subquery is a query inside another query, used to filter or calculate values dynamically.

---

## 🧩 Subquery Types

### Scalar Subquery

Returns a single value.

```sql
SELECT name, price
FROM products
WHERE price > (SELECT AVG(price) FROM products);
```

---

### Column Subquery

Returns a single column of values.

```sql
SELECT name
FROM products
WHERE category_id IN (SELECT id FROM categories WHERE name = 'Electronics');
```

---

### Row Subquery

Returns a single row (multiple columns).

```sql
SELECT *
FROM products
WHERE (category_id, price) = (SELECT category_id, MAX(price) FROM products GROUP BY category_id LIMIT 1);
```

---

### Correlated Subquery

References columns from the outer query.

```sql
SELECT name, price
FROM products p
WHERE price = (
  SELECT MAX(price)
  FROM products
  WHERE category_id = p.category_id
);
```
Finds the most expensive product in each category.

---

## 💡 Useful Tips

- Use table aliases for readability.
- Always specify join conditions to avoid Cartesian products.
- Subqueries can be used in SELECT, FROM, or WHERE clauses.

---

## 🏋️‍♂️ Exercises (HackerRank Style)

> Practice your SQL join and subquery skills with these inventory system challenges. Try to solve each problem before revealing the answer!

---

### Sample Data

```sql
CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  category_id INT REFERENCES categories(id),
  quantity INT,
  price NUMERIC(10,2)
);

CREATE TABLE warehouses (
  id SERIAL PRIMARY KEY,
  location VARCHAR(100)
);

CREATE TABLE inventory (
  product_id INT REFERENCES products(id),
  warehouse_id INT REFERENCES warehouses(id),
  stock INT,
  PRIMARY KEY (product_id, warehouse_id)
);

INSERT INTO categories (name) VALUES ('Electronics'), ('Books');
INSERT INTO products (name, category_id, quantity, price) VALUES
('Laptop Pro', 1, 15, 1200.00),
('Laptop Air', 1, 8, 950.00),
('Book: SQL Basics', 2, 30, 25.00),
('Book: PostgreSQL Guide', 2, 12, 35.00),
('Mouse Wireless', 1, 50, 20.00),
('Keyboard', 1, 40, 45.00);

INSERT INTO warehouses (location) VALUES ('New York'), ('Los Angeles');
INSERT INTO inventory (product_id, warehouse_id, stock) VALUES
(1, 1, 10), (1, 2, 5), (2, 1, 20), (3, 2, 50), (4, 1, 12), (5, 2, 25);
```

---

## 🟢 Basic

### 1. List all products and their category names

Write a query to display the name of each product and its category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name, c.name AS category
FROM products p
INNER JOIN categories c ON p.category_id = c.id;
```

**Expected Output:**

| name                   | category     |
|------------------------|-------------|
| Laptop Pro             | Electronics |
| Laptop Air             | Electronics |
| Book: SQL Basics       | Books       |
| Book: PostgreSQL Guide | Books       |
| Mouse Wireless         | Electronics |
| Keyboard               | Electronics |

</details>

---

### 2. List all products and their stock in each warehouse

Write a query to display the product name, warehouse location, and stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name, w.location, i.stock
FROM inventory i
INNER JOIN products p ON i.product_id = p.id
INNER JOIN warehouses w ON i.warehouse_id = w.id;
```

**Expected Output:**

| name                   | location     | stock |
|------------------------|-------------|-------|
| Laptop Pro             | New York    | 10    |
| Laptop Pro             | Los Angeles | 5     |
| Laptop Air             | New York    | 20    |
| Book: PostgreSQL Guide | Los Angeles | 50    |
| Book: SQL Basics       | New York    | 12    |
| Mouse Wireless         | Los Angeles | 25    |

</details>

---

### 3. List all warehouses and their total number of products

Write a query to display each warehouse location and the total number of different products stored in it.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT w.location, COUNT(DISTINCT i.product_id) AS product_count
FROM warehouses w
LEFT JOIN inventory i ON w.id = i.warehouse_id
GROUP BY w.location;
```

**Expected Output:**

| location     | product_count |
|--------------|--------------|
| New York     |      3       |
| Los Angeles  |      4       |

</details>

---

## 🟡 Medium

### 4. List all products and the total stock across all warehouses

Write a query to display each product name and the total stock across all warehouses.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name, COALESCE(SUM(i.stock), 0) AS total_stock
FROM products p
LEFT JOIN inventory i ON p.id = i.product_id
GROUP BY p.name;
```

**Expected Output:**

| name                   | total_stock |
|------------------------|-------------|
| Laptop Pro             | 15          |
| Laptop Air             | 20          |
| Book: SQL Basics       | 12          |
| Book: PostgreSQL Guide | 50          |
| Mouse Wireless         | 25          |
| Keyboard               | 0           |

</details>

---

### 5. List all warehouses that have at least one product with stock greater than 20

Write a query to display the locations of warehouses that have at least one product with stock greater than 20.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT DISTINCT w.location
FROM warehouses w
JOIN inventory i ON w.id = i.warehouse_id
WHERE i.stock > 20;
```

**Expected Output:**

| location    |
|-------------|
| Los Angeles |
| New York    |

</details>

---

### 6. List all products that are not in any warehouse

Write a query to display the names of products that do not appear in the inventory table.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name
FROM products p
LEFT JOIN inventory i ON p.id = i.product_id
WHERE i.product_id IS NULL;
```

**Expected Output:**

| name    |
|---------|
| Keyboard |

</details>

---

## 🔴 Advanced

### 7. List the name and price of the most expensive product in each category

Write a query to display the name and price of the most expensive product in each category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name, p.price, c.name AS category
FROM products p
INNER JOIN categories c ON p.category_id = c.id
WHERE (p.category_id, p.price) IN (
  SELECT category_id, MAX(price)
  FROM products
  GROUP BY category_id
);
```

**Expected Output:**

| name                   | price   | category     |
|------------------------|---------|-------------|
| Laptop Pro             | 1200.00 | Electronics |
| Book: PostgreSQL Guide | 35.00   | Books       |

</details>

---

### 8. List all warehouses that store only products from the 'Electronics' category

Write a query to display the locations of warehouses that store only products from the 'Electronics' category.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT w.location
FROM warehouses w
JOIN inventory i ON w.id = i.warehouse_id
JOIN products p ON i.product_id = p.id
GROUP BY w.location
HAVING BOOL_AND(p.category_id = (SELECT id FROM categories WHERE name = 'Electronics'));
```

**Expected Output:**

| location    |
|-------------|
| Los Angeles |

</details>

---

### 9. List all products that are stored in all warehouses

Write a query to display the names of products that are present in every warehouse.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT p.name
FROM products p
JOIN inventory i ON p.id = i.product_id
GROUP BY p.id, p.name
HAVING COUNT(DISTINCT i.warehouse_id) = (SELECT COUNT(*) FROM warehouses);
```

**Expected Output:**

| name        |
|-------------|
| Laptop Pro  |

</details>

---

### 10. List the warehouse(s) with the highest total stock

Write a query to display the location(s) of the warehouse(s) with the highest total stock.

<details>
  <summary><strong>Show Answer</strong></summary>

**Query:**
```sql
SELECT location
FROM (
  SELECT w.location, SUM(i.stock) AS total_stock
  FROM warehouses w
  JOIN inventory i ON w.id = i.warehouse_id
  GROUP BY w.location
) sub
WHERE total_stock = (SELECT MAX(total) FROM (SELECT SUM(i2.stock) AS total FROM warehouses w2 JOIN inventory i2 ON w2.id = i2.warehouse_id GROUP BY w2.location) t);
```

**Expected Output:**

| location    |
|-------------|
| Los Angeles |

</details>

---

## 📚 References

- [PostgreSQL Documentation - Joins](https://www.postgresql.org/docs/current/tutorial-join.html)
- [PostgreSQL Documentation - Subqueries](https://www.postgresql.org/docs/current/functions-subquery.html)
- [SQL Joins Visualizer](https://sql-joins.leopard.in.ua/)

[⬅️ Back ](../README.md)