# Arccos Function – SOEN 6011 Project (Design Document 1)

This repository implements **Function 1: arccos(x)** for SOEN 6011 — Software Engineering Processes. The goal is to develop a mathematically sound, reliable, and user-friendly function to compute the inverse cosine of a number `x` ∈ \[–1, 1], with a graphical user interface and full error handling, following software engineering best practices.

---

## 📌 Project Overview

| Feature         | Description                                                 |
| --------------- | ----------------------------------------------------------- |
| Function        | `arccos(x)` (inverse cosine)                                |
| Domain          | x ∈ \[–1, 1]                                                |
| Output          | Angle in radians (ℝ)                                        |
| Input Method    | Graphical User Interface (GUI) using Java Swing             |
| Language        | Java (JDK 17+)                                              |
| Standards       | Follows ISO/IEC/IEEE 29148:2018 for requirement specs       |
| Versioning      | Semantic Versioning (e.g., 1.0.0)                           |
| Accessibility   | Implements Java Accessibility API for improved usability    |
| Style           | Enforced Google Java Style Guide via Checkstyle and PMD     |
| Static Analysis | Integrated PMD and SonarLint for continuous code quality    |
| Debugging       | Debugged using JDB (Java Debugger); snapshots included      |
| Version Control | Hosted publicly on GitHub with high-quality commit messages |

---

## 📦 Semantic Versioning (SemVer)

This project follows **Semantic Versioning 2.0.0** principles to clearly communicate the scope of changes in each release version.

Version format: `MAJOR.MINOR.PATCH` (e.g., 1.1.1)

| Version Part | Meaning                                         | When to Increment                                     |
|--------------|------------------------------------------------|------------------------------------------------------|
| **MAJOR**    | Breaking changes, incompatible API changes    | When you make incompatible API changes or big rewrites |
| **MINOR**    | Backwards-compatible new features              | When you add functionality in a backwards-compatible manner |
| **PATCH**    | Backwards-compatible bug fixes                  | When you fix bugs or make minor improvements without adding new features |

### Example Versions

- `1.0.0` — Initial stable release.
- `1.1.0` — Added new feature in a backward-compatible way.
- `1.1.1` — Fixed bugs and improved stability without new features.

---

## ✅ Features

* Validates numeric input from the user
* Rejects inputs outside the valid domain
* Catches non-numeric and malformed values gracefully
* Provides clear error messages and input prompts
* Prints results with 6 decimal place precision
* Accessible GUI components with descriptive labels and mnemonics
* Semantic versioning for release management
* Enforced code quality using Checkstyle and PMD static analysis tools
* Debugging support with JDB to trace runtime behavior and fix issues

---

## 💻 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Ola-Steeve/SOEN_6011.git
cd SOEN_6011
