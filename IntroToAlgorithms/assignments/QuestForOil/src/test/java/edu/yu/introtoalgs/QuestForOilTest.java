package edu.yu.introtoalgs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

public class QuestForOilTest {
    @Test
    public void initialTest1() {
        final char[][] map = new char[2][2];
        map[0][0] = 'S';
        map[0][1] = 'S';
        map[1][0] = 'S';
        map[1][1] = 'S';
        final QuestForOilBase qfo = new QuestForOil(map);

        final int retval = qfo.nContiguous(0, 1);
        assertEquals(4, retval);
    }

    @Test
    public void initialTest2() {
        final char[][] map = new char[2][2];
        map[0][0] = 'S';
        map[0][1] = 'S';
        map[1][0] = 'S';
        map[1][1] = 'S';
        final QuestForOilBase qfo = new QuestForOil(map);

        final int retval = qfo.nContiguous(0, 0);
        assertEquals(4, retval);
    }

    @Test
    public void initialTest3() {
        final char[][] map = new char[2][2];
        map[0][0] = 'S';
        map[0][1] = 'S';
        map[1][0] = 'S';
        map[1][1] = 'S';
        final QuestForOilBase qfo = new QuestForOil(map);

        final int retval = qfo.nContiguous(1, 0);
        assertEquals(4, retval);
        final int retval2 = qfo.nContiguous(1, 0);
        assertEquals(4, retval2);

    }


    @Test
    public void initialTest4() {
        final char[][] map = new char[2][2];
        map[0][0] = 'S';
        map[0][1] = 'S';
        map[1][0] = 'S';
        map[1][1] = 'S';
        final QuestForOilBase qfo = new QuestForOil(map);

        final int retval = qfo.nContiguous(1, 1);
        assertEquals(4, retval);
    }

    @Test
    public void largeInputTest() {
        int inputSize = 5000;
        int exptected = 0;
        Random random = new Random();
        int randomRow = random.nextInt(101);
        int randomColumn = random.nextInt(101 - randomRow);


        char[][] matrix = new char[inputSize][inputSize];

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                if (j < inputSize - i) {
                    matrix[i][j] = 'S';
                    exptected++;
                } else {
                    matrix[i][j] = 'U';
                }
            }
        }
        System.out.println("STARTING....");
        long start = System.currentTimeMillis();
        final QuestForOilBase questForOilBase = new QuestForOil(matrix);
        int rterval = questForOilBase.nContiguous(randomRow, randomColumn);
        long end = System.currentTimeMillis();
        System.out.println("TIME = "+(end-start));
        assertEquals(exptected, rterval);
    }

    @Test
    public void largeInput2() {
        int inputSize = 5000;
        int exptected = 5000 * 5000;
        Random random = new Random();
        int randomRow = random.nextInt(101);
        int randomColumn = random.nextInt(101 - randomRow);


        char[][] matrix = new char[inputSize][inputSize];

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                matrix[i][j] = 'S';
            }
        }
        final QuestForOilBase questForOilBase = new QuestForOil(matrix);
        int rterval = questForOilBase.nContiguous(randomRow, randomColumn);
        assertEquals(exptected, rterval);
    }

    @Test
    public void largeInput3() {
        int inputSize = 5000;
        int exptected = 5000;


        char[][] matrix = new char[inputSize][inputSize];

        for (int i = 0; i < inputSize; i++) {
            matrix[0][i] = 'S';
        }
        for (int i = 1; i < inputSize; i++) {
            for (int k = 0; k < inputSize; k++) {
                matrix[i][k] = 'U';
            }
        }

        final QuestForOilBase questForOilBase = new QuestForOil(matrix);
        int rterval = questForOilBase.nContiguous(0, 0);
        assertEquals(exptected, rterval);
    }

    @Test
    public void largeInput4() {
        int inputSize = 5000;
        int exptected = 5000;


        char[][] matrix = new char[inputSize][inputSize];

        for (int i = 0; i < inputSize; i++) {
            for (int k = 0; k < inputSize; k++) {
                if (i == k)
                    matrix[i][k] = 'S';
                else
                    matrix[i][k] = 'U';
            }
        }

        final QuestForOilBase questForOilBase = new QuestForOil(matrix);
        int rterval = questForOilBase.nContiguous(0, 0);
        assertEquals(exptected, rterval);
    }

}



