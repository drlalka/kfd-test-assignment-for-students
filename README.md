# Library Management CLI (KFD Test Assignment)

Simple Java CLI for managing books, users, and borrowing operations.

## Features

- Add/remove/search books and users (Students, Faculty, Guests)
- Borrow/return books, track overdue, calculate fines
- Interactive CLI

## Requirements

- Java 17+ (tested with OpenJDK 24)
- Optional: IntelliJ IDEA or any Java IDE

## Setup

1. Clone the repository:
```bash
git clone https://github.com/drlalka/kfd-test-assignment-for-students.git
cd kfd-test-assignment-for-students
```
2. Compile the project using javac:
```bash
mkdir -p out
javac -d out $(find src -name "*.java")

```
3. Run cli:
```bash
java -cp out engine.Main
```
4. Run cli-test:
```bash
java -cp out test.Test
```
## Optional: Makefile

```bash
make run_cli # starts the interactive CLI

make run_test # runs the test scenario in test.Test

make clean # deletes compiled output
```