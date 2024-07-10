package edu.yu.introtoalgs;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OctopusCount implements OctopusCountI {
    private Set<Octopus> octopusHashSet;
    
    public OctopusCount(){
        this.octopusHashSet = new HashSet<>();
    }

    public static class Octopus{

        //An octopi class represents a single octopus,
        //2 octopuses are equal, iff, their tentacles are identical,
        //each octupi has 8 tentacles.

        // flow of the solution would be as follows.
        // each obsetvation ID octopus that is added, is compared to an array of octopuses. If an octupu
        private final Tentacle[] tentacles;
        private final int observationID;

        public Octopus(int observationId, ArmColor[] colors, int[] lengthInCM, ArmTexture[] textures){
           this.tentacles = new Tentacle[8];
           this.observationID = observationId;
           for(int i =0; i < 8; i++){
                int length = lengthInCM[i];
                ArmTexture armTexture = textures[i];
                ArmColor armColor = colors[i];
                tentacles[i] = new Tentacle(armColor,armTexture,length);
            }

        }
        @Override
        public int hashCode() {
            int result = 0;
            for(int i =0; i < 8; i++){
                result = result  + 31*this.tentacles[i].hashCode();
            }
            return 97*result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Octopus octopus = (Octopus) o;
            Tentacle[] temp = new Tentacle[8];
            for(int z = 0; z<8;z++){
                temp[z] = this.tentacles[z];
            }
            for(int i =0; i<8; i++){
                Tentacle tentacle = octopus.tentacles[i];
                boolean isInOctopus = false;
                for(int k =0; k<8; k++){
                    if (temp[k]!=null && tentacle.equals(temp[k])) {
                        isInOctopus = true;
                        temp[k] = null;
                        break;
                    }
                }
                if (!isInOctopus)
                    return false;
            }
            return true;
        }
    }
    public static class Tentacle {
        private final ArmTexture armTexture;
        private final ArmColor armColor;
        private final int length;

        public Tentacle(ArmColor armColor, ArmTexture armTexture, int length){
            this.armColor = armColor;
            this.length = length;
            this.armTexture = armTexture;
        }

        public int getLength() {
            return length;
        }

        public ArmColor getArmColor() {
            return armColor;
        }

        public ArmTexture getArmTexture() {
            return armTexture;
        }


        @Override
        public int hashCode() {
            return 97*length * armColor.hashCode() * armTexture.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tentacle tentacle = (Tentacle) o;
            return this.length == tentacle.length && this.armTexture == tentacle.armTexture && this.armColor == tentacle.armColor;
        }
    }
    /**
     * A single octopus observation, consisting of a set of arrays (each of size
     * exactly N_ARMS), such that the ith element of each array describes the
     * characteristics of the ith arm of the observed octopus.
     *
     * @param observationId non-negative integer, uniquely labels the observation
     *                      (multiple observations can map to the same octopus),
     * @param colors        the color of the ith arm, not null, elements can't be null
     * @param lengthInCM    the length of the ith arm, not null, elements must be
     *                      positive integers
     * @param textures      the texture of the ith arm, not null, elements can't be
     *                      null
     * @throws IllegalArgumentException if any of the parameter conditions are
     *                                  violated: e.g., there aren't exactly N_ARMS values for each arm
     *                                  characteristic or if a lengthInCM value is not a positive integer.
     */
    @Override
    public void addObservation(int observationId, ArmColor[] colors, int[] lengthInCM, ArmTexture[] textures){
        validate(observationId,colors,lengthInCM,textures);
        this.octopusHashSet.add(new Octopus(observationId,colors,lengthInCM,textures));
    }

    /**
     * Returns the number of unique octopus instances from the set of current
     * observations.
     *
     * @return the number of unique instances.
     */
    @Override
    public int countThem() {
        return this.octopusHashSet.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OctopusCount count = (OctopusCount) o;
        return Objects.equals(octopusHashSet, count.octopusHashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(octopusHashSet);
    }

    private void validate(int observationId, ArmColor[] colors, int[] lengthInCM, ArmTexture[] textures){
        if(colors == null || textures == null || colors.length!= 8 || textures.length != 8||observationId < 0|| lengthInCM.length != 8)
            throw new IllegalArgumentException("Invalid parameter");

        for(int i = 0;i<lengthInCM.length; i++){
            if(lengthInCM[i] < 0)
                throw new IllegalArgumentException("Invalid parameter");

        }


    }
}
