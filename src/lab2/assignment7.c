//README this implements insertion sort. Calling inverse_arr_values before and after insertion sort makes
//the array be sorted in descending order
#include <stdlib.h>
#include <stdio.h>

void inverse_arr_values(int arr[], int size);
void count_inversions(int arr[], int size);
void print_array(int arr[], int size);
void insertion_sort(int arr[], int size);

int main(int argc, char const *argv[]) {
    int arr[] = {1, 2, 5, 3, 4, 0};
    int n = sizeof(arr) / sizeof(arr[0]);
    count_inversions(arr, n);
    inverse_arr_values(arr, n);
    insertion_sort(arr, n);
    inverse_arr_values(arr, n);
    print_array(arr, n);
    return 0;
}

//takes every element * -1
void inverse_arr_values(int arr[], int size) {
    for(int i = 0; i < size; i++) {
        arr[i] *=-1;
    }
}

// insertion sort. Iterates up the array and leaves a sorted list behind it.
// checks at each index for a larger value behind it.
void insertion_sort(int arr[], int size) {
    int i, j;
    int count_of_swaps = 0;
    for(i = 1; i < size; i++) {
        j = i;
        while(j > 0 && arr[j-1] > arr[j]) {
            int temp = arr[j-1];
            arr[j-1] = arr[j];
            arr[j] = temp;
            j--;
            print_array(arr, size);
            printf("swap count:  %d\n", ++count_of_swaps);
        }
    }
}

//prints an array based on given size
void print_array(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
}

//counts how unsorted the given array is i.e how many swaps needs to be done for array
// to become sorted.
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