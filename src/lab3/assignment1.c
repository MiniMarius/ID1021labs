//README this is a simple filter that removes all characters that are not alphabetic, blank or newline.
#include <stdio.h>
#include <ctype.h> /* for tolower */

int filter(FILE *input, FILE *output);
int main(void)
{
    const int filtered_stream = filter(stdin, stdout);
    fflush(stdout);
    return filtered_stream;
}

int filter(FILE *input, FILE *output )
{
    while (!feof(input)) {
        int current_char = fgetc(input);
        if (!(isalpha(current_char) || current_char == ' ' || current_char == '\n')) {
            fputc(' ', output);
        }
        else fputc(current_char, output);
    }
    return 0;
}
