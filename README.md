# Table of Contents
1. [Problem](README.md#problem)
2. [Approach](README.md#Approach)
3. [Assumptions](README.md#Assumptions)
4. [Test Cases](README.md#Test Cases)
5. [Instructions](README.md#Instructions)


# Problem

Imagine you are a data engineer working for an online pharmacy. You are asked to generate a list of all drugs, the total number of UNIQUE individuals who prescribed the medication, and the total drug cost, which must be listed in descending order based on the total drug cost and if there is a tie, drug name. 

Disclosure: The projects that Insight Data Engineering Fellows work on during the program are much more complicated and interesting than this coding challenge. This challenge only tests you on the basics. 

# Approach

* Created a class which has "Drug Name","Number of prescribes","Total cost". It will be easy to hold these data as object rather using treemap or hashmap. By using objects it will be very easy to add update or remove the objects.
* A Set is maintained to check the drug names.
    *  If Drug Name is new(not in the set) then it will create drugDetails object and add it to the List.
    *  else drugDetails object is updated.
* For sorting the output based on Cost and Name. I implemented a comparator class called `SortByCost.` It sorts descending order of the cost and if cost are same then descending order of the Name.

In this Java project I have created two files : `PharmacyCounting.java` and `drugDetails.java`. I used FileInputStream so it can read large files line by line using less space. 

* To split the each line from input file into `ID, Last_Name, First_Name, Drug_Name, Drug_Cost` i used regular expressions instead of normal split becuase some of the drug names has comma in their drug names and prescriber names.

# Assumptions :
* The problem statement has no information about the printing format of the cost. So I made some assumptions like below: 
    - If total cost is  100 then output will be 100
    - If total is 100.2563 then output will be 100.25

# Test Cases : 
* This project result is tested with both the run_tests.sh and JUnit test cases.<br>
    To run the JUnit cases, 
    - First you need to add junit-4.10.jar file
    - Open the test cases folder under the src folder.
    - Then run the individual or all test cases.
    
# Instructions:
* Install Java from the [official website](https://java.com/en/).
* Make sure updated to install and set the environment  of JAVA compiler and interpreter.
* Update the PATH variable in Environmental variables with the location of JDK and JRE
* If Java and runtime installed correctly the execute `run.sh` file   
    
