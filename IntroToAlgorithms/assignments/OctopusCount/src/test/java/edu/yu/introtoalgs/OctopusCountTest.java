package edu.yu.introtoalgs;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static edu.yu.introtoalgs.OctopusCountI.ArmColor.*;
import static edu.yu.introtoalgs.OctopusCountI.ArmTexture.*;
import static org.junit.Assert.assertEquals;


public class OctopusCountTest {
    OctopusCountI octopusCount;
    OctopusCountI.ArmColor red = RED;
    OctopusCountI.ArmColor grey = GRAY;
    OctopusCountI.ArmColor black = BLACK;
    OctopusCountI.ArmTexture smooth = SMOOTH;
    OctopusCountI.ArmTexture sticky = STICKY;
    OctopusCountI.ArmTexture slimy = SLIMY;
    OctopusCountI.ArmTexture[] armTextures = {slimy, sticky, smooth};
    OctopusCountI.ArmColor[] armColors = {red, grey, black};

    @Before
    public void setup() {
        octopusCount = new OctopusCount();
    }

    @Test
    public void countTest() {
        int k = 1000000;
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0){
                generateIdenticalOctopus();}
            else{
                generateRandomOctopus();}
        }
        assertEquals(k + k/2,octopusCount.countThem());

    }

    public void generateIdenticalOctopus(){
        Random rd = new Random(); // creating Random object
        int observationId1 = rd.nextInt(100);
        int observationId2 = rd.nextInt(100);
        int[] intArray = new int[8];
        int[] intArray2 = new int[8];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rd.nextInt(1000);// storing random integers in an array

        }
        OctopusCountI.ArmTexture[] armTextures1 = new OctopusCountI.ArmTexture[8];
        OctopusCountI.ArmColor[] armColors1 = new OctopusCountI.ArmColor[8];
        OctopusCountI.ArmTexture[] armTextures2 = new OctopusCountI.ArmTexture[8];
        OctopusCountI.ArmColor[] armColors2 = new OctopusCountI.ArmColor[8];

        for(int i = 0; i<8;i++){
            armColors1[i] =armColors[rd.nextInt(3)];
            armTextures1[i] = armTextures[rd.nextInt(3)];
        }

        intArray2[0] = intArray [3];
        armColors2[0] = armColors1[3];
        armTextures2[0] = armTextures1[3];
        intArray2[1] = intArray [6];
        armColors2[1] = armColors1[6];
        armTextures2[1] = armTextures1[6];
        intArray2[2] = intArray [5];
        armColors2[2] = armColors1[5];
        armTextures2[2] = armTextures1[5];
        intArray2[3] = intArray [2];
        armColors2[3] = armColors1[2];
        armTextures2[3] = armTextures1[2];
        intArray2[4] = intArray [1];
        armColors2[4] = armColors1[1];
        armTextures2[4] = armTextures1[1];
        intArray2[5] = intArray [7];
        armColors2[5] = armColors1[7];
        armTextures2[5] = armTextures1[7];
        intArray2[6] = intArray [0];
        armColors2[6]= armColors1[0];
        armTextures2[6] = armTextures1[0];
        intArray2[7] = intArray [4];
        armColors2[7] = armColors1[4];
        armTextures2[7] = armTextures1[4];

        this.octopusCount.addObservation(observationId1,armColors1,intArray,armTextures1);
        this.octopusCount.addObservation(observationId2,armColors2,intArray2,armTextures2);



    }
    public void generateRandomOctopus(){
        Random rd = new Random(); // creating Random object
        int observationId1 = rd.nextInt(100);
        int observationId2 = rd.nextInt(100);
        int[] intArray = new int[8];
        int[] intArray2 = new int[8];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rd.nextInt(1000);// storing random integers in an array
            intArray2[i] = rd.nextInt(1000);//

        }
        OctopusCountI.ArmTexture[] armTextures1 = new OctopusCountI.ArmTexture[8];
        OctopusCountI.ArmColor[] armColors1 = new OctopusCountI.ArmColor[8];
        OctopusCountI.ArmTexture[] armTextures2 = new OctopusCountI.ArmTexture[8];
        OctopusCountI.ArmColor[] armColors2 = new OctopusCountI.ArmColor[8];

        for(int i = 0; i<8;i++){
            armColors1[i] =armColors[rd.nextInt(3)];
            armTextures1[i] = armTextures[rd.nextInt(3)];
            armColors2[i] =armColors[rd.nextInt(3)];
            armTextures2[i] = armTextures[rd.nextInt(3)];
        }

        this.octopusCount.addObservation(observationId1,armColors1,intArray,armTextures1);
        this.octopusCount.addObservation(observationId2,armColors2,intArray2,armTextures2);

    }

}
