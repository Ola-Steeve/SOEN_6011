# Arccos Function – SOEN 6011 Project (Design Document 1)

This repository implements **Function 1: arccos(x)** for SOEN 6011 — Software Engineering Processes. The goal is to develop a mathematically sound, reliable, and user-friendly function to compute the inverse cosine of a number `x` ∈ [–1, 1], with a textual user interface and full error handling.

---

## 📌 Project Overview

| Feature         | Description                                               |
|----------------|-----------------------------------------------------------|
| Function        | `arccos(x)` (inverse cosine)                              |
| Domain          | x ∈ [–1, 1]                                               |
| Output          | Angle in radians (ℝ)                                      |
| Input Method    | Textual User Interface (TUI) – console-based             |
| Language        | Java (JDK 17+)                                            |
| Standards       | Follows ISO/IEC/IEEE 29148:2018 for requirement specs     |

---

## ✅ Features

- Validates numeric input from the user
- Rejects inputs outside the valid domain
- Catches non-numeric and malformed values
- Prints result with 6 decimal place precision
- Graceful `quit` command to exit

---

## 💻 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/arccos-function.git
cd arccos-function
