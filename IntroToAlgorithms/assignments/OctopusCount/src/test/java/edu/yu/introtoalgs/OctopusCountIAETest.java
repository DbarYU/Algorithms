package edu.yu.introtoalgs;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static edu.yu.introtoalgs.OctopusCountI.ArmColor.*;
import static edu.yu.introtoalgs.OctopusCountI.ArmTexture.*;


public class OctopusCountIAETest {
    OctopusCountI.ArmColor red = RED;
    OctopusCountI.ArmColor grey = GRAY;
    OctopusCountI.ArmColor black = BLACK;
    OctopusCountI.ArmTexture smooth = SMOOTH;
    OctopusCountI.ArmTexture sticky = STICKY;
    OctopusCountI.ArmTexture slimy = SLIMY;

    int[] invalidLengthArr1 = {1,2,3,4,5};
    int[] invalidLengthArr2 = {1,2,3,4,5,6,7,8,9};
    int[] invalidLengthArr3 = {1,2,3,4,5,6,7,-8};
    int[] validLength = {1,2,3,4,5,6,7,8};

    OctopusCountI.ArmTexture[] invalidTexture = {slimy,sticky,smooth,sticky};
    OctopusCountI.ArmTexture[] invalidTexture2 = {slimy,sticky,smooth,sticky,slimy,sticky,smooth,sticky,smooth};
    OctopusCountI.ArmTexture[] validTexture = {slimy,sticky,smooth,sticky,slimy,sticky,smooth,sticky};
    Random rnd = new Random();

    int validOID = rnd.nextInt(1000000000);
    int invalidOID = validOID*-1;

    OctopusCountI.ArmColor[] invalidColor = {red,black,grey,red};
    OctopusCountI.ArmColor[] invalidColor2 = {red,black,grey,red,red,red,red,black,red,black,red};
    OctopusCountI.ArmColor[] validColor = {red,black,grey,red,red,red,grey,black};

    @Test(expected = IllegalArgumentException.class)
    public void testIAE1(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(invalidOID,validColor,validLength,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE2(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,invalidColor,validLength,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE3(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,invalidColor2,validLength,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE4(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,validColor,invalidLengthArr1,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE5(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,validColor,invalidLengthArr2,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE6(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,validColor,invalidLengthArr3,validTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE7(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,validColor,validLength,invalidTexture);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIAE8(){
        OctopusCountI octopusCount1 = new OctopusCount();
        octopusCount1.addObservation(validOID,validColor,validLength,invalidTexture2);
    }


}
