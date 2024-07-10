package edu.yu.introtoalgs;
import java.util.Random;



    class ThreeSum extends BigOMeasurable {
        private int[] nums;
        public int count;

        @Override
        public void setup(int n) {
            nums = new int[n];
            Random random = new Random();

            for (int i = 0; i < n; i++) {
                // Generate a random number between -1,000,000 and 1,000,000
                nums[i] = random.nextInt(10000) - 5000;
            }
        }

        @Override
        public void execute() {
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            this.count++;
                        }
                    }
                }
            }


        }


    }



