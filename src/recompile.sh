#!/bin/bash
#chmod u+x recompile.sh

#javac doesnt always capture all changes
#full delete and recompile solves this
cd "$(dirname "$0")"
clear
find . -type f -name "*.class" -delete
javac Game.java
java Game.java