all:
	javac -d bin -cp "lib/*" src/*.java

run:
	java -cp "bin;lib/*" App