#!/usr/bin/env bash

# compile all classes
find -name "*.java" > sources.txt
javac -cp .:lib/* @sources.txt

# create jar file
jar cvfm Imagene.jar src/META-INF/MANIFEST.MF -C src/ .

# run jar
cd src
java -cp ../Imagene.jar:../lib/* imagene.view.Main

# To run directly (not from the jar), execute from the src directory (after compiling as above):
# java -cp .:../lib/* imagene.view.Main

# To remove compiled class files when you're done with them, execute:
# git clean -f *.class
