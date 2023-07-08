#!/bin/bash
#chmod u+x run.sh

cd "$(dirname "$0")"
chmod u+x ./asset/boom
clear
./asset/boom
java Game