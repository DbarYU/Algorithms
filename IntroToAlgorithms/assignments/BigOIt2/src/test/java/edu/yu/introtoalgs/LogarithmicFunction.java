package edu.yu.introtoalgs;

public class LogarithmicFunction extends BigOMeasurable{
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
        int low = 0;
        int high = n - 1;
        int target = 42; // The element we want to find
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                // Element found
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

    }
    @Override
    public void setup(int n){
        super.setup(n);
        arr = new int[n];
        for (int i =0; i<n; i++){
            arr[i] = i+1;
        }
    }
}
