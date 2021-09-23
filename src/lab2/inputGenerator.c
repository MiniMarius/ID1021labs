#include <stdlib.h>
#include <stdio.h>

// Usage: generateInput seed nrIntegers maxInt
// Output: nrIntegers in interval [0,maxInt]

int main(int argc, char *argv[])
{
    unsigned int seed;
    int nrIntegers;
    int maxInt;
    int i;

    sscanf(argv[1],"%d", &seed);
    sscanf(argv[2],"%d", &nrIntegers);
    sscanf(argv[3],"%d", &maxInt);

    printf("%d\n", nrIntegers);

    srand(seed);
    for(i=0; i<nrIntegers; i++)
        printf("%d ", (int)(maxInt * ((double)rand()/(double)RAND_MAX)));
}