//README this implements a method count_inversions which counts the number of inversions in the input array and prints a list of all inversions
//time complexity is O(n^2) since we have a nested loop that both iterate over n amount of items.
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