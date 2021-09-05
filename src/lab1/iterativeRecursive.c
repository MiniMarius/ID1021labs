#include <stdio.h>
#include <stdlib.h>
#include <string.h>




void recursiveReverse();
void iterativeReverse();
int main(int argc, char const *argv[])
{
    printf("Enter your characters please: \n");
    //recursiveFunction();
    iterativeReverse();
    return 0;
}


void recursiveReverse() {
    int c = getchar();
    if(c == '\n') return;
    recursiveReverse();
    putchar(c);
}

void iterativeReverse() {
        char c;
        char *str = malloc(20*sizeof(char));
        int i = 0;
        while((c = getchar()) != '\n')
            str[i++] = c;

        for(i = i - 1; i >= 0; i--)
            putchar(str[i]);

        free(str);
}