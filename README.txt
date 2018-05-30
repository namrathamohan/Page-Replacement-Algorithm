
Code Execution Steps:
-----------------------------
Note: 
(1) I am using a 12 row input file that contains the page-reference string to test the LRU and CLOCK Page Replacement Algorithm. 
(2) The program takes three arguments:
	1st Argument: LRU or CLOCK
	2nd Argument: Number of Frames
	3rd Argument: Reference string Input file

Test case 1: To check the LRU Replacement Algorithm
----------------------------------------------------------------------
Step 1: To compile the file
            javac PageReplacementAlgorithm.java

Step 2: To execute
            java PageReplacementAlgorithm LRU 4 InputReferenceString.txt

- InputReferenceString.txt is a 12 rows input file which contains two fields. The first field is either “R” or “W” and the second field is the page number. 
This is passed as the argument while executing.

Expected Output: 
LRU Algorithm Statistics.
---------------------------------------------------
The total number of page references: 12
The total number of page misses: 8
The total number of time units for page misses: 60
The total number of time units for writing back the dirty (modified) page: 20
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Test case 2: To check the CLOCK Replacement Algorithm
--------------------------------------------------------------------------
Note: The java file is already compiled during Test Case 1 execution. So no need to again compile. 

Step to execute:
            java PageReplacementAlgorithm CLOCK 4 InputReferenceString.txt

- InputReferenceString.txt is a 12 rows input file which contains two fields. The first field is either “R” or “W” and the second field is the page number. 
This is passed as the argument while executing.

Expected Output: 
CLOCK Algorithm Statistics.
---------------------------------------------------
The total number of page references: 12
The total number of page misses: 10
The total number of time units for page misses: 80
The total number of time units for writing back the dirty (modified) page: 30
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



