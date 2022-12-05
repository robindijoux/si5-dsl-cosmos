#!/bin/sh

# build
cd jvm
mvn clean install

cd ../antlr
mvn clean package