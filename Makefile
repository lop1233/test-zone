# Helper targets for common Maven workflows

ifeq ($(OS),Windows_NT)
MVNW := mvnw.cmd
else
MVNW := ./mvnw
endif

JAR := target/quarkus-app/quarkus-run.jar

.PHONY: compile package run run-jar clean

compile:
	$(MVNW) compile

package:
	$(MVNW) package

run:
	$(MVNW) quarkus:dev

run-jar: package
	java -jar "$(JAR)"

clean:
	$(MVNW) clean
