package edu.yu.introtoalgs;

import static edu.yu.introtoalgs.OctopusCountI.ArmColor.*;
import static edu.yu.introtoalgs.OctopusCountI.ArmTexture.*;

public class OctopusTimeIt {
    OctopusCountI.ArmColor red = RED;
    OctopusCountI.ArmColor grey = GRAY;
    OctopusCountI.ArmColor black = BLACK;
    OctopusCountI.ArmTexture smooth = SMOOTH;
    OctopusCountI.ArmTexture sticky = STICKY;
    OctopusCountI.ArmTexture slimy = SLIMY;
    private int[][] lengths;
    private OctopusCountI.ArmTexture[] textures ={slimy,sticky,smooth,slimy,sticky,smooth,sticky,smooth};
    private  OctopusCountI.ArmColor[] armColors = {red,grey,black,red,grey,black,red,black};

    public OctopusTimeIt(int n){
        this.lengths = new int[n][8];
        generateOctopi(n);
    }



    public void simulateNotEqual(){
        OctopusCountI octopusCount = new OctopusCount();
        long start = System.currentTimeMillis();
        for(int i = 1; i <lengths.length+1; i++ ){
            octopusCount.addObservation(i,armColors,this.lengths[i-1],textures);
        }
        long end = System.currentTimeMillis();
        System.out.println("When n is: "+lengths.length);
        float sec = (end - start); System.out.println(sec + " MiliSeconds");
        assert(octopusCount.countThem()== lengths.length);

    }

    private  void generateOctopi(int n){
        for(int i = 0 ; i<n; i++){
            for(int k = 0; k<8;k++){
                this.lengths[i][k] = i;
            }


        }


    }
    public void generateEqual(){
        OctopusCountI octopusCount = new OctopusCount();
        long start = System.currentTimeMillis();
        for(int i = 1; i <lengths.length+1; i++ ){
            octopusCount.addObservation(i,armColors,this.lengths[1],textures);
        }
        long end = System.currentTimeMillis();
        System.out.println("When n is: "+lengths.length);
        float sec = (end - start); System.out.println(sec + " MiliSeconds");
        assert(octopusCount.countThem() == 1);
    }

    public void simulate(){
        OctopusCountI octopusCount = new OctopusCount();
        long start = System.currentTimeMillis();
        for(int i = 1; i <lengths.length+1; i++ ){
            if (i %2 == 0)
            octopusCount.addObservation(i,armColors,this.lengths[1],textures);

            else
                octopusCount.addObservation(i,armColors,this.lengths[i],textures);
        }
        long end = System.currentTimeMillis();
        System.out.println("When n is: "+lengths.length);
        float sec = (end - start); System.out.println(sec + " MiliSeconds");
        assert(octopusCount.countThem() == lengths.length/2);
    }

}
