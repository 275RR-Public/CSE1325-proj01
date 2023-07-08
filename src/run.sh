#!/bin/bash
#chmod u+x recompile.sh

cd "$(dirname "$0")"
./asset/boom
java Game.java