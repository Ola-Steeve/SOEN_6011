Sure! Here’s the README with the **commit message guidelines** section removed, keeping everything else as requested:

---

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
```

### 2. Build the project with Maven

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn exec:java -Dexec.mainClass="ArccosConsole"
```

Or run the compiled jar directly:

```bash
java -cp target/ArccosConsole-1.1.0.jar ArccosConsole
```

---

## 🛠️ Tools & Technologies

* Java 17+
* Maven (build & dependency management)
* FlatLaf (modern dark-themed GUI)
* Checkstyle (code style enforcement)
* **PMD** (static code analysis for identifying common coding issues)
* **SonarLint** (IDE static analysis integration for continuous feedback)
* **JDB (Java Debugger)** (used for step-by-step debugging and inspecting runtime state)
* Git & GitHub (version control & hosting)

---

## 📊 Static Analysis & Debugging Snapshots

* **PMD snapshots**: Included in `/docs/pmd-scan.png` showing rule checks and violations fixed.
* **Checkstyle reports**: Available in `/docs/checkstyle-report.html`.
* **JDB debugging session snapshots**: Included in `/docs/jdb-debug-session.png` demonstrating breakpoints and variable inspection during execution.

