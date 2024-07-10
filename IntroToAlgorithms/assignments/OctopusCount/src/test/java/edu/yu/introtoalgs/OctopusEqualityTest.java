package edu.yu.introtoalgs;

import java.util.Random;
import org.junit.Test;

import static edu.yu.introtoalgs.OctopusCountI.ArmColor.*;
import static edu.yu.introtoalgs.OctopusCountI.ArmTexture.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class OctopusEqualityTest
{
    OctopusCountI.ArmColor red = RED;
    OctopusCountI.ArmColor grey = GRAY;
    OctopusCountI.ArmColor black = BLACK;
    OctopusCountI.ArmTexture smooth = SMOOTH;
    OctopusCountI.ArmTexture sticky = STICKY;
    OctopusCountI.ArmTexture slimy = SLIMY;
    OctopusCountI.ArmTexture[] armTextures = {slimy,sticky,smooth};
    OctopusCountI.ArmColor[] armColors = {red,grey,black};


    /**
     * Rigorous Test :-)
     */
    @Test
    public void baseTest()
    {
        OctopusCount.Octopus octopus1 = new OctopusCount.Octopus (1,
                new OctopusCountI.ArmColor[]{GRAY, RED, BLACK, GRAY, RED, BLACK, GRAY, RED},
                new int[]{15, 20, 18, 15, 20, 18, 15, 20},
                new OctopusCountI.ArmTexture[]{SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY});

        OctopusCount.Octopus octopus2 = new OctopusCount.Octopus (1,
                new OctopusCountI.ArmColor[]{GRAY, RED, BLACK, GRAY, RED, BLACK, GRAY, RED},
                new int[]{15, 20, 18, 15, 20, 18, 15, 20},
                new OctopusCountI.ArmTexture[]{SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY});

        OctopusCount.Octopus octopus3 = new OctopusCount.Octopus (1,
                new OctopusCountI.ArmColor[]{GRAY, RED, BLACK, GRAY, RED, BLACK, GRAY, RED},
                new int[]{21, 20, 18, 15, 20, 18, 15, 15},
                new OctopusCountI.ArmTexture[]{SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY, STICKY, SMOOTH, SLIMY});

        OctopusCount.Octopus octopus4 = new OctopusCount.Octopus (1,
                new OctopusCountI.ArmColor[]{red, RED, BLACK, GRAY, RED, BLACK, GRAY, grey},
                new int[]{20, 20, 18, 15, 20, 18, 15, 15},
                new OctopusCountI.ArmTexture[]{slimy, SLIMY, STICKY, SMOOTH, SLIMY, STICKY, SMOOTH, smooth});


        assertEquals(octopus1, octopus2);
        assertNotEquals(octopus3,octopus2);
        assertEquals(octopus1,octopus4);



    }
    @Test
    public void test( ) {
        for(int i = 0; i < 100000; i ++) {
            if (i%2 == 0)
                generateIdenticalOctopus();
            else
                generateRandomOctopus();






}

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

    OctopusCount.Octopus octopus1 = new OctopusCount.Octopus(observationId1,armColors1,intArray,armTextures1);
    OctopusCount.Octopus octopus2 = new OctopusCount.Octopus(observationId2,armColors2,intArray2,armTextures2);

    assertEquals(octopus1,octopus2);

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

    OctopusCount.Octopus octopus1 = new OctopusCount.Octopus(observationId1,armColors1,intArray,armTextures1);
    OctopusCount.Octopus octopus2 = new OctopusCount.Octopus(observationId2,armColors2,intArray2,armTextures2);
    assertNotEquals(octopus1,octopus2);

}


}
