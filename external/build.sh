#!/bin/sh

# build
cd kernel-jvm
mvn clean install

cd ../antlr
mvn clean package