# ID1021 Lab Solutions
Marius S, metse@kth.se
## How To Run
It's recommended that you open up this repository directly from
IntellIJ, since the lib dependency inside the repo is directly configured
in this IDE. You can then choose whichever java class file to run and 
test. 

The class which uses the princeton dependency is the RecursionReverse-class. If you wish to use a CLI you'll have to configure the 
classpath/sourcepath yourself before compiling/running.

##Choice of Algorithm for parentheses balancing
The algorithm starts with iterating over the input string.

If the currently selected char is an opening bracket, it's pushed to the stack.

If it's a closing bracket, it is compared to the top element of the stack to assert that it's a matching opening bracket. If it is, we pop the top element from the stack.

The sequence is correct if the stack is empty after the loop since that means we've matched all of the brackets.
## Complexity Calculation
The space complexity of assignment 7  is O(n) as a worst case scenario based on the n amount of items needed to be stored in the stack at the same time, which depends on amount of opening brackets in the input.


The time complexity of the parentheses algorithm is O(n) as a worst case depending on the amount of characters in the input string which means the amount of times the for-each loop will run.
