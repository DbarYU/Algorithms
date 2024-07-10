package edu.yu.introtoalgs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


public class TentacleEqualityTest {

    @Test
    public void unitEqualityTest() {
        OctopusCount.Tentacle tentacle1 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.GRAY, OctopusCountI.ArmTexture.SMOOTH, 15);
        OctopusCount.Tentacle tentacle2 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.GRAY, OctopusCountI.ArmTexture.SMOOTH, 15);
        OctopusCount.Tentacle tentacle3 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.GRAY, OctopusCountI.ArmTexture.SMOOTH, 14);
        OctopusCount.Tentacle tentacle4 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.RED, OctopusCountI.ArmTexture.SMOOTH, 15);
        OctopusCount.Tentacle tentacle5 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.GRAY, OctopusCountI.ArmTexture.SLIMY, 15);
        OctopusCount.Tentacle tentacle6 = new OctopusCount.Tentacle(OctopusCountI.ArmColor.BLACK, OctopusCountI.ArmTexture.STICKY, 92);

        assertEquals(tentacle1, tentacle2); // Tentacle 1 should be equal to Tentacle 2
        assertNotEquals(tentacle1, tentacle3); // Tentacle 1 should not be equal to Tentacle 3
        assertNotEquals(tentacle1, tentacle4); // Tentacle 1 should not be equal to Tentacle 4
        assertNotEquals(tentacle1, tentacle5); // Tentacle 1 should not be equal to Tentacle 5
        assertNotEquals(tentacle1, tentacle6); // Tentacle 1 should not be equal to Tentacle 6

        assertEquals(tentacle2, tentacle1); // Tentacle 2 should be equal to Tentacle 1
        assertNotEquals(tentacle2, tentacle3); // Tentacle 2 should not be equal to Tentacle 3
        assertNotEquals(tentacle2, tentacle4); // Tentacle 2 should not be equal to Tentacle 4
        assertNotEquals(tentacle2, tentacle5); // Tentacle 2 should not be equal to Tentacle 5
        assertNotEquals(tentacle2, tentacle6); // Tentacle 2 should not be equal to Tentacle 6

        assertNotEquals(tentacle3, tentacle1); // Tentacle 3 should not be equal to Tentacle 1
        assertNotEquals(tentacle3, tentacle2); // Tentacle 3 should not be equal to Tentacle 2
        assertNotEquals(tentacle3, tentacle4); // Tentacle 3 should not be equal to Tentacle 4
        assertNotEquals(tentacle3, tentacle5); // Tentacle 3 should not be equal to Tentacle 5
        assertNotEquals(tentacle3, tentacle6); // Tentacle 3 should not be equal to Tentacle 6


    }
}
