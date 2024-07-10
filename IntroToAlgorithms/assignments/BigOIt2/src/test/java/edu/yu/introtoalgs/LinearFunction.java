package edu.yu.introtoalgs;

    public class LinearFunction extends BigOMeasurable {
        private int[] test;


        @Override
        public void setup(int n) {
            super.setup(n);
            this.test = new int[n];
        }

        @Override
        public void execute() {
            for(int i = 0; i<this.n; i++) {
                test[i] = i+5;
            }
        }
    }

