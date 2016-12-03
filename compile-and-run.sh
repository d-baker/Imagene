#!/usr/bin/env bash

#######################################
# Written by Dorothea Baker (s3367422)
# for
# Programming Project 1
# SP3 2016
#######################################

# This script is intended for those who wish to compile and run the project without using an IDE. It should work on any unix operating system.

# compile all classes
find . -name "*.java" > sources.txt
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
# (Note: this will only work if you cloned the github repo for the project and have git installed)
