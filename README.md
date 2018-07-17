# Table of Contents
1. [Problem](README.md#problem)
2. [Approach](README.md#Approach)
3. [Instructions](README.md#instructions)
4. [Output](README.md#output)
5. [Tips on getting an interview](README.md#tips-on-getting-an-interview)
6. [Instructions to submit your solution](README.md#instructions-to-submit-your-solution)
7. [Questions?](README.md#questions?)

# Problem

Imagine you are a data engineer working for an online pharmacy. You are asked to generate a list of all drugs, the total number of UNIQUE individuals who prescribed the medication, and the total drug cost, which must be listed in descending order based on the total drug cost and if there is a tie, drug name. 

Disclosure: The projects that Insight Data Engineering Fellows work on during the program are much more complicated and interesting than this coding challenge. This challenge only tests you on the basics. 

# Approach

* Created a class which has "Drug Name","Number of prescribers","Total cost". It will be easy to hold these data as object rather using treemap or hashmap. By using objects it will be very easy to add update or remove the objects.
* A Set is maintained to check the drug names.
    *  If Drug Name is new(not in the set) then it will create drugDetails object and add it to the List.
    *  else drugDetails object is updated.

In this Java project I have created two files : `PharmacyCounting.java` and `drugDetails.java`. I used FileInputStream so it can read large files line by line using less space. 

* To split the each line from input file into `ID, Last_Name, First_Name, Drug_Name, Drug_Cost` i used regular expressions instead of normal split becuase some of the drug names has comma in their drug names and prescriber names.

# Assumptions :
* The problem statement has no infomration about the printing format of the cost. 
- For example 

    
