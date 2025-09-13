SRC_DIR := src
OUT_DIR := out

all: compile

compile:
	@mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(shell find $(SRC_DIR) -name "*.java")

run_test: compile
	java -cp $(OUT_DIR) test.Test

run_cli: compile
	java -cp $(OUT_DIR) engine.Main

clean:
	rm -rf $(OUT_DIR)

.PHONY: all compile run_test run_cli clean
