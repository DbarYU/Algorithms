package edu.yu.introtoalgs;

import edu.yu.introtoalgs.OctopusTimeIt;

public class TimeSimulation {
    public static void main(String[] args){
        OctopusTimeIt timeIt = new OctopusTimeIt(Integer.parseInt(args[0]));
        timeIt.simulate();
}}
