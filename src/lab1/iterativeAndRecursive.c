#include <stdio.h>
#include <stdlib.h>
#include <string.h>




void recursive_reverse();
void iterative_reverse();
int main(int argc, char const *argv[])
{
    printf("\nEnter your characters for recursive reversal test:");
    recursive_reverse();
    printf("\nEnter your characters for iterative reversal test:");
    iterative_reverse();
    return 0;
}


void recursive_reverse() {
    int c;
    if((c = getchar()) == '\n') return;
    recursive_reverse();
    putchar(c);
}

void iterative_reverse() {
        int *char_array = malloc(sizeof(char)*100);
        int c;
        int i = 0;
        while((c= getchar()) != '\n')
            char_array[i++] = c;

        for(i = i - 1; i >= 0; i--)
            putchar(char_array[i]);
        free(char_array);
}