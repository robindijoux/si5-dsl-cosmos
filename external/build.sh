#!/bin/sh

java -jar antlr-4.11.1-complete.jar $1.g4

javac -cp ./antlr-4.11.1-complete.jar $1*.java