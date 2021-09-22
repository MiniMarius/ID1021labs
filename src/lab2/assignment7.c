//README this implements insertion sort sorted in descending order instead of ascending order.
//code copied from assingment 2, nothing changed yet.
#include <stdlib.h>
#include <stdio.h>


void count_inversions(int arr[], int size);
void print_array(int arr[], int size);
void insertion_sort(int arr[], int size);
int main(int argc, char const *argv[])
{
    int arr[] = {1, 2, 5, 3, 4, 0};
    int n = sizeof(arr) / sizeof(arr[0]);
    count_inversions(arr, n);
    insertion_sort(arr, n);
    return 0;
}
void insertion_sort(int arr[], int size) {
    int i, j;
    int count_of_swaps = 0;
    for(i = 1; i < size; i++) {
        j = i;
        while(j > 0 && arr[j-1] > arr[j]) {
            int temp = arr[j-1];
            arr[j-1] = arr[j];
            arr[j] = temp;
            j -=1;
            print_array(arr, size);
            printf("swap count:  %d\n", ++count_of_swaps);
        }
    }

}

void print_array(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
}

void count_inversions(int arr[], int size) {
    int i,j;
    int inversion_count = 0;
    for(i = 0; i < size - 1; i++) {
        for(j = i + 1; j < size; j++) {
            if(arr[i] > arr[j]) {
            inversion_count++;
            printf("[[%d,%d], [%d,%d]], ", i,arr[i], j,arr[j]);
            }
        }
    }
    printf("inversion count: %d \n", inversion_count);
}