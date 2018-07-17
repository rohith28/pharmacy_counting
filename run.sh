#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Below is an example of what might be found in this file if your program was written in Python
#
#python ./src/pharmacy_counting.py ./input/itcont.txt ./output/top_cost_drug.txt
#clear
#java -jar src\com\insight\pharmacy\pharmacy_counting.jar input\itcont.txt output\top_cost_drug.txt
cd src
javac -d .  PharmacyCounting.java
javac -d . drugDetails.java
java PharmacyCounting ../input/itcont.txt ../output/top_cost_drug.txt