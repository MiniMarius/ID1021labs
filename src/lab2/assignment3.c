//README this takes an array of integers and puts all the negative elements before the positive. No sorting methods used.

#include <stdio.h>
#include <math.h>

void shift_negatives(int arr[], int size);
void print_array(int arr[], int size);
int main() {
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
            int key, j;
            for (int i = 0; i < size; i++) {
                key = arr[i];

                // skip element if positive
                if (key > 0)
                    continue;

                j = i - 1;
                while (j >= 0 && arr[j] > 0) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                // Put negative element at its right position
                arr[j + 1] = key;
            }
}



