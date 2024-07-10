package edu.yu.introtoalgs;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TxSortFJ extends TxSortFJBase {
    private class ForkSortTask extends RecursiveTask<TxBase[]> {
        private TxBase[] txBases;
        private int start;
        private int end;
        private int threshold;
        public ForkSortTask(TxBase[] txBases,int start,int end,int threshold){
            this.txBases = txBases;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }


        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        public TxBase[] compute() {
            if (end - start <= threshold) {
                return heapSort(start, end);
            } else{
                ForkSortTask forkSortTask1 = new ForkSortTask(this.txBases, start, (start + end)/2, threshold);
                ForkSortTask forkSortTask2 = new ForkSortTask(this.txBases, (start + end)/2, end, threshold);
                forkSortTask1.fork();
                TxBase[] arr1 = forkSortTask2.compute();
                TxBase[] arr2 = forkSortTask1.join();
                return mergeSorted(arr1,arr2);
            }
        }
        private TxBase[] heapSort(int start,int finish){
            PriorityQueue<TxBase> txBasePriorityQueue = new PriorityQueue<>(finish-start+1);
            for(int i = start; i<finish; i++){
                txBasePriorityQueue.add(this.txBases[i]);
            }
            TxBase[] sortedArray = new TxBase[finish-start];
            for(int i=0; i<sortedArray.length;i++){
                sortedArray[i] = txBasePriorityQueue.remove();
            }
            return sortedArray;
        }
        private TxBase[] mergeSorted(TxBase[] arr1,TxBase[] arr2){
            TxBase[] sortedArray = new TxBase[arr1.length+arr2.length];
            int arr1Counter = 0,arr2Counter = 0;

            while(arr1Counter + arr2Counter < sortedArray.length){
                if(arr1Counter == arr1.length){
                    for(int i =arr2Counter;i<arr2.length;i++){
                        sortedArray[arr1Counter+i] = arr2[i];
                    }
                    break;
                }else if(arr2Counter == arr2.length){
                    for(int i = arr1Counter;i<arr1.length;i++){
                        sortedArray[arr2Counter+i] = arr1[i];
                    }
                    break;
                }
                if(arr1[arr1Counter].compareTo(arr2[arr2Counter]) < 0) {
                    sortedArray[arr1Counter + arr2Counter] = arr1[arr1Counter];
                    arr1Counter++;
                }else{
                    sortedArray[arr1Counter + arr2Counter] = arr2[arr2Counter];
                    arr2Counter++;
                }
            }
            return sortedArray;
        }

    }
    /**
     * Constructor.
     *
     * @param transactions a list of transactions, possibly not sorted.
     */
    private final TxBase[] transactions;
    private final ForkJoinPool pool;
    public TxSortFJ(List<TxBase> transactions) {
        super(transactions);
        if(transactions == null)
            throw new IllegalArgumentException();
        List<TxBase> copy = new ArrayList<>(transactions);
        this.transactions = toArray(copy);
        this.pool = ForkJoinPool.commonPool();

    }

    private TxBase[] toArray(List<TxBase> transactions) {
        TxBase[] arr = new TxBase[transactions.size()];
        for(int i=0; i<arr.length;i++){
            arr[i] = transactions.get(i);
        }
        return arr;
    }

    /**
     * Returns an array of transactions, sorted in ascending order of
     * TxBase.time() values: any instances with null TxBase.time() values precede
     * all other transaction instances in the sort results.
     *
     * @return the transaction instances passed to the constructor, returned as
     * an array, and sorted as specified above.  Students MAY ONLY use the
     * ForkJoin and their own code in their implementation.
     */
    @Override
    public TxBase[] sort() {
        return this.pool.invoke(new ForkSortTask(this.transactions,0,this.transactions.length,2000));
    }
}
