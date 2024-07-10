import edu.yu.da.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class PickayeshivaTest {
    private class Yeshivas  {
        private class Yeshiva implements Comparable<Yeshiva> {
            private double cookingRanking;
            private double ratioRanking;
            public Yeshiva(double ratioRanking,double cookingRanking){
                this.ratioRanking = ratioRanking;
                this.cookingRanking = cookingRanking;
            }

            @Override
            public int compareTo(Yeshiva o) {
                return Double.compare(ratioRanking,o.ratioRanking);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Yeshiva yeshiva = (Yeshiva) o;
                return Double.compare(cookingRanking, yeshiva.cookingRanking) == 0 && Double.compare(ratioRanking, yeshiva.ratioRanking) == 0;
            }

            @Override
            public int hashCode() {
                return Objects.hash(cookingRanking, ratioRanking);
            }
        }
        private final List<Yeshiva> yeshivas;
        public Yeshivas(double[] ratioRankings,double[] cookingRankings){
            yeshivas = new ArrayList<>();
            for(int i=0; i<ratioRankings.length; i++){
                yeshivas.add(new Yeshiva(ratioRankings[i],cookingRankings[i]));
            }
            Collections.sort(yeshivas);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Yeshivas yeshivas1 = (Yeshivas) o;
            return yeshivas.equals(yeshivas1.yeshivas);
        }

        @Override
        public int hashCode() {
            return Objects.hash(yeshivas);
        }
    }

    @Test
    public void professorTest(){
        final double [] facultyRatioRankings = {0,1,2};
        final double [] cookingRankings = {3,2,1};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        final Yeshivas expectedYeshivas = new Yeshivas(facultyRatioRankings,cookingRankings);
        final Yeshivas actualYeshivas = new Yeshivas(pay.getFacultyRatioRankings(), pay.getCookingRankings());
        Assert.assertEquals(expectedYeshivas,actualYeshivas);



    }
    @Test
    public void smallScaleTest1(){
        final double [] facultyRatioRankings = {3,1,2,1};
        final double [] cookingRankings = {3,2,1,2};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        double[] expected = new double[]{3};
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
    }
    @Test
    public void smallScaleTest2(){
        final double [] facultyRatioRankings = {3,1,2,1,5,9,12,13,38};
        final double [] cookingRankings =      {3,2,41,20,19,7,5,18,4};

        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        final Yeshivas expectedYeshivas = new Yeshivas(new double[]{2,5,13,38},new double[]{41,19,18,4});
        final Yeshivas actualYeshivas = new Yeshivas(pay.getFacultyRatioRankings(), pay.getCookingRankings());
        Assert.assertEquals(expectedYeshivas,actualYeshivas);


    }
    @Test
    public void smallScaleTest4(){
        final double [] facultyRatioRankings = {1,2,3,3};
        final double [] cookingRankings = {4,3,2,1};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final Yeshivas expectedYeshivas =  new Yeshivas(new double[]{1,2,3},new double[]{4,3,2});
        final Yeshivas actualYeshivas = new Yeshivas(pay.getFacultyRatioRankings(), pay.getCookingRankings());
        Assert.assertEquals(expectedYeshivas,actualYeshivas);

    }
    @Test
    public void smallScaleTest5(){
        final double [] cookingRankings =           {3,1,2,1,5,9,12,13,38};
        final double [] facultyRatioRankings =      {3,2,41,20,19,7,5,18,4};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings =     {2,5,13,38};
        final double []expectedFacultyRatioRankings = {41,19,18,4};
        final Yeshivas expectedYeshivas = new Yeshivas(expectedFacultyRatioRankings,expectedCookingRankings);
        final Yeshivas actualYeshivas = new Yeshivas(pay.getFacultyRatioRankings(),pay.getCookingRankings());
        Assert.assertEquals(expectedYeshivas,actualYeshivas);

    }

    @Test
    public void largeScaleTest1(){
        int scalar = 67108864;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =0; i<scalar; i++){
            facultyRatioRankings[i] = i;
            cookingRankings[i] = i;
        }
        double[] expected = new double[]{scalar-1};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void largeScaleTest2(){
        int scalar = 67108864;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =1; i<=scalar; i++){
            facultyRatioRankings[i-1] = scalar-i;
            cookingRankings[i-1] = i;
        }
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(cookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(facultyRatioRankings,pay.getFacultyRatioRankings(),0);
    }

    @Test
    public void largeScaleTest3(){
        int scalar = 67108864;
        float min = 0;
        int largestNumIndex = (int) (Math.random() * (scalar - min) + min);

        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =1; i<=scalar; i++){
            if(largestNumIndex == i){
                facultyRatioRankings[i-1] = scalar+1;
                cookingRankings[i-1] = scalar+1;
            }else {
                facultyRatioRankings[i - 1] = scalar - i;
                cookingRankings[i - 1] = i;
            }
        }
        double[] expected = new double[]{scalar+1};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void largeScaleTest4(){
        int scalar = 67108864;
        float min = 0;
        int largestNumIndex = (int) (Math.random() * (scalar - min) + min);

        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =1; i<=scalar; i++){
            if(largestNumIndex == i){
                facultyRatioRankings[i-1] = scalar+1;
                cookingRankings[i-1] = scalar+1;
            }else {
                facultyRatioRankings[i - 1] = scalar - i;
                cookingRankings[i - 1] = i;
            }
        }
        double[] expected = new double[]{scalar+1};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void largeScaleTest5(){
        int scalar = 67108864;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =1; i<=scalar; i++){
            cookingRankings[i-1] = scalar-i;
            facultyRatioRankings[i-1] = i;

        }
        double[] reversedArray1 = Arrays.copyOf(facultyRatioRankings, facultyRatioRankings.length);
        for (int i = 0, j = facultyRatioRankings.length - 1; i < j; i++, j--) {
            double temp = reversedArray1[i];
            reversedArray1[i] = reversedArray1[j];
            reversedArray1[j] = temp;
        }
        double[] reversedArray2 = Arrays.copyOf(cookingRankings, cookingRankings.length);
        for (int i = 0, j = cookingRankings.length - 1; i < j; i++, j--) {
            double temp = reversedArray2[i];
            reversedArray2[i] = reversedArray2[j];
            reversedArray2[j] = temp;
        }

        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(reversedArray1,pay.getFacultyRatioRankings(),0);
        Assert.assertArrayEquals(reversedArray2,pay.getCookingRankings(),0);



    }
    @Test
    public void smallScaleTest6(){
        final double [] cookingRankings=           {3,1,39,1,5,9,12,13,38};
        final double [] facultyRatioRankings=      {3,2,41,20,19,7,5,18,4};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {39};
        final double []expectedFacultyRatioRankings = {41};
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest1(){
        PickAYeshivaBase pay = new PickAYeshiva(null,new double[]{1});
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest2(){
        PickAYeshivaBase pay = new PickAYeshiva(new double[]{1},null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest3(){
        PickAYeshivaBase pay = new PickAYeshiva(null,null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest4(){
        PickAYeshivaBase pay = new PickAYeshiva(new double[]{1,2,3,4},new double[]{1,2,3});
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest5(){
        PickAYeshivaBase pay = new PickAYeshiva(new double[]{},new double[]{});
    }
    @Test
    public void clientMaintainsOwnership(){
        int scalar = 50;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        final double[] facultyRatioRankingsTemp = new double[scalar];
        final double[] cookingRankingsTemp = new double[scalar];
        for (int i =0; i<scalar; i++){
            facultyRatioRankings[i] = i;
            cookingRankings[i] = i;
            facultyRatioRankingsTemp[i] = i;
            cookingRankingsTemp[i] = i;
        }
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(cookingRankingsTemp,cookingRankings,0);
        Assert.assertArrayEquals(facultyRatioRankingsTemp,facultyRatioRankings,0);
    }
    @Test
    public void smallScaleTest7(){
        final double [] cookingRankings=           {38,40};
        final double [] facultyRatioRankings=      {41,41};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {40};
        final double []expectedFacultyRatioRankings = {41};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void smallScaleTest8(){
        final double [] cookingRankings=       {41,41};
        final double [] facultyRatioRankings=     {38,40};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {41};
        final double []expectedFacultyRatioRankings = {40};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void largeScaleTest6(){
        int scalar = 67108864;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i = 1; i<=scalar; i++){
            cookingRankings[i-1] = scalar;
            facultyRatioRankings[i-1] = i;
        }
        final double[] expected = new double[]{scalar};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);

    }
    @Test
    public void largeScaleTest7(){
        int scalar = 67108864;
        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i = 1; i<=scalar; i++){
            facultyRatioRankings[i-1] = scalar;
            cookingRankings[i-1] = i;
        }
        final double[] expected = new double[]{scalar};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void largeScaleTest8(){
        int scalar = 67108864;
        float min = 0;
        int largestNumIndex = (int) (Math.random() * (scalar - min) + min);
        int maxNum = 0;

        final double[] facultyRatioRankings = new double[scalar];
        final double[] cookingRankings = new double[scalar];
        for (int i =1; i<=scalar; i++){
            if(largestNumIndex == i){
                facultyRatioRankings[i-1] = scalar+i;
                cookingRankings[i-1] = scalar+i;
                maxNum = i;
            }else {
                facultyRatioRankings[i - 1] = scalar - i;
                cookingRankings[i - 1] = i;
            }
        }
        double[] expected = new double[]{scalar+maxNum};
        PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);
        Assert.assertArrayEquals(expected,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expected,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void smallScaleTest9(){
        final double [] cookingRankings= {10,5,6};
        final double [] facultyRatioRankings= {1,5,6};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {6,10};
        final double []expectedFacultyRatioRankings = {6,1};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void smallScaleTest10(){
        final double [] cookingRankings= {1,6,5};
        final double [] facultyRatioRankings= {10,6,5};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {1,6};
        final double []expectedFacultyRatioRankings = {10,6};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);
    }
    @Test
    public void smallScaleTest11(){
        final double [] cookingRankings= {6,5};
        final double [] facultyRatioRankings= {5,5};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {6};
        final double []expectedFacultyRatioRankings = {5};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);

    }
    @Test
    public void smallScaleTest12(){
        final double [] cookingRankings= {5,5};
        final double [] facultyRatioRankings= {6,5};
        final PickAYeshivaBase pay = new PickAYeshiva(facultyRatioRankings,cookingRankings);

        final double [] expectedCookingRankings = {5};
        final double []expectedFacultyRatioRankings = {6};
        Assert.assertArrayEquals(expectedCookingRankings,pay.getCookingRankings(),0);
        Assert.assertArrayEquals(expectedFacultyRatioRankings,pay.getFacultyRatioRankings(),0);

    }



}
