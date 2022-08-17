#!/bin/bash

mvn clean package
cp target/converter-1.0-SNAPSHOT-jar-with-dependencies.jar .
java -jar converter-1.0-SNAPSHOT-jar-with-dependencies.jar -i input.json
