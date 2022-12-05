#!/bin/sh

cd antlr
mvn exec:java -Dexec.args="../$1"