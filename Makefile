compile:
	@mkdir -p out
	javac -d out $(shell find src -name "*.java")

run-test: compile
	java -cp out test.Test

run-cli: compile
	java -cp out engine.Main

lint: checkstyle.jar checkstyle.xml
	java -jar checkstyle.jar -c checkstyle.xml $(shell find src/engine src/models -name "*.java")

checkstyle.jar:
	curl -L -o checkstyle.jar https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.17.0/checkstyle-10.17.0-all.jar

checkstyle.xml:
	@echo '<?xml version="1.0"?>' > checkstyle.xml
	@echo '<!DOCTYPE module PUBLIC "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN" "https://checkstyle.org/dtds/configuration_1_3.dtd">' >> checkstyle.xml
	@echo '<module name="Checker">' >> checkstyle.xml
	@echo '  <module name="LineLength">' >> checkstyle.xml
	@echo '    <property name="max" value="120"/>' >> checkstyle.xml
	@echo '  </module>' >> checkstyle.xml
	@echo '  <module name="TreeWalker">' >> checkstyle.xml
	@echo '    <module name="WhitespaceAround"/>' >> checkstyle.xml
	@echo '    <module name="Indentation"/>' >> checkstyle.xml
	@echo '    <module name="EmptyBlock"/>' >> checkstyle.xml
	@echo '    <module name="UnusedImports"/>' >> checkstyle.xml
	@echo '  </module>' >> checkstyle.xml
	@echo '</module>' >> checkstyle.xml

clean:
	rm -rf out

clean-all: clean
	rm -f checkstyle.jar checkstyle.xml

.PHONY: all compile run-test run-cli lint clean clean-all
