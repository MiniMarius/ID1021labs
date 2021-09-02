#include <stdio.h>
#include <stdlib.h>






int main(int argc, char const *argv[])
{
    printf("Enter your characters please: \n");
    recursiveFunction();
    return 0;
}


void recursiveFunction() {
    if (getchar() != '\n')
        recursiveFunction();
    putchar(character)
}