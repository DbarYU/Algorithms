package edu.yu.introtoalgs;

import java.util.Random;

public class LinearithmicFunction extends BigOMeasurable{
    int[] arr;

    /**
     * Performs a single execution of an algorithm: MAY ONLY be invoked after
     * setup() has previously been invoked.  The algorithm must scale as a
     * function of the parameter "n" supplied to setup().
     * <p>
     * NOTE: ONLY the duration of this method should be considered when
     * evaluating algorithm performance.
     */
    @Override
    public void execute() {
        mergeSort(arr, 0, n - 1);
    }

        private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

    @Override
    public void setup(int n){
        super.setup(n);
        Random random = new Random();
        arr = new int[n];
        for(int i =0; i<n; i++){
            arr[i] = i +1;
        }
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }
}
