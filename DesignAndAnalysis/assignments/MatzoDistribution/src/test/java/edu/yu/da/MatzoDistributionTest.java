import edu.yu.da.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class MatzoDistributionTest {


    String source = "Source";
    String sink = "Sink";
    String vertex0 = "vertex0";
    String vertex1 = "vertex1";
    String vertex2 = "vertex2";
    String vertex3 = "vertex3";
    String vertex4 = "vertex4";
    String vertex5 = "vertex5";
    String vertex6 = "vertex6";
    String vertex7 = "vertex7";
    String vertex8 = "vertex8";


    @Test
    public void initialTest1(){
        String source = "A";
        String sink = "C";
        int constraints = 5;

        MatzoDistributionBase mdb = new MatzoDistribution(source,constraints,sink);
        mdb.addWarehouse("B",constraints);
        mdb.roadExists(source,"B",5);
        mdb.roadExists("B",sink,5);
        Assert.assertEquals(5,mdb.max());
    }
    @Test
    public void initialTest2(){
        String source = "A";
        String sink = "C";
        int constraints = 5;

        MatzoDistributionBase mdb = new MatzoDistribution(source,constraints+constraints,sink);
        mdb.addWarehouse("B",constraints);
        mdb.roadExists(source,"B",5);
        mdb.roadExists("B",sink,5);
        Assert.assertEquals(5,mdb.max());
        mdb.addWarehouse("D",5);
        mdb.roadExists(source,"D",5);
        mdb.roadExists("D",sink,5);
        Assert.assertEquals(10,mdb.max());
    }
    @Test
    public void initialTest3(){
        String source = "Source";
        String sink = "Sink";
        String vertex1 = "vertex1";
        String vertex2 = "vertex2";


        MatzoDistributionBase mdb = new MatzoDistribution(source,18,sink);
        mdb.addWarehouse(vertex1,10);
        mdb.addWarehouse(vertex2,15);
        mdb.roadExists(source,vertex1,24);
        mdb.roadExists(source,vertex2,32);
        mdb.roadExists(vertex2,vertex1,24);
        mdb.roadExists(vertex1,sink,32);
        mdb.roadExists(vertex2,sink,24);

        Assert.assertEquals(18,mdb.max());
    }
    @Test
    public void initialTest4(){
        String source = "Source";
        String sink = "Sink";
        String vertex1 = "vertex1";
        String vertex2 = "vertex2";
        String vertex3 = "vertex3";
        String vertex4 = "vertex4";



        MatzoDistributionBase mdb = new MatzoDistribution(source,23,sink);
        mdb.addWarehouse(vertex1,100);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);



        mdb.roadExists(source,vertex1,11);
        mdb.roadExists(source,vertex2,12);
        mdb.roadExists(vertex2,vertex1,1);
        mdb.roadExists(vertex2,vertex4,11);
        mdb.roadExists(vertex4,sink,4);
        mdb.roadExists(vertex1,vertex3,12);
        mdb.roadExists(vertex4,vertex3,7);
        mdb.roadExists(vertex3,sink,19);

        Assert.assertEquals(23,mdb.max());
    }

    @Test
    public void initialTest5(){
        String source = "Source";
        String sink = "Sink";
        String vertex1 = "vertex1";
        String vertex2 = "vertex2";
        String vertex3 = "vertex3";
        String vertex4 = "vertex4";



        MatzoDistributionBase mdb = new MatzoDistribution(source,10,sink);
        mdb.addWarehouse(vertex1,100);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);



        mdb.roadExists(source,vertex1,11);
        mdb.roadExists(source,vertex2,12);
        mdb.roadExists(vertex2,vertex1,1);
        mdb.roadExists(vertex2,vertex4,11);
        mdb.roadExists(vertex4,sink,4);
        mdb.roadExists(vertex1,vertex3,12);
        mdb.roadExists(vertex4,vertex3,7);
        mdb.roadExists(vertex3,sink,19);

        Assert.assertEquals(10,mdb.max());
    }
    @Test
    public void initialTest6(){
        String source = "Source";
        String sink = "Sink";
        String vertex1 = "vertex1";
        String vertex2 = "vertex2";
        String vertex3 = "vertex3";
        String vertex4 = "vertex4";



        MatzoDistributionBase mdb = new MatzoDistribution(source,20,sink);
        mdb.addWarehouse(vertex1,6);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);



        mdb.roadExists(source,vertex1,11);
        mdb.roadExists(source,vertex2,12);
        mdb.roadExists(vertex2,vertex1,1);

        mdb.roadExists(vertex2,vertex4,11);
        mdb.roadExists(vertex4,sink,4);
        mdb.roadExists(vertex1,vertex3,12);
        mdb.roadExists(vertex4,vertex3,7);
        mdb.roadExists(vertex3,sink,19);

        Assert.assertEquals(17,mdb.max());
    }
    @Test
    public void complextTest(){
        String source = "Source";
        String sink = "Sink";

        String vertex0 = "vertex0";
        String vertex1 = "vertex1";
        String vertex2 = "vertex2";
        String vertex3 = "vertex3";
        String vertex4 = "vertex4";
        String vertex5 = "vertex5";
        String vertex6 = "vertex6";
        String vertex7 = "vertex7";
        String vertex8 = "vertex8";




        MatzoDistributionBase mdb = new MatzoDistribution(source,20,sink);

        mdb.addWarehouse(vertex1,150);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);
        mdb.addWarehouse(vertex7,150);
        mdb.addWarehouse(vertex6,100);
        mdb.addWarehouse(vertex8,150);
        mdb.addWarehouse(vertex0,150);



        mdb.roadExists(source,vertex0,7);
        mdb.roadExists(source,vertex1,2);
        mdb.roadExists(source,vertex2,1);

        mdb.roadExists(vertex0,vertex3,2);
        mdb.roadExists(vertex0,vertex4,4);
        mdb.roadExists(vertex3,vertex6,7);
        mdb.roadExists(vertex3,vertex7,1);
        mdb.roadExists(vertex7,sink,3);
        mdb.roadExists(vertex6,sink,1);

        mdb.roadExists(vertex1,vertex4,5);
        mdb.roadExists(vertex1,vertex5,6);
        mdb.roadExists(vertex4,vertex5,8);
        mdb.roadExists(vertex4,vertex8,3);
        mdb.roadExists(vertex5,vertex8,3);
        mdb.roadExists(vertex8,sink,4);

        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex2,vertex7,8);
        Assert.assertEquals(7,mdb.max());
    }
    @Test
    public void complexTest2(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,20,this.sink);
        mdb.addWarehouse(vertex1,150);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(5,mdb.max());


    }
    @Test
    public void complexTest3(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,20,this.sink);
        mdb.addWarehouse(vertex1,2);
        mdb.addWarehouse(vertex2,150);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(4,mdb.max());


    }
    @Test
    public void complexTest4(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,20,this.sink);
        mdb.addWarehouse(vertex1,2);
        mdb.addWarehouse(vertex2,1);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(3,mdb.max());


    }
    @Test
    public void complexTest5(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,20,this.sink);
        mdb.addWarehouse(vertex1,5);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,1);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(4,mdb.max());


    }
    @Test
    public void complexTest6(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,5,this.sink);
        mdb.addWarehouse(vertex1,2);
        mdb.addWarehouse(vertex2,2);
        mdb.addWarehouse(vertex3,1);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(4,mdb.max());


    }
    @Test
    public void complexTest7(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,3,this.sink);
        mdb.addWarehouse(vertex1,2);
        mdb.addWarehouse(vertex2,2);
        mdb.addWarehouse(vertex3,1);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);

        mdb.roadExists(source,vertex1,4);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(vertex1,sink,2);
        mdb.roadExists(vertex1,vertex3,1);
        mdb.roadExists(vertex3,sink,2);
        mdb.roadExists(vertex1,vertex4,2);
        mdb.roadExists(vertex4,vertex3,1);
        mdb.roadExists(vertex4,vertex5,1);
        mdb.roadExists(vertex5,sink,1);
        mdb.roadExists(vertex2,vertex4,4);

        Assert.assertEquals(3,mdb.max());
    }
    @Test
    public void complexTest8(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,200);
        mdb.addWarehouse(vertex2,200);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);
        mdb.addWarehouse(vertex6,600);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);


        Assert.assertEquals(28,mdb.max());
    }

    @Test
    public void complexTest9(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,200);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);
        mdb.addWarehouse(vertex6,600);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);


        Assert.assertEquals(25,mdb.max());
    }
    @Test
    public void complexTest10(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,100);
        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);
        mdb.addWarehouse(vertex6,600);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(24,mdb.max());
    }
    @Test
    public void complexTest11(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,11);

        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,600);
        mdb.addWarehouse(vertex6,600);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(24,mdb.max());
    }
    @Test
    public void complexTest12(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,3,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,8);
        mdb.addWarehouse(vertex3,10);

        mdb.addWarehouse(vertex4,150);
        mdb.addWarehouse(vertex5,5);
        mdb.addWarehouse(vertex6,600);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(3,mdb.max());
    }
    @Test
    public void complexTest13(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,6);
        mdb.addWarehouse(vertex3,13);


        mdb.addWarehouse(vertex4,7);
        mdb.addWarehouse(vertex5,5);
        mdb.addWarehouse(vertex6,9);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(7+5+9,mdb.max());
    }
    @Test
    public void complexTest14(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,6);
        mdb.addWarehouse(vertex3,13);


        mdb.addWarehouse(vertex4,14);
        mdb.addWarehouse(vertex5,14);
        mdb.addWarehouse(vertex6,14);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(11+5+9,mdb.max());
    }
    @Test
    public void complexTest15(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,6);
        mdb.addWarehouse(vertex3,13);


        mdb.addWarehouse(vertex4,5);
        mdb.addWarehouse(vertex5,14);
        mdb.addWarehouse(vertex6,14);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(11+5+5+4,mdb.max());
    }
    @Test
    public void complexTest16(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,9);
        mdb.addWarehouse(vertex2,6);
        mdb.addWarehouse(vertex3,13);


        mdb.addWarehouse(vertex4,5);
        mdb.addWarehouse(vertex5,8);
        mdb.addWarehouse(vertex6,14);

        mdb.roadExists(source,vertex1,10);
        mdb.roadExists(source,vertex2,5);
        mdb.roadExists(source,vertex3,15);

        mdb.roadExists(vertex1,vertex4,9);
        mdb.roadExists(vertex1,vertex5,15);
        mdb.roadExists(vertex1,vertex2,4);

        mdb.roadExists(vertex2,vertex5,8);
        mdb.roadExists(vertex2,vertex3,4);

        mdb.roadExists(vertex3,vertex6,16);

        mdb.roadExists(vertex6,vertex2,6);
        mdb.roadExists(vertex6,sink,10);

        mdb.roadExists(vertex4,sink,10);
        mdb.roadExists(vertex4,vertex5,15);
        mdb.roadExists(vertex5,sink,10);
        mdb.roadExists(vertex5,vertex6,15);

        Assert.assertEquals(11+5+5+4-2,mdb.max());
    }
    @Test
    public void complexTest17(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,15);
        mdb.addWarehouse(vertex2,15);
        mdb.addWarehouse(vertex3,15);


        mdb.addWarehouse(vertex4,50);
        mdb.addWarehouse(vertex5,80);
        mdb.addWarehouse(vertex6,140);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);

        Assert.assertEquals(3,mdb.max());
        mdb.roadExists(vertex5,vertex4,3);

        Assert.assertEquals(6,mdb.max());
        mdb.roadExists(vertex5,sink,5);
        Assert.assertEquals(11,mdb.max());
        mdb.roadExists(vertex3,vertex5,7);
        Assert.assertEquals(11,mdb.max());
        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        Assert.assertEquals(13,mdb.max());
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(17,mdb.max());

    }
    @Test
    public void complexTest18(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,15);
        mdb.addWarehouse(vertex2,15);
        mdb.addWarehouse(vertex3,15);


        mdb.addWarehouse(vertex4,50);
        mdb.addWarehouse(vertex5,80);
        mdb.addWarehouse(vertex6,140);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);

        Assert.assertEquals(3,mdb.max());
        mdb.roadExists(vertex5,vertex4,3);

        Assert.assertEquals(6,mdb.max());
        mdb.roadExists(vertex5,sink,5);
        Assert.assertEquals(11,mdb.max());
        mdb.roadExists(vertex3,vertex5,7);
        Assert.assertEquals(11,mdb.max());
        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        Assert.assertEquals(13,mdb.max());
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(17,mdb.max());

    }
    @Test
    public void complexTest19(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,15);
        mdb.addWarehouse(vertex2,15);
        mdb.addWarehouse(vertex3,15);


        mdb.addWarehouse(vertex4,50);
        mdb.addWarehouse(vertex5,6);
        mdb.addWarehouse(vertex6,140);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);


        mdb.roadExists(vertex5,vertex4,3);


        mdb.roadExists(vertex5,sink,5);

        mdb.roadExists(vertex3,vertex5,7);

        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(3+5+2+1,mdb.max());
    }
    @Test
    public void complexTest20(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,15);
        mdb.addWarehouse(vertex2,15);
        mdb.addWarehouse(vertex3,15);


        mdb.addWarehouse(vertex4,3);
        mdb.addWarehouse(vertex5,6);
        mdb.addWarehouse(vertex6,140);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);


        mdb.roadExists(vertex5,vertex4,3);


        mdb.roadExists(vertex5,sink,5);

        mdb.roadExists(vertex3,vertex5,7);

        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(3+5+3,mdb.max());
    }
    @Test
    public void complexTest21(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,3);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,3);


        mdb.addWarehouse(vertex4,3);
        mdb.addWarehouse(vertex5,6);
        mdb.addWarehouse(vertex6,140);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);


        mdb.roadExists(vertex5,vertex4,3);


        mdb.roadExists(vertex5,sink,5);

        mdb.roadExists(vertex3,vertex5,7);

        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(3+5+3,mdb.max());
    }
    @Test
    public void complexTest22(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,3);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,3);


        mdb.addWarehouse(vertex4,3);
        mdb.addWarehouse(vertex5,6);
        mdb.addWarehouse(vertex6,2);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);


        mdb.roadExists(vertex5,vertex4,3);


        mdb.roadExists(vertex5,sink,5);

        mdb.roadExists(vertex3,vertex5,7);

        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(3+5+2,mdb.max());
    }
    @Test
    public void complexTest23(){

        MatzoDistributionBase mdb = new MatzoDistribution(this.source,30,this.sink);
        mdb.addWarehouse(vertex1,3);
        mdb.addWarehouse(vertex2,5);
        mdb.addWarehouse(vertex3,3);


        mdb.addWarehouse(vertex4,3);
        mdb.addWarehouse(vertex5,6);
        mdb.addWarehouse(vertex6,2);

        Assert.assertEquals(0,mdb.max());


        mdb.roadExists(source,vertex1,8);
        mdb.roadExists(source,vertex2,7);
        mdb.roadExists(source,vertex3,4);
        Assert.assertEquals(0,mdb.max());

        mdb.roadExists(vertex1,vertex2,2);
        mdb.roadExists(vertex1,vertex4,3);
        mdb.roadExists(vertex1,vertex5,9);
        Assert.assertEquals(0,mdb.max());

        mdb.roadExists(vertex2,vertex5,6);
        mdb.roadExists(vertex2,vertex3,4);
        mdb.roadExists(vertex4,sink,9);
        Assert.assertEquals(3,mdb.max());

        mdb.roadExists(vertex5,vertex4,3);
        Assert.assertEquals(3,mdb.max());
        mdb.roadExists(vertex5,sink,5);
        Assert.assertEquals(8,mdb.max());
        mdb.roadExists(vertex3,vertex5,7);
        mdb.roadExists(vertex3,vertex6,2);
        mdb.roadExists(vertex6,sink,8);
        mdb.roadExists(vertex5,vertex6,4);
        Assert.assertEquals(3+5+2,mdb.max());
    }



    /** Constructor: defines the two "endpoints" of the distribution network.
     *
     * @param sourceWarehouse names the warehouse that initiates the matzo
     * distribution, cannot be blank, must differ from destinationWarehouse.
     * @param sourceConstraint positive-valued-integer representing an upper
     * bound on the amount of matzo packages that can be distributed per day from
     * the source warehouse.
     * @param destinationWarehouse names the warehouse to which all matzos must
     * ultimately be delivered, cannot be blank, must differ from sourceWarehouse.
     * @throws IllegalArgumentException if the parameter pre-conditions are not
     * met.
     */
    @Test (expected = IllegalArgumentException.class)
    public void validationTest1(){
        MatzoDistributionBase mdb = new MatzoDistribution("",5,sink);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest2(){
        MatzoDistributionBase mdb = new MatzoDistribution(source,5,"");
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest3(){
        MatzoDistributionBase mdb = new MatzoDistribution("",5,"");
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest4(){
        MatzoDistributionBase mdb = new MatzoDistribution(source,0,sink);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest5(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        num *= -1;
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest6(){
        Random random = new Random();
        int num = random.nextInt();
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,source);
    }
    /** Adds a warehouse to the distribution network.
     *
     * @param warehouseId uniquely identifies the warehouse, cannot previously
     * have been added to the network, cannot be "blank".
     * @param constraint positive-valued-integer representing an upper bound on
     * the amount of matzo packages that can be distributed per day from that
     * warehouse.
     * @throws IllegalArgumentException if the parameter pre-conditions are not
     * met.
     */
    @Test (expected = IllegalArgumentException.class)
    public void validationTest7(){
        Random random = new Random();
        int num = random.nextInt();
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse("",num);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest8(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        num *= -1;
        mdb.addWarehouse(vertex3,num);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest9(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        num *= -1;
        mdb.addWarehouse("",num);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest10(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        num *= -1;
        mdb.addWarehouse("",num);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest11(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse("0",0);

    }
/** Specify that a road exists from warehouse1 to warehouse2 with a
 * constraint on the capacity of the road.
 *
 * @param w1 warehouse 1, must have already been added to the distribution
 * network, different from w2, cannot be blank.
 * @param w2 warehouse 2, must have already been added to the distribution
 * network, different from w1, cannot be blank.
 * @param constraint positive-valued-integer, representing an upper bound on
 * the amount of matzo packages per day that can be distributed on this road.
 * */

    @Test (expected = IllegalArgumentException.class)
    public void validationTest12(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.roadExists(vertex1,vertex2,5);

    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest13(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex3,5);
        mdb.roadExists(vertex1,vertex2,5);

    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest14(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex3,5);
        mdb.roadExists(vertex1,vertex2,5);

    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest15(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex1,5);
        mdb.roadExists(vertex1,vertex2,5);

    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest16(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex1,5);
        mdb.roadExists(sink,"",5);

    }

    @Test (expected = IllegalArgumentException.class)
    public void validationTest17(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex1,5);
        mdb.roadExists(sink,vertex1,0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest18(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex1,5);
        mdb.roadExists(sink,vertex1,-1);
    }
    @Test (expected = IllegalArgumentException.class)
    public void validationTest19(){
        Random random = new Random();
        int num = random.nextInt(1000000);
        MatzoDistributionBase mdb = new MatzoDistribution(source,num,sink);
        mdb.addWarehouse(vertex1,5);
        mdb.roadExists(sink,vertex1,10);
    }





}
