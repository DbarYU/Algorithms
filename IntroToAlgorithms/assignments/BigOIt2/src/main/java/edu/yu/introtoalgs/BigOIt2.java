package edu.yu.introtoalgs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;


public class BigOIt2 extends BigOIt2Base {
    private double[][]data;
    private double[][] ratio;
    private double[] iteration;

    private  int INIT ;
    private final int iterations = 100;
    private final int doubles = 6;
    private int counter;

    public BigOIt2(){
        setData();
    }
    private  void  setData(){
        this.INIT = 250;
        this.data = new double[this.iterations][doubles];
        this.ratio = new  double[this.iterations][doubles];
        this.iteration = new double[this.iterations];
        this.counter = INIT;
    }

    @Override
    public double doublingRatio(String bigOMeasurable, long timeOutInMs) {
        // need to set a reasonable timeout in second.
        BigOMeasurable obj = bigOMeasurable(bigOMeasurable);
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            Runnable runnable = () -> {
                for(int k = 0;k<this.iterations;k++){
                    boolean toMuchTime = false;
                    boolean toLittleTime = false;
                    for (int i = 0; i < doubles; i++) {
                        obj.setup(counter);
                        long startTime = System.currentTimeMillis();
                        long startTimeNano = System.nanoTime();
                        obj.execute();
                        long endTimeNano = System.nanoTime();
                        long endTime = System.currentTimeMillis();
                        long elapsedTimeInMilliseconds = (endTime - startTime);
                        long elapsedNanoTime = (endTimeNano-startTimeNano);

                        if(elapsedTimeInMilliseconds >1000)
                            toMuchTime = true;

                        if(elapsedTimeInMilliseconds < 10)
                            toLittleTime = true;

                        data[k][i] = elapsedNanoTime;
                        double doubleRatio = 0;
                        if (i!= 0)
                             doubleRatio = doubleRatio(data[k][i - 1], data[k][i]);

                        if((doubleRatio < .5 && doubleRatio != 0) || doubleRatio > 20){
                            //need bigger n to determine growth,
                            i--;}

                        else if(doubleRatio != 0){
                                ratio[k][i-1] = doubleRatio;

                        }
                            counter = counter*2;
                        }

                        if(toMuchTime)
                            INIT = INIT/2;
                        else if(toLittleTime && INIT<16000)
                            INIT = INIT*2;

                        this.counter = INIT;

                        if(k !=0)
                        this.iteration[k-1] = average(k-1);

                    }
            };
            Future<?> f = service.submit(runnable);
            f.get(timeOutInMs -50 ,TimeUnit.MILLISECONDS);
        }catch(OutOfMemoryError |InterruptedException | TimeoutException | ExecutionException e) {
            double toBeReturned = returnRatio();
            setData();
            return toBeReturned;

        }
            double toBeReturned = returnRatio();
            setData();
            return toBeReturned;

    }
    private BigOMeasurable bigOMeasurable(String bigOMeasurable){
        Class<? extends BigOMeasurable> instance;
        BigOMeasurable obj;
        try {
            instance = (Class<? extends BigOMeasurable>) Class.forName(bigOMeasurable);
            if (instance.getSuperclass() != BigOMeasurable.class)
                throw new RuntimeException("Class is not Big0Measurable.");
            obj = instance.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw new RuntimeException(e.toString());
        }
        return obj;
    }
    private double doubleRatio(double t1, double t2){
        return t2/t1;
    }
    private double average(int k) {
        double num = 0;
        int i;
        for (i = 0; i < doubles-1 ;i++) {
            num = num + this.ratio[k][i];
        }
        return num / i;
    }
    private double returnRatio(){
        double num = 0;
        int i;
        for (i = 0; i < iteration.length && iteration[i] != 0;i++) {
            num = num + this.iteration[i];
        }
        if(i <= 10)
            return Double.NaN;


        return num / i;

    }
}