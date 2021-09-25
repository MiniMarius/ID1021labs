//README this takes an array of integers and puts all the negative elements before the positive. No sorting methods used.

#include <stdio.h>
#include <math.h>

void shift_negatives(int arr[], int size);
void print_array(int arr[], int size);
int main(int argc, char const *argv[]) {
    int arr[] = {-4,5-13,43,-32,-5,100,93,-11,12, 11, 13, 5, 6};
    int size = sizeof(arr)/sizeof(arr[0]);

    shift_negatives(arr, size);
    print_array(arr, size);
    
    return 0;
}

//prints an array with size n
void print_array(int arr[], int size) {
    int i;
    printf("Ordered numbers: \n");
    for (i=0; i < size; i++)
        printf("%d ", arr[i]);
}

void shift_negatives(int arr[], int size) {
            if (size < 1) return;
            int i, j, current;
            for (i = 0; i < size; i++) {
                current = arr[i];
                if (current < 0) {
                    if (i != j) {
                        arr[i] = arr[j];
                        arr[j] = current;
                    }
                    j++;
                }
            }
}



