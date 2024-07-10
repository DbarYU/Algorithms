import edu.yu.da.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpheresOfInfluenceTest {
    @Test
    public void firstTest(){

        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(10,16);
        spheresOfInfluenceBase.addInfluencer("a",3,6);
        spheresOfInfluenceBase.addInfluencer("b",9,7);
        spheresOfInfluenceBase.addInfluencer("c",15,6);
        spheresOfInfluenceBase.addInfluencer("d",3,2);
        Assert.assertEquals(List.of("a","b","c"),spheresOfInfluenceBase.getMinimalCoverageInfluencers());

    }
    @Test
    public void secondTest(){

        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(12,16);
        spheresOfInfluenceBase.addInfluencer("a",3,6);
        spheresOfInfluenceBase.addInfluencer("b",9,7);
        spheresOfInfluenceBase.addInfluencer("c",15,6);
        spheresOfInfluenceBase.addInfluencer("d",3,2);
        Assert.assertEquals(Collections.emptyList(),spheresOfInfluenceBase.getMinimalCoverageInfluencers());

    }
    @Test
    public void professorTest(){
    SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(2,10);
    spheresOfInfluenceBase.addInfluencer("A",2,3);
    spheresOfInfluenceBase.addInfluencer("B",6,5);
    List<String> solution = List.of("A","B");
    Assert.assertEquals(solution,spheresOfInfluenceBase.getMinimalCoverageInfluencers());
}
    @Test public void largeScaleTest(){
        int scale = 80000;
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,scale);
        for(int i =0; i<scale;i++){
            spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,51);
        }
        Assert.assertEquals(Math.floorDiv(scale,20),spheresOfInfluenceBase.getMinimalCoverageInfluencers().size());
    }
    @Test public void largeScaleTest2(){
        int scale = 8000;
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,scale);
        for(int i =0; i<scale;i++){
            spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,50);
        }
        Assert.assertEquals(Collections.emptyList(),spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }
    @Test
    public void firstRealTest(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(10,10);

        spheresOfInfluenceBase.addInfluencer("A",0,5);
        spheresOfInfluenceBase.addInfluencer("B",3,6);
        spheresOfInfluenceBase.addInfluencer("C",4,6);
        spheresOfInfluenceBase.addInfluencer("D",7,6);
        List<String> solution = List.of("B","D");
        Assert.assertEquals(solution,spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }
    @Test
    public void test(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("A",1,100000);
        List<String> solution = List.of("A");
        Assert.assertEquals(solution,spheresOfInfluenceBase.getMinimalCoverageInfluencers());

    }

    @Test
    public void checkTest(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,100);

        spheresOfInfluenceBase.addInfluencer("A",30,51);
        spheresOfInfluenceBase.addInfluencer("B",50,51);
        spheresOfInfluenceBase.addInfluencer("C",70,51);
        spheresOfInfluenceBase.addInfluencer("D",90,51);
        Assert.assertEquals(Collections.emptyList(),spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }

    @Test
    public void checkTest2(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,100);
        spheresOfInfluenceBase.addInfluencer("A",10,51);
        spheresOfInfluenceBase.addInfluencer("B",30,51);
        spheresOfInfluenceBase.addInfluencer("C",50,51);
        spheresOfInfluenceBase.addInfluencer("D",70,51);
        spheresOfInfluenceBase.addInfluencer("E",90,51);
        int scalar = 100000;
        for(int i = 0; i < scalar; i++){
            if(i != 10 && i != 30 && i!=50 && i != 70 && i != 90)
                spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,49);
        }

        Assert.assertEquals(List.of("A","B","C","D","E"),spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }

    @Test
    public void smallTest(){
     SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,1000);

     spheresOfInfluenceBase.addInfluencer("A",5,1000);
     List<String> expected = List.of("A");
     Assert.assertEquals(expected,spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }
    @Test
    public void smallTest2(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,1000);

        spheresOfInfluenceBase.addInfluencer("A",5,996);
        Assert.assertEquals(Collections.emptyList(),spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }
    @Test
    public void smallTest3(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,1000);

        spheresOfInfluenceBase.addInfluencer("A",5,997);
        for(int i =0; i<100000; i++){
            if(i != 5 && i != 997)
                spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,997);
        }

        Assert.assertEquals(1,spheresOfInfluenceBase.getMinimalCoverageInfluencers().size());
    }
    @Test
    public void smallTest4(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,1000);

        spheresOfInfluenceBase.addInfluencer("A",5,997);
        for(int i =0; i<1000; i++){
            spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,996);
        }
        Assert.assertEquals(1,spheresOfInfluenceBase.getMinimalCoverageInfluencers().size());
    }
    @Test
    public void smallTest5(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(100,1000);

        spheresOfInfluenceBase.addInfluencer("A",5,997);
        for(int i =0; i<1000; i++){
            spheresOfInfluenceBase.addInfluencer(String.valueOf(i),i,996);
        }
        Assert.assertEquals(1,spheresOfInfluenceBase.getMinimalCoverageInfluencers().size());
    }

    /** Constructor that defines the MU rectangular 2D plane of student values.
     *
     * maxStrength the maximum "strength" value demarcating the MU 2D
     * plane in one dimension, must be greater than 0.
     * maxRight the maximum "right religiosity" value (in MU's "left to
     * right" spectrum) demarcating the MU 2D plane in the other dimension, must
     * be greater than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void validationTest1(){
       SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(-1,5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest2(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,-5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest3(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(-1,-5);
    }
    /** Specifies the two characteristics of an influencer.
     *
     *  id uniquely identifies the influencer, must be non-empty.
     *  xValue the influencer's position on the "right-to-left" spectrum,
     * represents the center of the influencer's sphere of influence.  The
     * influencer's "strength" value is in the center of the MU rectangular 2D
     * plane.  Must be a non-negative integer.
     *  radius demarcates the extent of the influencer's influence, must be
     * greater than 0.
     *  IllegalArgumentException if the Javadoc constraints are violated,
     * including if an influencer with this id has previously been added or if an
     * influencer with a duplicate xValue and radius values has previously been
     * added.
     */
    @Test(expected = IllegalArgumentException.class)
    public void validationTest4(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("",5,5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest5(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("TEST",-1,5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest6(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("TEST",1,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest7(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("TEST",1,-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest8(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("TEST",1,1);
        spheresOfInfluenceBase.addInfluencer("TEST",1,1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest9(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        spheresOfInfluenceBase.addInfluencer("TEST2",1,1);
        spheresOfInfluenceBase.addInfluencer("TEST1",1,1);
    }

    @Test
    public void edgeCase(){
        SpheresOfInfluenceBase spheresOfInfluenceBase = new SpheresOfInfluence(1,1);
        Assert.assertEquals(Collections.emptyList(),spheresOfInfluenceBase.getMinimalCoverageInfluencers());
    }

}