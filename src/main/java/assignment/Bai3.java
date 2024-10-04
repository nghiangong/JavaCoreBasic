package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Bai3 {

    public static void main(String[] args) {
        int[] a = readArrayFromFile("input.txt");
        quickSort(a, 0, a.length-1);
        for (int i=0; i < a.length; i++)
            System.out.print(a[i]+" ");
    }

    public static int[] readArrayFromFile(String fileName) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            int n = scanner.nextInt();  // Read array size
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();  // Read array elements
            }

            return a;  // Return the array
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }

    static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int left = begin;
        int right = end - 1;
        while (true) {
            while (left <= right && arr[left] < pivot) left++;
            while (right >= left && arr[right] > pivot) right--;
            if (left >= right) break;
            {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            left++;
            right--;
        }
        {
            int temp = arr[left];
            arr[left] = arr[end];
            arr[end] = temp;
        }
        return left;
    }

    static void quickSort(int arr[], int low, int high){
        if (low < high) {
            int index = partition(arr, low, high);
            quickSort(arr, low, index-1);
            quickSort(arr, index+1, high);
        }
    }
}
