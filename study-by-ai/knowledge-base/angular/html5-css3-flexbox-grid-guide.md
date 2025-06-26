# 🟨 HTML5 & CSS3 Complete Guide

Welcome to your all-in-one guide for modern web layouts using **HTML5** and **CSS3**!  
This tutorial covers Flexbox, Grid, Responsiveness, and Pre-processors (Sass/Less) with examples, resources, and tips. 🚀

---

## 📋 Summary

- [Introduction](#introduction)
- [HTML5 Basics](#html5-basics)
- [CSS3 Fundamentals](#css3-fundamentals)
- [Flexbox Layout](#flexbox-layout)
- [CSS Grid Layout](#css-grid-layout)
- [Responsive Design](#responsive-design)
- [CSS Pre-processors (Sass/Less)](#css-pre-processors-sassless)
- [Useful Resources](#useful-resources)
- [Sample Project](#sample-project)

---

## 🏁 Introduction

HTML5 and CSS3 are the backbone of modern web development.  
**Flexbox** and **Grid** make layouts easier, while **Sass/Less** help you write cleaner, reusable CSS.

---

## 🟢 Basic Concepts

### HTML5 Essentials

- Structure: `<!DOCTYPE html>`, `<html>`, `<head>`, `<body>`
- Semantic tags: `<header>`, `<nav>`, `<main>`, `<section>`, `<footer>`, etc.
- Simple page layout

**Example:**
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Basic HTML5 Page</title>
  </head>
  <body>
    <header><h1>Welcome</h1></header>
    <main>
      <section>
        <p>This is a basic HTML5 structure.</p>
      </section>
    </main>
    <footer>&copy; 2024</footer>
  </body>
</html>
```

### CSS3 Essentials

- Selectors, properties, values
- Colors, backgrounds, borders

**Example:**
```css
body {
  background: #f4f4f4;
  color: #222;
}
```

#### 📝 Harkrank Exercises (Basic)

1. **[Easy]** Create an HTML5 page with a `<header>`, `<main>`, and `<footer>`.
2. **[Easy]** Add a CSS rule to change the background color of the `<body>`.
3. **[Medium]** Use a semantic tag to wrap your navigation links.

---

## 🟡 Medium Concepts

### Flexbox Layout

- 1D layouts (row or column)
- Key properties: `display: flex;`, `justify-content`, `align-items`, `gap`

**Example:**
```css
.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
}
.flex-container > div {
  flex: 1;
  background: #fff;
  padding: 1rem;
}
```

### CSS Grid Layout

- 2D layouts (rows and columns)
- Key properties: `display: grid;`, `grid-template-columns`, `gap`

**Example:**
```css
.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
}
.grid-container > div {
  background: #fff;
  padding: 1rem;
}
```

#### 📝 Harkrank Exercises (Medium)

1. **[Medium]** Create a Flexbox container with three equally spaced boxes.
2. **[Medium]** Build a 2x2 CSS Grid layout for a photo gallery.
3. **[Hard]** Make a navigation bar using Flexbox that is responsive.

---

## 🔴 Advanced Concepts

### Responsive Design

- Media queries
- Relative units (`em`, `rem`, `%`)
- Mobile-first approach

**Example:**
```css
@media (max-width: 600px) {
  .flex-container {
    flex-direction: column;
  }
}
```

### CSS Pre-processors (Sass/Less)

- Variables, nesting, mixins, functions

**Sass Example:**
```scss
$primary: #3498db;

.button {
  background: $primary;
  color: #fff;
  padding: 1rem;
  border-radius: 4px;
  &:hover {
    background: darken($primary, 10%);
  }
}
```

#### 📝 Harkrank Exercises (Advanced)

1. **[Medium]** Write a media query to change the layout of a grid on mobile.
2. **[Hard]** Use Sass variables and nesting to style a button with hover effects.
3. **[Expert]** Combine Flexbox and Grid in a single layout for a dashboard page.

---

## 📝 HTML5 Basics

- Semantic tags: `<header>`, `<nav>`, `<main>`, `<section>`, `<footer>`, etc.
- Accessibility and SEO improvements.

**Example:**
```html
<header>
  <h1>My Website</h1>
</header>
<nav>
  <ul>
    <li>Home</li>
    <li>About</li>
  </ul>
</nav>
<main>
  <section>
    <h2>Welcome!</h2>
    <p>This is a semantic HTML5 layout.</p>
  </section>
</main>
<footer>
  &copy; 2024
</footer>
```

---

## 🎨 CSS3 Fundamentals

- Selectors, properties, values
- Colors, backgrounds, borders, shadows
- Transitions, animations

**Example:**
```css
body {
  background: #f4f4f4;
  color: #222;
  font-family: Arial, sans-serif;
}
```

---

## 📦 Flexbox Layout

Flexbox is great for 1D layouts (rows or columns).

**Key properties:**
- `display: flex;`
- `justify-content`, `align-items`, `flex-direction`, `gap`

**Example:**
```css
.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
}
.flex-container > div {
  flex: 1;
  background: #fff;
  padding: 1rem;
}
```

**Learn more:**  
🔗 [CSS Tricks Flexbox Guide](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)

---

## 🗃️ CSS Grid Layout

Grid is perfect for 2D layouts (rows and columns).

**Key properties:**
- `display: grid;`
- `grid-template-columns`, `grid-template-rows`, `gap`

**Example:**
```css
.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
}
.grid-container > div {
  background: #fff;
  padding: 1rem;
}
```

**Learn more:**  
🔗 [CSS Tricks Grid Guide](https://css-tricks.com/snippets/css/complete-guide-grid/)

---

## 📱 Responsive Design

Make your site look good on all devices!

**Techniques:**
- Media queries
- Relative units (`em`, `rem`, `%`)
- Mobile-first approach

**Example:**
```css
@media (max-width: 600px) {
  .flex-container {
    flex-direction: column;
  }
}
```

**Learn more:**  
🔗 [MDN Responsive Design](https://developer.mozilla.org/en-US/docs/Learn/CSS/CSS_layout/Responsive_Design)

---

## 🧩 CSS Pre-processors (Sass/Less)

Write modular, maintainable CSS with variables, nesting, and mixins.

**Sass Example:**
```scss
$primary: #3498db;

.button {
  background: $primary;
  color: #fff;
  padding: 1rem;
  border-radius: 4px;
  &:hover {
    background: darken($primary, 10%);
  }
}
```

**Learn more:**  
🔗 [Sass Official Site](https://sass-lang.com/)

---

## 📚 Useful Resources

- [MDN Web Docs](https://developer.mozilla.org/)
- [Flexbox Froggy (Game)](https://flexboxfroggy.com/)
- [Grid Garden (Game)](https://cssgridgarden.com/)
- [Can I use](https://caniuse.com/)

---

## 🛠️ Sample Project

See a [sample HTML & CSS project here](./index.html)  
Source Sass: [styles.scss](./styles.scss)  
Compiled CSS: [styles.css](./styles.css)

---

Happy coding! ✨

[🔙 Back ](../README.md)
