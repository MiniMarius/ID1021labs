#include <stdlib.h>
#include <stdio.h>

void printArray(int arr[], int size);
void insertionSort(int arr[], int size);
int main(int argc, char const *argv[])
{
    int arr[] = {9, 6, 4, 5, 2};
    int n = sizeof(arr) / sizeof(arr[0]);
    insertionSort(arr, n);
    return 0;
}
void insertionSort(int arr[], int size) {
    int i;
    for(i = 1; i < size; i++) {
        int j = i;
        while(j > 0 && arr[j-1]>arr[j]) {
            int temp = arr[j-1];
            arr[j-1] = arr[j];
            arr[j] = temp;
            j -=1;
            printArray(arr, size);
        }
    }
}

void printArray(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}