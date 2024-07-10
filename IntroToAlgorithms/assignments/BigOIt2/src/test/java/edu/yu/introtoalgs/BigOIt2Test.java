package edu.yu.introtoalgs;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class BigOIt2Test {


    BigOIt2Base bigOIt2Base;
    BigOMeasurable linearFunction;
    BigOMeasurable quadraticFuntion;
    BigOMeasurable cubicFunction;
    BigOMeasurable octopusCount;
    BigOMeasurable threeSum;
    BigOMeasurable constantAlgorithm;
    BigOMeasurable linearithmicAlgorithm;
    BigOMeasurable logarithmicAlgorithm;
    private final double DELTA =0.1;

    @Before
    public void setUp() {
        this.bigOIt2Base = new BigOIt2();
        this.linearFunction = new LinearFunction();
        this.quadraticFuntion = new QuadraticFunction();
        this.cubicFunction = new CubicFuntion();
        this.octopusCount = new OctopusCountBigO();
        this.threeSum  = new ThreeSum();
        this.constantAlgorithm = new ConstantAlgorithm();
        this.linearithmicAlgorithm = new LinearithmicFunction();
        this.logarithmicAlgorithm = new LogarithmicFunction();



    }
    @Test
    public void linearTest() {
        double linearResult = this.bigOIt2Base.doublingRatio(this.linearFunction.getClass().getName(), 40000);
        assertEquals("Linear Function, expected doubling ratio is 2", 2, linearResult, DELTA);
    }
    @Test
    public void quadraticTest() {
        BigOIt2Base bigOIt2= new BigOIt2();
        double quadraticResult = (bigOIt2.doublingRatio(this.quadraticFuntion.getClass().getName(), 40000));
        assertEquals("Quadratic Function, expected doubling ratio is 4",4,quadraticResult,DELTA);

    }
    @Test
    public void linearNotEnoughData() {
        double linearResult = this.bigOIt2Base.doublingRatio(this.linearFunction.getClass().getName(), 10);
        assertEquals("Linear Function, expected doubling ratio is 2", Double.NaN, linearResult, DELTA);
    }
    @Test
    public void linearithmicTest(){
        double constantResult = bigOIt2Base.doublingRatio(this.linearithmicAlgorithm.getClass().getName(), 40000);
        assertEquals("Logarithmic Function, expected doubling ratio is ~2",2.05,constantResult,DELTA);

    }







}