//README this implements insertion sort, merge sort and quicksort. Used for comparison between the sorting methods.
#include <stdlib.h>
#include <stdio.h>


void quicksort() {
void merge_sort()
void print_array(int arr[], int size);
void insertion_sort(int arr[], int size);
int main(int argc, char const *argv[])
{
    int arr[] = {1, 2, 5, 3, 4, 0};
    int n = sizeof(arr) / sizeof(arr[0]);
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
            printf("Amount of swaps: %d\n", ++count_of_swaps);
        }
    }
}

void quicksort() {
}

void merge_sort() {
}
void print_array(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
}