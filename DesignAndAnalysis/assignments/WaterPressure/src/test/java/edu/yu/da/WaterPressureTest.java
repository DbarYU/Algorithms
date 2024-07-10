package edu.yu.da;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WaterPressureTest {
    @Test
    public void ProfessorTest(){
        String initialInputPump = "0";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        wp.addBlockage("0","1",1.0);
        wp.addBlockage("1","2",2.0);
        assertEquals(2.0,wp.minAmount(),0);
        wp.addSecondInputPump("2");
        assertEquals(1.0,wp.minAmount(),0);
    }
    @Test
    public void twoDifferentSubtrees(){
        String initialInputPump = "0";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        wp.addBlockage("0","1",1.0);
        wp.addBlockage("1","2",2.0);
        wp.addBlockage("hello","howAreYa?",.1);
        assertEquals(-1,wp.minAmount(),0);;
    }
    @Test
    public void largeScaleTest1(){
        String initialInputPump = "0";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        int scalar = 15000000;

        for(int i=0; i<scalar;i++){
            wp.addBlockage(String.valueOf(i),String.valueOf(i+1),i+1);
        }


        assertEquals(1,wp.minAmount(),scalar+1);

    }
    @Test
    public void largeScaleTest2(){
        String initialInputPump = "0";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        int scalar = 10000000;

        for(int i=0; i<scalar;i++){
            wp.addBlockage(String.valueOf(i),String.valueOf(i+1),i+1);
            wp.addBlockage(String.valueOf(i+1),String.valueOf(i),i+1);
        }
        wp.addSecondInputPump(String.valueOf(scalar));


        assertEquals(1,wp.minAmount(),scalar);

    }
    @Test
    public void smallScaleTest1(){
        String initialInputPump = "0";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        wp.addBlockage(initialInputPump,"1",1);
        wp.addBlockage(initialInputPump,"2",7);
        wp.addBlockage("1","2",5);
        wp.addBlockage("1","4",4);
        wp.addBlockage("1","5",3);
        wp.addBlockage("5","4",2);
        wp.addBlockage("2","5",6);
        wp.addBlockage("zero","one",1);
        wp.addBlockage("zero","two",7);
        wp.addBlockage("one","two",5);
        wp.addBlockage("one","four",4);
        wp.addBlockage("one","five",3);
        wp.addBlockage("five","four",2);
        wp.addBlockage("two","five",6);

        wp.addSecondInputPump("zero");

        assertEquals(5,wp.minAmount(),0);
    }
    @Test
    public void smallScaleTest2(){
        String initialInputPump = "A";
        final WaterPressureBase wp = new WaterPressure ( initialInputPump );
        wp.addBlockage("A","B",6);
        wp.addBlockage("B","A",6);

        wp.addBlockage("A","C",8);
        wp.addBlockage("C","A",8);

        wp.addBlockage("B","C",3);
        wp.addBlockage("C","B",3);

        wp.addBlockage("C","F",3);
        wp.addBlockage("F","C",3);

        wp.addBlockage("C","D",4);
        wp.addBlockage("D","C",4);

        wp.addBlockage("B","F",5);
        wp.addBlockage("F","B",5);

        wp.addBlockage("B","D",6);
        wp.addBlockage("D","B",6);

        wp.addBlockage("F","D",2);
        wp.addBlockage("D","F",2);

        wp.addBlockage("F","E",2);
        wp.addBlockage("E","F",2);

        wp.addBlockage("D","E",5);
        wp.addBlockage("E","D",5);

        assertEquals(6,wp.minAmount(),0);
    }
    @Test
    public void smallScaleTest3() {
        String initialInputPump = "A";
        final WaterPressureBase wp = new WaterPressure (initialInputPump);
        wp.addBlockage("There","IsNoMST",4);
        assertEquals(-1,wp.minAmount(),0);
    }
    @Test
    public void binaryTreeTest(){
        int levels = 500000;
        WaterPressureBase wp = generateBinaryTree(levels);
        assertEquals(2*levels+1,wp.minAmount(),0);
    }


    public WaterPressureBase generateBinaryTree(int levels) {
        String startVertex = "1";
        WaterPressureBase wp = new WaterPressure(startVertex);
        for (int i = 1; i <= levels; i++) {
            int newLevel = 2 * i;
            wp.addBlockage(String.valueOf(i), String.valueOf(newLevel), newLevel);
            wp.addBlockage(String.valueOf(i), String.valueOf(newLevel + 1), newLevel+1);
        }
        return wp;
    }
    @Test
    public void errorTesting() {
        String startingVertex = "StartVertex";
        final WaterPressureBase wp = new WaterPressure(startingVertex);
        wp.addBlockage(startingVertex, "a", 3);
        wp.addBlockage(startingVertex, "b", 2);
        wp.addBlockage("a", "b", 1);
        wp.addBlockage("sca", "afds", 2);
        wp.minAmount();


    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest1() {
        //The two vertices must differ from one another
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("v", "v", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest2() {
        //The two vertices must differ from one another
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("TESTING", "TESTING", 3);
    }


    @Test(expected = IllegalArgumentException.class)
    public void validationTest4() {
        //an edge between the two  vertices cannot have been added previously.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("TESTING", "v", 3);
        wp.addBlockage("TESTING", "v", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest5() {
        //an edge between the two  vertices cannot have been added previously.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("TESTING", "v", 3);
        wp.addBlockage("TESTING", "v", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest6() {
        //specifies a vertex, length must be > 0.y.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("", "v", 3);
        wp.addBlockage("TESTING", "gh", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest7() {
        //specifies a vertex, length must be > 0.y.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("d", "v", 3);
        wp.addBlockage("TESTING", "", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest8() {
        //specifies a vertex, length must be > 0.y.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("", "", 3);
        wp.addBlockage("", "", 4);
    }
    @Test(expected = IllegalStateException.class)
    public void validationTest9() {
        //id Doit has been previously invoked IllegalStateException
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("d", "v", 3);
        wp.minAmount();
        wp.addBlockage("u", "v", 3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest10() {
        //specifies a vertex, length must be > 0.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("v","w",0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest11() {
        //specifies a vertex, length must be > 0.
        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("v","w",-1);
    }

    @Test
    public void validationTest13() {
        /**
         * Returns the cost (sum of the edge weights) of the longest valid path if
         * one exists, 0.0 otherwise.
         *
         * @return the cost if the graph contains a longest valid path, 0.0
         * otherwise.
         */

        WaterPressureBase wp = new WaterPressure("TESTING");
        wp.addBlockage("v","w",3);
        wp.minAmount();
        assertEquals(-1,wp.minAmount(),0);
    }

}