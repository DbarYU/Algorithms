package edu.yu.introtoalgs;

public class QuadraticFunction extends BigOMeasurable {
    int[] test;
    public int sum;

    @Override
    public void setup(int n) {
        super.setup(n);
        this.test = new int[n];
        this.sum = 0;
    }


    @Override
    public void execute() {
        for(int i = 0; i< this.test.length;i++){
            for (int k = 0; k<this.test.length;k++){
                this.sum += test[i] + test[k];
            }
        }

    }
}
