package edu.yu.introtoalgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EQIQ extends EQIQBase{
    private double maxTimeForSuccess;
    private final List<LinearFunction> timeFunctions;
    private class LinearFunction{
        private final double m;
        private final double b;
        private LinearFunction(double m,double b){
            this.b = b;
            this.m =m;
        }

        private double getB() {
            return b;
        }

        private double getM() {
            return m;
        }

        private double getValue(double x) {return (this.m*x + b);}
        private double functionIntercept(LinearFunction function){
            if(function.getM() == this.m && function.getB() ==this.b){
                return 0;
            }
            double m = this.m - function.getM();
            double b = function.getB() - this.b;
            return  b/m;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinearFunction that = (LinearFunction) o;
            return Double.compare(m, that.m) == 0 && Double.compare(b, that.b) == 0;
        }

    }
    private boolean canNepotismSucceed;
    private final ArrayList<LinearFunction> marginTimeFunctions;
    private double IQquestions;
    private double EQquestions;
    private final LinearFunction nepotismFunction;
    /**
     * Constructor: supplies the information needed to solve the EQIQ problem.
     * When the constructor invocation completes successfully, clients invocation
     * of every other API method must return in O(1) time.
     * <p>
     * param totalQuestion the number of questions on the candidate interview
     * test, must be greater than 1
     *
     * @param totalQuestions
     * @param eqSuccessRate  the ith element of this array specifies the success
     *                       rate of the ith candidate for EQ questions.  Client maintains ownership.
     * @param iqSuccessRate  the ith element of this array specifies the success
     *                       rate of the ith candidate for IQ questions.  Client maintains ownership.
     *                       <p>
     *                       NOTE: the size of the two arrays must be identical, and greater than one.
     * @param nepotismIndex  the index in the above arrays that specifies the
     *                       values of the nepotism candidate.  Candidate indices are numbered
     *                       0..nCandidates -
     */
    public EQIQ(int totalQuestions, double[] eqSuccessRate, double[] iqSuccessRate, int nepotismIndex) {
        super(totalQuestions, eqSuccessRate, iqSuccessRate, nepotismIndex);

        validate(totalQuestions , eqSuccessRate , iqSuccessRate , nepotismIndex);
        int numOfCandidates = eqSuccessRate.length;

        this.marginTimeFunctions = new ArrayList<>();
        this.timeFunctions = new ArrayList<>();
        double EQSuccessRateForNepotismCandidate = eqSuccessRate[nepotismIndex];
        double IQSuccessRateForNepotismCandidate = iqSuccessRate[nepotismIndex];



        double mOfNepotismCandidate = 3600/IQSuccessRateForNepotismCandidate - 3600/EQSuccessRateForNepotismCandidate;
        double bOfNepotismCandidate = 3600/EQSuccessRateForNepotismCandidate * totalQuestions;
        this.nepotismFunction = createTimeFunction(3600/IQSuccessRateForNepotismCandidate,3600/EQSuccessRateForNepotismCandidate,totalQuestions);

        this.canNepotismSucceed = true;
        for (int i = 0; i < numOfCandidates; i++) {
            if (i != nepotismIndex) {
                if (iqSuccessRate[i] >= IQSuccessRateForNepotismCandidate && eqSuccessRate[i] >= EQSuccessRateForNepotismCandidate) {
                    this.canNepotismSucceed = false;
                    break;
                } else {
                    double m = 3600/iqSuccessRate[i] - 3600/eqSuccessRate[i];
                    double b = 3600/eqSuccessRate[i] * totalQuestions;
                    this.marginTimeFunctions.add(new LinearFunction(m-mOfNepotismCandidate,b-bOfNepotismCandidate));
                    this.timeFunctions.add(createTimeFunction(3600/iqSuccessRate[i],3600/eqSuccessRate[i],totalQuestions));
                }
            }
        }

        if (canNepotismSucceed) {
            double numberOfIQQuestions = calculateMinimumMargin(totalQuestions, EQSuccessRateForNepotismCandidate, IQSuccessRateForNepotismCandidate);
            if (numberOfIQQuestions == -1)
                this.canNepotismSucceed = false;
            else {
                this.canNepotismSucceed = true;
                this.IQquestions = numberOfIQQuestions;
                this.EQquestions = totalQuestions - numberOfIQQuestions;
                calculateTime();
                round();
            }

        }
    }

    private void round() {
        int decimalPlaces = 3;
        double multiplier = Math.pow(10, decimalPlaces);
        this.IQquestions = Math.round(this.IQquestions * multiplier) / multiplier;
        this.EQquestions = Math.round(this.EQquestions * multiplier) / multiplier;
        this.maxTimeForSuccess = Math.round(this.maxTimeForSuccess * multiplier) / multiplier;
    }

    private void validate(int totalQuestions, double[] eqSuccessRate, double[] iqSuccessRate, int nepotismIndex) {
        if(eqSuccessRate == null)
            throw  new IllegalArgumentException();
        if(iqSuccessRate == null)
            throw  new IllegalArgumentException();
        if(totalQuestions == 0)
            throw new IllegalArgumentException();
        if(nepotismIndex < 0)
            throw new IllegalArgumentException();
        if(eqSuccessRate.length != iqSuccessRate.length)
            throw new IllegalArgumentException();

    }

    private LinearFunction createTimeFunction(double v, double v1, int totalQuestions) {
        return new LinearFunction(v-v1,totalQuestions*v1);
    }

    private void calculateTime() {
        double timeForNepotism = this.nepotismFunction.getValue(this.IQquestions);
        double high = Integer.MAX_VALUE;
        for(LinearFunction linearFunction:this.timeFunctions){
            high = Math.min(high,linearFunction.getValue(this.IQquestions));
        }
        this.maxTimeForSuccess = high- timeForNepotism;

    }
    private void bruteForce(int totalQuestions){
        double max = 0;
        double x = -1;
        for(double i =0; i<totalQuestions;i++){
            for(double j = 0; j<1; j+=.001){
                double maxMargin = Double.MAX_VALUE;
                double time = i+j;
                double nepotismTime = this.nepotismFunction.getValue(time);
                for(LinearFunction linearFunction: this.timeFunctions){
                    double margin = linearFunction.getValue(time) - nepotismTime;
                    maxMargin =Math.min(maxMargin,margin);
                }
                x = maxMargin > max ? time : x;
                max = Math.max(max,maxMargin);
            }
        }
        System.out.println("Final IQ questions for Brute force = "+x);
        System.out.println("BRUTE FORCE MAX MARGIN = "+max);
    }

    private double calculateMinimumMargin(int totalQuestions,double eqSuccess,double iqSuccess) {
        double maxMargin1 = eqSuccess > iqSuccess ? eqSuccess * totalQuestions : iqSuccess * totalQuestions;
        double maxMargin2 = maxMargin1;
        double maxMargin3 = 0;

        for (LinearFunction linearFunction : this.marginTimeFunctions) {
            maxMargin1 = Math.min(maxMargin1, linearFunction.getValue(0));
            maxMargin2 = Math.min(maxMargin2, linearFunction.getValue(totalQuestions));
        }

        double minX =-1;
        for (int i = 0; i < marginTimeFunctions.size(); i++) {
            for (int j = i + 1; j < marginTimeFunctions.size(); j++) {
                LinearFunction function1 = marginTimeFunctions.get(i);
                LinearFunction function2 = marginTimeFunctions.get(j);
                double xIntercept = function1.functionIntercept(function2);
                if (!(xIntercept > totalQuestions || xIntercept < 0)) {
                    double temp = function1.getValue(xIntercept);
                    for (LinearFunction function : marginTimeFunctions) {
                        temp = Math.min(temp, function.getValue(xIntercept));
                    }
                    minX = temp >  maxMargin3 ? xIntercept : minX;
                    maxMargin3 = Math.max(temp, maxMargin3);
                }
            }
        }


        if(maxMargin1 <=0 && maxMargin2 <=0 && maxMargin3<=0)
            return -1;
        double min =  Math.max(maxMargin3, Math.max(maxMargin1, maxMargin2));
        if(min == maxMargin3)
            return minX;
        else if(min == maxMargin2)
            return totalQuestions;
        else
            return 0;
    }



    /**
     * Return true iff some combination of EQ and IQ questions allow the
     * "nepotism candidate" to win.
     */
    @Override
    public boolean canNepotismSucceed() {
        return canNepotismSucceed;
    }

    /**
     * If canNepotismSucceed() is true, returns the number of EQ questions
     * (accurate to three decimal places) in the optimal configuration for the
     * nepotism candidate; otherwise, returns -1.0
     */
    @Override
    public double getNumberEQQuestions() { return this.canNepotismSucceed ? this.EQquestions : -1;}

    /**
     * If canNepotismSucceed() is true, returns the number of IQ questions
     * (accurate to three decimal places) in the optimal configuration for the
     * neptotism candidate; otherwise, returns -1.0
     */
    @Override
    public double getNumberIQQuestions() {return this.canNepotismSucceed ? this.IQquestions : -1;}

    /**
     * If canNepotismSucceed() is true, returns the number of SECONDS (accurate
     * to three decimal places) by which the nepotism candidate completes the
     * test ahead of the next best candidate; otherwise, returns -1.0
     */
    @Override
    public double getNumberOfSecondsSuccess() {
        return this.canNepotismSucceed ? this.maxTimeForSuccess : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EQIQ eqiq = (EQIQ) o;
        return Double.compare(maxTimeForSuccess, eqiq.maxTimeForSuccess) == 0 && canNepotismSucceed == eqiq.canNepotismSucceed && Double.compare(IQquestions, eqiq.IQquestions) == 0 && Double.compare(EQquestions, eqiq.EQquestions) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxTimeForSuccess,canNepotismSucceed, IQquestions, EQquestions);
    }
}