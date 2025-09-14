# Library Management CLI (KFD Test Assignment)

Simple Java CLI for managing books, users, and borrowing operations.

## Features

- Add/remove/search books and users (Students, Faculty, Guests)
- Borrow/return books, track overdue, calculate fines
- Interactive CLI

## Requirements

- Java 17+
- Optional: IntelliJ IDEA or any Java IDE to test

## Structure
```bash.
.
├── Makefile
├── out # for jrk
│   └── ...
├── README.md
└── src
    ├── engine # interface pakage
    │   ├── CLI.java # dialoge func for cli
    │   ├── Library.java # interface that compile Book and User storages
    │   └── Main.java # for using cli
    ├── models # inner packege
    │   ├── Book.java 
    │   ├── BookStorage.java
    │   ├── ErrorMess.java
    │   ├── Faculty.java
    │   ├── Guest.java
    │   ├── Student.java
    │   ├── User.java
    │   └── UsersData.java
    └── test
        └── Test.java # place for virtualising input stream
```
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

You can use the provided Makefile to compile, run, and lint the project.

```bash
make compile     # compile all Java sources into the 'out' folder
make run_cli     # starts the interactive CLI
make run_test    # runs the test scenario in test.Test
make lint        # runs Checkstyle on all source files
make clean       # removes compiled files in 'out'
make clean-all   # removes compiled files and Checkstyle artifacts
```
## P.S.

if we talk about LLM, i use it for consultations(cause i am not main-java writer and i have some pointer problems that was solved by AI) and making linter settings (in makefile too). And of course i used Idea Code Completion, that helped me to write (getters/settrs for example)