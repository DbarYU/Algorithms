package edu.yu.da;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BeatThePricingSchemesTest {
    public class SchemesTest{
        private int quantity;
        private double price;
        private int num;
        public SchemesTest(double price,int quantity,int num){
            this.price = price;
            this.quantity = quantity;
            this.num =num;
        }

        @Override
        public String toString() {
            return "SchemesTest{" +
                    "quantity=" + quantity +
                    ", price=" + price +
                    ", num=" + num +
                    '}';
        }
    }
    public class BruteForcePricingSchemes {
        private List<List<SchemesTest>> allPermutations;

        private List<List<SchemesTest>> returnedSchemes;
        private double smallestPrice;
        public BruteForcePricingSchemes(List<SchemesTest> schemes, int quantity){
            this.allPermutations = new ArrayList<>();
            for(int i =0; i<schemes.size(); i++) {
                ArrayList<SchemesTest> temp = new ArrayList<>();
                temp.add(schemes.get(i));
                combine(quantity - schemes.get(i).quantity, schemes, temp);
            }
            smallestPrice = Double.MAX_VALUE;
            for(List<SchemesTest> currentScheme: this.allPermutations){
                double currentPrice = 0;
                for(SchemesTest price: currentScheme){
                    currentPrice += price.price;
                }
                if(smallestPrice > currentPrice) {
                    returnedSchemes = new ArrayList<>();
                    returnedSchemes.add(currentScheme);
                    smallestPrice = currentPrice;
                }
                if(smallestPrice == currentPrice)
                    returnedSchemes.add(currentScheme);
            }
        }

        private void combine(int quantity, List<SchemesTest> elements,List<SchemesTest> currentSchemes) {
            if(quantity <= 0)
                this.allPermutations.add(currentSchemes);
            else {
                for(int i =0; i< elements.size(); i++){
                    ArrayList<SchemesTest> temp = new ArrayList<>(currentSchemes);
                    temp.add(elements.get(i));
                    combine(quantity - elements.get(i).quantity,elements,temp);
                }

            }
        }
    }
    @Test
    public void initialTest(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(10,9);
        beatThePricingScheme.addPricingScheme(8,7);
        Assert.assertEquals(10,beatThePricingScheme.cheapestPrice(8),0);


    }
    @Test
    public void smallTest(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(10,9);
        Assert.assertEquals(10,beatThePricingScheme.cheapestPrice(9),0);
        List<Integer> expected = List.of(1);
        Assert.assertEquals(expected,beatThePricingScheme.optimalDecisions());
    }
    @Test
    public void smallTest3(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(10,9);
        Assert.assertEquals(8,beatThePricingScheme.cheapestPrice(4),0);
        List<Integer> expected = List.of(0,0,0,0);
        Assert.assertEquals(expected,beatThePricingScheme.optimalDecisions());
    }
    @Test
    public void initialTest2(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(10,9);
        beatThePricingScheme.addPricingScheme(8,7);
        Assert.assertEquals(10,beatThePricingScheme.cheapestPrice(9),0);

    }
    @Test
    public void initialTest3(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(8,7);
        beatThePricingScheme.addPricingScheme(10,9);
        beatThePricingScheme.addPricingScheme(11,26);
        Assert.assertEquals(10,beatThePricingScheme.cheapestPrice(9),0);
        List<Integer> expected = List.of(2);
        Assert.assertEquals(expected,beatThePricingScheme.optimalDecisions());
    }
    @Test
    public void professorTest1(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(8.75,5);
        Assert.assertEquals(8,beatThePricingScheme.cheapestPrice(4),0);
        List<Integer> expected = List.of(0,0,0,0);
        Assert.assertEquals(expected,beatThePricingScheme.optimalDecisions());
    }
    @Test
    public void professorTest2(){
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(2);
        beatThePricingScheme.addPricingScheme(8.75,5);
        Assert.assertEquals(10.75,beatThePricingScheme.cheapestPrice(6),0);
        List<Integer> expected = List.of(0,1);
        Assert.assertEquals(expected,beatThePricingScheme.optimalDecisions());
    }
    @Test
    public void testingOptimalDecisions(){
        int threshold = 43;
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(3);
        beatThePricingScheme.addPricingScheme(20,14);
        beatThePricingScheme.addPricingScheme(28,24);
        beatThePricingScheme.addPricingScheme(39,32);
        beatThePricingScheme.addPricingScheme(25,19);
        beatThePricingScheme.addPricingScheme(37,31);
        beatThePricingScheme.addPricingScheme(9,7);

        List<SchemesTest> schemesTest = new ArrayList<>();
        schemesTest.add(new SchemesTest(3,1,0));
        schemesTest.add(new SchemesTest(20,14,1));
        schemesTest.add(new SchemesTest(28,24,2));
        schemesTest.add(new SchemesTest(39,32,3));
        schemesTest.add(new SchemesTest(25,19,4));
        schemesTest.add(new SchemesTest(37,31,5));
        schemesTest.add(new SchemesTest(9,7,6));
        BruteForcePricingSchemes bruteForcePricingSchemes = new BruteForcePricingSchemes(schemesTest,threshold);




    }
    @Test
    public void initialTest4(){
        int threshold = 53;
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(3);
        beatThePricingScheme.addPricingScheme(20,14);
        beatThePricingScheme.addPricingScheme(28,24);
        beatThePricingScheme.addPricingScheme(39,32);
        beatThePricingScheme.addPricingScheme(25,19);
        beatThePricingScheme.addPricingScheme(37,31);
        beatThePricingScheme.addPricingScheme(9,7);

        List<SchemesTest> schemesTest = new ArrayList<>();
        schemesTest.add(new SchemesTest(3,1,0));
        schemesTest.add(new SchemesTest(20,14,1));
        schemesTest.add(new SchemesTest(28,24,2));
        schemesTest.add(new SchemesTest(39,32,3));
        schemesTest.add(new SchemesTest(25,19,4));
        schemesTest.add(new SchemesTest(37,31,5));
        schemesTest.add(new SchemesTest(9,7,6));

        for(int i= 1; i<= threshold; i++){
            BruteForcePricingSchemes bruteForcePricingSchemes = new BruteForcePricingSchemes(schemesTest,i);
            Assert.assertEquals(bruteForcePricingSchemes.smallestPrice,beatThePricingScheme.cheapestPrice(i),0);
        }

    }
    @Test
    public void initialTest5(){
        int threshold = 53;
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(3);
        beatThePricingScheme.addPricingScheme(20,14);
        beatThePricingScheme.addPricingScheme(28,24);
        beatThePricingScheme.addPricingScheme(39,32);
        beatThePricingScheme.addPricingScheme(25,19);
        beatThePricingScheme.addPricingScheme(37,31);
        beatThePricingScheme.addPricingScheme(9,7);

        List<SchemesTest> schemesTest = new ArrayList<>();
        schemesTest.add(new SchemesTest(3,1,0));
        schemesTest.add(new SchemesTest(20,14,1));
        schemesTest.add(new SchemesTest(28,24,2));
        schemesTest.add(new SchemesTest(39,32,3));
        schemesTest.add(new SchemesTest(25,19,4));
        schemesTest.add(new SchemesTest(37,31,5));
        schemesTest.add(new SchemesTest(9,7,6));

        for(int i= threshold; i> 0; i--){
            BruteForcePricingSchemes bruteForcePricingSchemes = new BruteForcePricingSchemes(schemesTest,i);
            Assert.assertEquals(bruteForcePricingSchemes.smallestPrice,beatThePricingScheme.cheapestPrice(i),0);
        }

    }
    @Test
    public void randomizedTest()
    {

        int numOfPriceSchemes = 8;
        double[]  prices = generatePrices(numOfPriceSchemes);
        int[] quantities = generateQuantities(numOfPriceSchemes);
        BeatThePricingSchemesBase beatThePricingScheme = getBeatThePricingSchemesBase(prices, quantities);

        List<SchemesTest> schemesTest = new ArrayList<>();
        schemesTest.add(new SchemesTest(4.5,1,0));
        schemesTest.add(new SchemesTest(prices[0],quantities[0],1));
        schemesTest.add(new SchemesTest(prices[1],quantities[1],2));
        schemesTest.add(new SchemesTest(prices[2],quantities[2],3));
        schemesTest.add(new SchemesTest(prices[3],quantities[3],4));
        schemesTest.add(new SchemesTest(prices[4],quantities[4],5));
        schemesTest.add(new SchemesTest(prices[5],quantities[5],6));

        for(int i = 15; i>0; i--) {
            BruteForcePricingSchemes bruteForcePricingSchemes = new BruteForcePricingSchemes(schemesTest, i);
            Assert.assertEquals(bruteForcePricingSchemes.smallestPrice, beatThePricingScheme.cheapestPrice(i), 0);
        }

    }

    private static BeatThePricingSchemesBase getBeatThePricingSchemesBase(double[] prices, int[] quantities) {
        BeatThePricingSchemesBase beatThePricingScheme = new BeatThePricingSchemes(4.5);
        beatThePricingScheme.addPricingScheme(prices[0], quantities[0]);
        beatThePricingScheme.addPricingScheme(prices[1], quantities[1]);
        beatThePricingScheme.addPricingScheme(prices[2], quantities[2]);
        beatThePricingScheme.addPricingScheme(prices[3], quantities[3]);
        beatThePricingScheme.addPricingScheme(prices[4], quantities[4]);
        beatThePricingScheme.addPricingScheme(prices[5], quantities[5]);
        return beatThePricingScheme;
    }

    private int[] generateQuantities(int quantities) {
        Random rand = new Random();
        int[] allQuantities = new int[quantities];
        for(int i =0; i<quantities; i++){
            int low = 2;
            int high = 15;
            allQuantities[i] = rand.nextInt(high-low) + low;

        }
        return allQuantities;
    }

    private double[] generatePrices(int numOfPrices){
        double [] prices = new double[numOfPrices];
        for(int i = 0; i<numOfPrices; i++){
            double startPrice = 5;
            double endPrice = 30;
            double random = new Random().nextDouble();
            double price = startPrice + (random * (endPrice - startPrice));
            prices[i] = price;
        }
        return prices;

    }




    /** Adds a pricing scheme to be considered when making the "select optimal
     * pricing schemes" decision.
     *
     * @param price the price to be paid for the specified quantity, must be
     * greater than 0.
     * @param quantity, which for the sake of DP, cannot exceed MAX_MATZOS and
     * must be greater than zero.
     * @throw IllegalArgumentException if the parameter pre-conditions are violated.
     * @see MAX_SCHEMES
     * addPricingScheme
     */
    @Test(expected = IllegalArgumentException.class)
    public void validateTest1(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        beatThePricingSchemesBase.addPricingScheme(0,4);

    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest2(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        beatThePricingSchemesBase.addPricingScheme(-1,4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest3(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        beatThePricingSchemesBase.addPricingScheme(1,101);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest4(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        beatThePricingSchemesBase.addPricingScheme(1,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest5(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        beatThePricingSchemesBase.addPricingScheme(1,-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest6(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        for(int i = 1; i<=21; i++){
            beatThePricingSchemesBase.addPricingScheme(i,i);
        }
    }
    @Test
    public void validateTest7(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(3);
        for(int i = 1; i<=20; i++){
            beatThePricingSchemesBase.addPricingScheme(i,i);
        }
    }
    /** Constructor: client specifies the price of a single quantity of the
     * desired item.
     *
     * @param unitPrice the price-per-single-unit, must be greater than 0.
     * @throw IllegalArgumentException if the parameter pre-conditions are
     * violated.
     */
    @Test(expected = IllegalArgumentException.class)
    public void validateTest8(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest9(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(-1);
    }
    /** Returns the cheapest price needed to buy at least threshold items.  Thus
     * the quantity bought may exceed the threshold, as long as that is the
     * cheapest price for threshold number of items given the current set of
     * price schemas.
     *
     * @param threshold the minimum number of items to be purchased, cannot
     * exceed MAX_MATZOS, and must be greater than zero.
     * @return the cheapest price required to purchase at least the threshold
     * quantity.
     * @throw IllegalArgumentException if the parameter pre-conditions are violated.
     * @see MAX_MATZOS
     * cheapestPrice
     */
    @Test(expected = IllegalArgumentException.class)
    public void validateTest10(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.cheapestPrice(BeatThePricingSchemesBase.MAX_MATZOS +1 );
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest11(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.cheapestPrice(0 );
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateTest12(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.cheapestPrice(-1 );
    }
    @Test(expected = IllegalStateException.class)
    public void validateTest13Test(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.optimalDecisions();
    }
    @Test(expected = IllegalStateException.class)
    public void validateTest14Test(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.cheapestPrice(5);
        beatThePricingSchemesBase.addPricingScheme(5,5);
        beatThePricingSchemesBase.optimalDecisions();
    }
    @Test(expected = IllegalStateException.class)
    public void validateTest15Test(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.addPricingScheme(5,5);
        beatThePricingSchemesBase.cheapestPrice(5);
        beatThePricingSchemesBase.addPricingScheme(7,7);
        beatThePricingSchemesBase.optimalDecisions();
    }
    @Test(expected = IllegalStateException.class)
    public void validateTest16Test(){
        BeatThePricingSchemesBase beatThePricingSchemesBase = new BeatThePricingSchemes(2);
        beatThePricingSchemesBase.addPricingScheme(7,7);
        beatThePricingSchemesBase.optimalDecisions();
    }




}