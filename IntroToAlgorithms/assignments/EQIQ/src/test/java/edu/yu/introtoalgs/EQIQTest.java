package edu.yu.introtoalgs;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class EQIQTest {
    double DELTA = .3;


    @Test
    public void initTest() {
        EQIQBase eqiqBase = new EQIQ(100, new double[]{10.1, 10}, new double[]{20.1, 20}, 1);
        assertFalse(eqiqBase.canNepotismSucceed());
        assertEquals(-1, eqiqBase.getNumberEQQuestions(), 0);
        assertEquals(-1, eqiqBase.getNumberIQQuestions(), 0);
        assertEquals(-1, eqiqBase.getNumberOfSecondsSuccess(), 0);
    }

    @Test
    public void basicTest() {
        EQIQBase eqiqBase = new EQIQ(2, new double[]{10, 20}, new double[]{40, 40}, 1);
        assertTrue(eqiqBase.canNepotismSucceed());
        double expected = 0;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, 0);
        assertEquals(2, eqiqBase.getNumberEQQuestions(), 0);
        double timeExpected = 360;
        assertEquals(timeExpected, eqiqBase.getNumberOfSecondsSuccess(), .3);

    }

    @Test
    public void nepotismShouldSucceed() {
        EQIQBase eqiqBase = new EQIQ(3, new double[]{20, 10, 10, 50}, new double[]{30, 10, 10, 20}, 0);
        assertTrue(eqiqBase.canNepotismSucceed());
        double expected = 3;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, 0);
        assertEquals(0, eqiqBase.getNumberEQQuestions(), 0);

    }

    @Test
    public void nepotismShouldSucceed2() {
        EQIQBase eqiqBase = new EQIQ(9, new double[]{7, 9, 3, 1.5, 9}, new double[]{10, 8, 11, 12, 9}, 0);
        assertTrue(eqiqBase.canNepotismSucceed());
        double expected = 8.25;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, DELTA);
        assertEquals(9 - expected, eqiqBase.getNumberEQQuestions(), DELTA);
        System.out.println(eqiqBase.getNumberOfSecondsSuccess());
    }

    @Test
    public void nepotismShouldSucceed3() {
        EQIQBase eqiqBase = new EQIQ(9, new double[]{7, 9, 3, 1.5}, new double[]{10, 8, 11, 12}, 0);
        assertTrue(eqiqBase.canNepotismSucceed());
        double expected = 7.8;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, DELTA);
        assertEquals(9 - expected, eqiqBase.getNumberEQQuestions(), DELTA);
        System.out.println(eqiqBase.getNumberOfSecondsSuccess());
    }

    @Test
    public void nepotismShouldSucceed4() {
        EQIQBase eqiqBase = new EQIQ(10, new double[]{5, 6, 7, 8}, new double[]{12, 11, 10, 9}, 1);
        assertTrue(eqiqBase.canNepotismSucceed());
        double expected = 7.5;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, DELTA);
        assertEquals(10 - expected, eqiqBase.getNumberEQQuestions(), DELTA);
    }

    @Test
    public void nepotismShouldNotSucceed() {
        EQIQBase eqiqBase = new EQIQ(9, new double[]{7, 9, 3, 1.5, 9, 6}, new double[]{10, 8, 11, 12, 9, 25}, 0);
        assertFalse(eqiqBase.canNepotismSucceed());
        double expected = -1;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, DELTA);
        assertEquals(expected, eqiqBase.getNumberEQQuestions(), DELTA);
    }

    @Test
    public void nepotismShouldNotSucceed2() {
        EQIQBase eqiqBase = new EQIQ(9, new double[]{7, 9, 3, 1.5, 9, 6}, new double[]{10, 8, 11, 12, 9, 25}, 0);
        assertFalse(eqiqBase.canNepotismSucceed());
        double expected = -1;
        double value = eqiqBase.getNumberIQQuestions();
        assertEquals(expected, value, DELTA);
        assertEquals(expected, eqiqBase.getNumberEQQuestions(), DELTA);
    }

    @Test
    public void maintainOwnershipTest() {
        final double[] myArray = {1, 2, 3, 4};
        final double[] myArray1 = {1, 2, 3, 4};
        EQIQBase eqiqBase = new EQIQ(2, myArray1, myArray, 3);
        assertArrayEquals(new double[]{1, 2, 3, 4}, myArray, 0);
        assertArrayEquals(new double[]{1, 2, 3, 4}, myArray1, 0);
    }

    @Test
    public void manyContestants() {
        Random random = new Random();
        int nepotismCandidate = random.nextInt(100);
        System.out.println("NEPOTISM CANDIDATE IS" + nepotismCandidate);
        final int totalQuestions = 10;
        final int nCandidates = 100;
        final double[] eqSuccessRate = new double[nCandidates];
        final double[] iqSuccessRate = new double[nCandidates];

        for (int i = 0; i < nCandidates; i++) {
            eqSuccessRate[i] = i + 1;   // EQ success rate increases linearly from 1 to 100
            iqSuccessRate[i] = 101 - i; // IQ success rate decreases linearly from 100 to 1
        }
        EQIQBase eqiqBase = new EQIQ(totalQuestions, eqSuccessRate, iqSuccessRate, nepotismCandidate);
        assertTrue(eqiqBase.canNepotismSucceed());
    }

    @Test
    public void manyCandidates() {
        final int totalQuestions = 10_000;
        //22 candidates
        final double[] eqSuccessRate = {10.0, 20.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0};
        final double[] iqSuccessRate = {40.0, 40.0, 39.0, 38.0, 37.0, 36.0, 35.0, 34.0, 33.0, 32.0, 31.0, 30.0, 29.0, 28.0, 27.0, 26.0, 25.0, 24.0, 23.0, 22.0, 21.0, 20.0};
        final int nepotismIndex = 1;
        final EQIQBase eqiq = new EQIQ(totalQuestions, eqSuccessRate, iqSuccessRate, nepotismIndex);

        System.out.println("EQ Questions: " + eqiq.getNumberEQQuestions());
        System.out.println("IQ Questions: " + eqiq.getNumberIQQuestions());
        System.out.println("Number of seconds success: " + eqiq.getNumberOfSecondsSuccess());
        assertTrue(eqiq.canNepotismSucceed());
    }
}
