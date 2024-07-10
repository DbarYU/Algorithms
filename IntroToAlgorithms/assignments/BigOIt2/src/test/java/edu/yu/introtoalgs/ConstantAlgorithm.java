package edu.yu.introtoalgs;

import java.util.HashSet;

public class ConstantAlgorithm extends BigOMeasurable {
    int result;

    @Override
    public void setup(int n){
    super.setup(n);
    this.result = 0;
    }



    @Override
    public void execute() {
        // This algorithm always performs a constant number of operations based on 'n'
        int n = this.n;
        this.result = n * 10; // This operation is constant based on 'n'
    }
}

