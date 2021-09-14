# ID1021 Lab Solutions
Marius S, metse@kth.se
## How To Run
It's recommended that you open up this repository directly from
IntellIJ, since the lib dependency inside the repo is directly configured
in this IDE. You can then choose whichever java class file to run and 
test. 

The class which uses the princeton dependency is the RecursionReverse-class. If you wish to use a CLI you'll have to configure the 
classpath/sourcepath yourself before compiling/running.
##Choice of Algorithm for assignment 1'
The algorithms utilize recursion and iteration since that's what was required by the assignment.
##Choice of Algorithm for assignment 2
The iterable solution is based upon the princeton's example of a stack. The solution with recursion is very much based upon the c code created in assignment 1, translated to java with use of Stdin from the princeton library.
##Choice of Algorithm for assignment 3
The algorithm here uses a doubly linked circular list.
##Choice of Algorithm for assignment 4
The algorithm here uses a singly linked circular list.
##Choice of Algorithm for assignment 5
The way I chose to implement the removal of kth element was by creating a loop which iterates from the head of the queue and forwards. The loop index starts with a value the size of the queue and decreases for every step forwards into inte queue until it finally stops at the desired index.
##Choice of Algorithm for assignment 6
The implementation I chose was based on the doubly linked list in assignment 3. 
##Choice of Algorithm for assignment 7
The algorithm starts with iterating over the input string.

If the currently selected char is an opening bracket, it's pushed to the stack.

If it's a closing bracket, it is compared to the top element of the stack to assert that it's a matching opening bracket. If it is, we pop the top element from the stack.

The sequence is correct if the stack is empty after the loop since that means we've matched all the brackets.
## Complexity Calculation
The space complexity of assignment 7  is O(n) as worst case scenario based on the n amount of items needed to be stored in the stack at the same time, which depends on amount of opening brackets in the input.


The time complexity of the parentheses algorithm is O(n) as worst case depending on the amount of characters in the input string which means the amount of times the for-each loop will run.
