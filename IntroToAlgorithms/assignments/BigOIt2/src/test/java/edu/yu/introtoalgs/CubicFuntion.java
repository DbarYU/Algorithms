package edu.yu.introtoalgs;

import java.util.Random;

public class CubicFuntion extends BigOMeasurable {
    int[]test;
    public int sum;

    @Override
    public void setup(int n) {
        super.setup(n);
        Random random = new Random();
        this.test = new int[n];
        for(int i= 0; i< test.length; i++){
            test[i] = random.nextInt();
        }
        this.sum = 0;
    }


    @Override
    public void execute() {
        this.sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum += test[i] + test[j] + test[k];
                }
            }
        }
    }
    }
