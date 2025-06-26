# 📦 Semantic Versioning (SemVer) Guide

## ✨ Summary
Semantic Versioning (SemVer) is a versioning scheme for software that conveys meaning about the underlying changes with each new release. It helps developers and users understand the impact of updates and manage dependencies reliably.

---

## 🧩 What is SemVer?
Semantic Versioning is a specification for assigning version numbers to software releases. The format is:

```
MAJOR.MINOR.PATCH
```

- **MAJOR**: Breaking changes (incompatible API changes)
- **MINOR**: Backwards-compatible new features
- **PATCH**: Backwards-compatible bug fixes

Example: `2.5.1` (MAJOR=2, MINOR=5, PATCH=1)

---

## 🔢 Version Numbering Explained
| Component | Meaning                                   | Example |
|-----------|-------------------------------------------|---------|
| MAJOR     | Incompatible API changes                  | 2.x.x   |
| MINOR     | Backwards-compatible new features         | x.5.x   |
| PATCH     | Backwards-compatible bug fixes            | x.x.1   |

---

## 🚦 When to Increment?
- **MAJOR**: When you make incompatible API changes
- **MINOR**: When you add functionality in a backwards-compatible manner
- **PATCH**: When you make backwards-compatible bug fixes

---

## 🏷️ Pre-release & Build Metadata
- **Pre-release**: `1.0.0-alpha`, `2.1.0-beta.2`
- **Build metadata**: `1.0.0+20130313144700`

Format: `MAJOR.MINOR.PATCH-PRERELEASE+BUILD`

---

## 📚 Example
- `1.4.2` → Minor bug fix
- `2.0.0` → Breaking change
- `1.5.0` → New feature, backwards-compatible
- `1.5.0-beta` → Pre-release version

---

## 🧪 Practical Examples

### Example 1: PATCH Release
Suppose you fix a bug in your code without changing any APIs:

- **Before:** `1.0.0`
- **After:**  `1.0.1`

> _"Fixed a typo in the documentation."_
>
> - `PATCH` version incremented

---

### Example 2: MINOR Release
You add a new, backwards-compatible feature:

- **Before:** `1.0.1`
- **After:**  `1.1.0`

> _"Added a new optional parameter to an existing function."_
>
> - `MINOR` version incremented

---

### Example 3: MAJOR Release
You make a change that breaks compatibility with previous versions:

- **Before:** `1.1.0`
- **After:**  `2.0.0`

> _"Renamed a public API method, requiring users to update their code."_
>
> - `MAJOR` version incremented

---

### Example 4: Pre-release Version
You want to publish a version for testing before the official release:

- **Version:** `2.0.0-beta.1`

> _"First beta release of the upcoming 2.0.0 version."_
>
> - Indicates a pre-release version

---

### Example 5: Build Metadata
You want to include build information:

- **Version:** `2.0.0+exp.sha.5114f85`

> _"Release 2.0.0 built from commit sha 5114f85."_
>
> - Includes build metadata

---

## 📝 Best Practices
- Start with `1.0.0` for production-ready software
- Increment versions thoughtfully
- Communicate changes clearly in release notes
- Use pre-release tags for unstable versions

---

## 🔗 References
- [semver.org](https://semver.org/)
- [Keep a Changelog](https://keepachangelog.com/)

---

## 🚀 TL;DR
> **SemVer = MAJOR.MINOR.PATCH**
> - Breaking change? Bump MAJOR
> - New feature? Bump MINOR
> - Bug fix? Bump PATCH

---

> "Version numbers are for computers, but SemVer is for humans too!" 😄
