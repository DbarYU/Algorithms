package edu.yu.introtoalgs;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class TxSortFjTest {

    @Test
    public void correctnessTest() {
        int amountOfTransactions = 9000000;
        Account account1 = new Account();
        Account account2 = new Account();
        ArrayList<TxBase> rawList = new ArrayList<>();
        TxBase[] rawArray = new TxBase[amountOfTransactions];

        for(int i =0; i<amountOfTransactions; i++){
            TxBase transaction = new Tx(account1,account2,i+1);
            rawList.add(i,transaction);
            rawArray[i] = transaction;
        }
        Collections.shuffle(rawList);

        System.out.println("FINISHED ADDING");
        TxSortFJBase sortFJBase = new TxSortFJ(rawList);
        System.out.print("TIME IT TOOK TO RUN FORK JOIN = ");
        long start = System.currentTimeMillis();
        TxBase[] forkJoinSortedArray = sortFJBase.sort();
        long end = System.currentTimeMillis() -start;
        System.out.println(end);
        assertTrue(testSorted(forkJoinSortedArray));
        assertEquals(amountOfTransactions,forkJoinSortedArray.length);


        Collections.shuffle(rawList);
        System.out.print("TIME IT TAKES TO RUN PARALLEL SORT = ");
        long time1 = System.currentTimeMillis();
        Arrays.parallelSort(rawList.toArray(rawArray));
        long time2 = System.currentTimeMillis() - time1;
        System.out.println(time2);

        Collections.shuffle(rawList);
        TxBase[] sortTest = rawList.toArray(rawArray);
        System.out.print("TIME IT TAKES TO RUN ARRAYS.SORT = ");
        long time3 = System.currentTimeMillis();
        Arrays.sort(sortTest);
        long end3 = System.currentTimeMillis() - time3;
        System.out.println(end3);
    }
    @Test
    public void initialBasicTest() {
        int amountOfTransactions = 10;
        Account account1 = new Account();
        Account account2 = new Account();
        ArrayList<TxBase> actual = new ArrayList<>();

        for(int i =0; i<amountOfTransactions; i++){
            TxBase transaction = new Tx(account1,account2,i+1);
            actual.add(i,transaction);
        }
        Collections.shuffle(actual);
        TxSortFJBase sortFJBase = new TxSortFJ(actual);
        TxBase[] lastActual = sortFJBase.sort();
        assertTrue(testSorted(lastActual));
        assertEquals(amountOfTransactions,lastActual.length);

    }
    private boolean testSorted(TxBase[] arr){
        for(int i =0; i<arr.length-1;i++){
            if(arr[i].compareTo(arr[i+1]) > 0)
                return false;
        }
        return true;
    }
    @Test
    public void nullInputsTest1() {
        int amountOfTransactions = 9000000;
        Account account1 = new Account();
        Account account2 = new Account();
        ArrayList<TxBase> rawList = new ArrayList<>();
        TxBase[] rawArray = new TxBase[amountOfTransactions];

        for(int i =0; i<amountOfTransactions; i++){
            TxBase transaction = new Tx(account1,account2,i+1);
            if(i % 5 == 0)
                transaction.setTimeToNull();

            rawList.add(i,transaction);
            rawArray[i] = transaction;
        }
        Collections.shuffle(rawList);

        System.out.println("FINISHED ADDING");
        TxSortFJBase sortFJBase = new TxSortFJ(rawList);
        System.out.print("TIME IT TOOK TO RUN FORK JOIN = ");
        long start = System.currentTimeMillis();
        TxBase[] forkJoinSortedArray = sortFJBase.sort();
        long end = System.currentTimeMillis() -start;
        System.out.println(end);
        assertTrue(testSorted(forkJoinSortedArray));
        assertEquals(amountOfTransactions,forkJoinSortedArray.length);



        Collections.shuffle(rawList);
        System.out.print("TIME IT TAKES TO RUN PARALLEL SORT = ");
        long time1 = System.currentTimeMillis();
        Arrays.parallelSort(rawList.toArray(rawArray));
        long time2 = System.currentTimeMillis() - time1;
        System.out.println(time2);

        Collections.shuffle(rawList);
        TxBase[] sortTest = rawList.toArray(rawArray);
        System.out.print("TIME IT TAKES TO RUN ARRAYS.SORT = ");
        long time3 = System.currentTimeMillis();
        Arrays.sort(sortTest);
        long end3 = System.currentTimeMillis() - time3;
        System.out.println(end3);
    }
    @Test
    public void nullInputsTest2() throws InterruptedException {
        int amountOfTransactions = 100;
        Account account1 = new Account();
        Account account2 = new Account();
        ArrayList<TxBase> rawList = new ArrayList<>();
        TxBase[] rawArray = new TxBase[amountOfTransactions];

        for(int i =0; i<amountOfTransactions; i++){
            TxBase transaction = new Tx(account1,account2,i+1);
            if(i % 5 == 0)
                transaction.setTimeToNull();

            rawList.add(i,transaction);
            rawArray[i] = transaction;
            Thread.sleep(10);
        }
        Collections.shuffle(rawList);

        System.out.println("FINISHED ADDING");
        TxSortFJBase sortFJBase = new TxSortFJ(rawList);
        System.out.print("TIME IT TOOK TO RUN FORK JOIN = ");
        long start = System.currentTimeMillis();
        TxBase[] forkJoinSortedArray = sortFJBase.sort();
        long end = System.currentTimeMillis() -start;
        System.out.println(end);
        assertTrue(testSorted(forkJoinSortedArray));
        assertEquals(amountOfTransactions,forkJoinSortedArray.length);



        Collections.shuffle(rawList);
        System.out.print("TIME IT TAKES TO RUN PARALLEL SORT = ");
        long time1 = System.currentTimeMillis();
        Arrays.parallelSort(rawList.toArray(rawArray));
        long time2 = System.currentTimeMillis() - time1;
        System.out.println(time2);

        Collections.shuffle(rawList);
        TxBase[] sortTest = rawList.toArray(rawArray);
        System.out.print("TIME IT TAKES TO RUN ARRAYS.SORT = ");
        long time3 = System.currentTimeMillis();
        Arrays.sort(sortTest);
        long end3 = System.currentTimeMillis() - time3;
        System.out.println(end3);
    }
    @Test
    public void randomAccountsTest() {
        int amountOfTransactions = 9000000;
        Account[] accounts = generateRandomAccounts(amountOfTransactions+1);
        ArrayList<TxBase> rawList = new ArrayList<>();
        TxBase[] rawArray = new TxBase[amountOfTransactions];

        for(int i =0; i<amountOfTransactions; i++){
            Account account1 = accounts[i];
            Account account2 = accounts[i+1];
            TxBase transaction = new Tx(account1,account2,i+1);
            if(i % 5 == 0)
                transaction.setTimeToNull();

            rawList.add(i,transaction);
            rawArray[i] = transaction;
        }
        Collections.shuffle(rawList);

        System.out.println("FINISHED ADDING");
        TxSortFJBase sortFJBase = new TxSortFJ(rawList);
        System.out.print("TIME IT TOOK TO RUN FORK JOIN = ");
        long start = System.currentTimeMillis();
        TxBase[] forkJoinSortedArray = sortFJBase.sort();
        long end = System.currentTimeMillis() -start;
        System.out.println(end);
        assertTrue(testSorted(forkJoinSortedArray));
        System.out.println("ASSERTED TRUE ");
        assertEquals(amountOfTransactions,forkJoinSortedArray.length);


        Collections.shuffle(rawList);
        System.out.print("TIME IT TAKES TO RUN PARALLEL SORT = ");
        long time1 = System.currentTimeMillis();
        Arrays.parallelSort(rawList.toArray(rawArray));
        long time2 = System.currentTimeMillis() - time1;
        System.out.println(time2);

        Collections.shuffle(rawList);
        TxBase[] sortTest = rawList.toArray(rawArray);
        System.out.print("TIME IT TAKES TO RUN ARRAYS.SORT = ");
        long time3 = System.currentTimeMillis();
        Arrays.sort(sortTest);
        long end3 = System.currentTimeMillis() - time3;
        System.out.println(end3);
    }
    @Test
    public void smallAccountsTest() throws InterruptedException {
        int amountOfTransactions = 100;
        Account[] accounts = generateRandomAccounts(amountOfTransactions+1);
        ArrayList<TxBase> rawList = new ArrayList<>();
        TxBase[] rawArray = new TxBase[amountOfTransactions];

        for(int i =0; i<amountOfTransactions; i++){
            Account account1 = accounts[i];
            Account account2 = accounts[i+1];
            TxBase transaction = new Tx(account1,account2,i+1);
            if(i % 5 == 0)
                transaction.setTimeToNull();

            rawList.add(i,transaction);
            rawArray[i] = transaction;
            Thread.sleep(100);
        }
        Collections.shuffle(rawList);

        System.out.println("FINISHED ADDING");
        TxSortFJBase sortFJBase = new TxSortFJ(rawList);
        System.out.print("TIME IT TOOK TO RUN FORK JOIN = ");
        long start = System.currentTimeMillis();
        TxBase[] forkJoinSortedArray = sortFJBase.sort();
        long end = System.currentTimeMillis() -start;
        System.out.println(end);
        assertTrue(testSorted(forkJoinSortedArray));
        System.out.println("ASSERTED TRUE ");

        Collections.shuffle(rawList);
        System.out.print("TIME IT TAKES TO RUN PARALLEL SORT = ");
        long time1 = System.currentTimeMillis();
        Arrays.parallelSort(rawList.toArray(rawArray));
        long time2 = System.currentTimeMillis() - time1;
        System.out.println(time2);
        assertEquals(amountOfTransactions,forkJoinSortedArray.length);

        Collections.shuffle(rawList);
        TxBase[] sortTest = rawList.toArray(rawArray);
        System.out.print("TIME IT TAKES TO RUN ARRAYS.SORT = ");
        long time3 = System.currentTimeMillis();
        Arrays.sort(sortTest);
        long end3 = System.currentTimeMillis() - time3;
        System.out.println(end3);
    }

    private Account[] generateRandomAccounts(int amountOfTransactions) {
        Account[] accounts = new Account[amountOfTransactions];
        for(int i =0; i<amountOfTransactions; i++){
            Account temp = new Account();
            accounts[i] = temp;
        }
        return accounts;
    }
}