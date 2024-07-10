
import org.junit.Test;
import edu.yu.da.*;
import java.util.*;

import static org.junit.Assert.*;

public class ThereAndBackAgainTest {
    @Test
    public void smallScaleDoitSuccess() {
        String startingVertex = "StartVertex";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startingVertex);
        taba.addEdge(startingVertex, "a", 3);
        taba.addEdge(startingVertex, "b", 2);
        taba.addEdge("a", "b", 1);

        taba.addEdge("sca", "afds", 2);

        taba.doIt();


        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());


        assertEquals(taba.goalVertex(),"a");
        assertEquals(taba.goalCost(), 3, 0);

    }

    @Test
    public void smallScaleDoit2Success() {
        String startingVertex = "StartVertex";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startingVertex);
        taba.addEdge(startingVertex, "a", 3);
        taba.addEdge(startingVertex, "b", 2);
        taba.addEdge("a", "b", 1);
        taba.addEdge("d", "a", 3);
        taba.addEdge("sca", "afds", 2);

        taba.doIt();

        assertEquals(taba.goalVertex(), "d");
        assertEquals(taba.goalCost(), 6, 0);

        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());

        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void largeScaleTest1() {
        String startingVertex = "Start";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startingVertex);
        final int threshold = 200000;
        for (int i = 2; i <= threshold; i++) {
            taba.addEdge(startingVertex, String.valueOf(i), i);
        }
        taba.addEdge("sca", "afds", 2);

        taba.addEdge(String.valueOf(1), startingVertex, 3);
        taba.addEdge(String.valueOf(2), String.valueOf(1), 1);

        taba.doIt();

        assertEquals("1", taba.goalVertex());
        assertEquals(taba.goalCost(), 3, 0);

        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());

        System.out.println(result1);
        System.out.println(result2);


    }

    @Test
    public void largeScaleTest2() {
        String startingVertex = "Start";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startingVertex);
        final int threshold = 2000000;
        for (int i = 2; i <= threshold; i++) {
            taba.addEdge(startingVertex, String.valueOf(i), i);
        }
        taba.addEdge(String.valueOf(1), startingVertex, 3);
        taba.addEdge(String.valueOf(2), String.valueOf(1), 1);
        taba.addEdge("Goal", "1", 10);
        taba.addEdge("sca", "afds", 2);

        taba.doIt();

        assertEquals("Goal", taba.goalVertex());
        assertEquals(taba.goalCost(), 13, 0);

        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());

        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void largeScaleTest3() {
        int levels = 200000;
        final ThereAndBackAgainBase taba = generateBinaryTree(levels);
        int height = (int) (Math.log(levels * 2) / Math.log(2));
        taba.addEdge("1", String.valueOf(levels * 2 + 1), height);
        taba.addEdge("sca", "afds", 2);

        taba.doIt();
        assertEquals(taba.goalVertex(), String.valueOf(levels * 2 + 1));
        assertEquals(taba.goalCost(), height, 0);
        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());


        System.out.println(result1);
        System.out.println(result2);
    }

    @Test
    public void largeScaleTest4() {
        int levels = 200000;
        final ThereAndBackAgainBase taba = generateBinaryTree(levels);
        int height = (int) (Math.log(levels * 2) / Math.log(2));
        taba.addEdge("1", String.valueOf(levels * 2 + 1), height);
        taba.addEdge(String.valueOf(levels * 2 + 1), String.valueOf(levels * 2 + 1 + 1), 1);
        taba.addEdge("sca", "afds", 2);

        taba.doIt();
        assertEquals(taba.goalVertex(), String.valueOf(levels * 2 + 1 + 1));
        assertEquals(taba.goalCost(), height + 1, 0);
        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());

        System.out.println(result1);
        System.out.println(result2);
    }

    // Function to generate a binary tree with n levels
    public ThereAndBackAgainBase generateBinaryTree(int levels) {
        String startVertex = "1";
        ThereAndBackAgainBase taba = new ThereAndBackAgain(startVertex);


        for (int i = 1; i <= levels; i++) {
            int newLevel = 2 * i;
            taba.addEdge(String.valueOf(i), String.valueOf(newLevel), 1);
            taba.addEdge(String.valueOf(i), String.valueOf(newLevel + 1), 1);
        }
        System.out.println("DONE TESTING");


        return taba;
    }


    @Test
    public void errorTesting() {
        String startingVertex = "StartVertex";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startingVertex);
        taba.addEdge(startingVertex, "a", 3);
        taba.addEdge(startingVertex, "b", 2);
        taba.addEdge("a", "b", 1);
        taba.addEdge("sca", "afds", 2);
        taba.doIt();


    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest1() {
        //The two vertices must differ from one another
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v", "v", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest2() {
        //The two vertices must differ from one another
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("TESTING", "TESTING", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest3() {
        //an edge between the two  vertices cannot have been added previously.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("TESTING", "v", 3);
        taba.addEdge("v", "TESTING", 3);

    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest4() {
        //an edge between the two  vertices cannot have been added previously.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("TESTING", "v", 3);
        taba.addEdge("TESTING", "v", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validationTest5() {
        //an edge between the two  vertices cannot have been added previously.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("TESTING", "v", 3);
        taba.addEdge("TESTING", "v", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest6() {
        //specifies a vertex, length must be > 0.y.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("", "v", 3);
        taba.addEdge("TESTING", "gh", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest7() {
        //specifies a vertex, length must be > 0.y.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("d", "v", 3);
        taba.addEdge("TESTING", "", 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest8() {
        //specifies a vertex, length must be > 0.y.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("", "", 3);
        taba.addEdge("", "", 4);
    }
    @Test(expected = IllegalStateException.class)
    public void validationTest9() {
        //id Doit has been previously invoked IllegalStateException
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("d", "v", 3);
        taba.doIt();
        taba.addEdge("u", "v", 3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest10() {
        //specifies a vertex, length must be > 0.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validationTest11() {
        //specifies a vertex, length must be > 0.
        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",-1);
    }
    @Test
    public void validationTest12() {
        /**
         * If the graph contains a "goal vertex of the longest valid path" (as
         * defined by the requirements document), returns it.  Else returns null.
         *
         * @return goal vertex of the longest valid path if one exists, null
         * otherwise.
         */

        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",3);
        taba.doIt();
        assertNull(taba.goalVertex());
    }
    @Test
    public void validationTest13() {
        /**
         * Returns the cost (sum of the edge weights) of the longest valid path if
         * one exists, 0.0 otherwise.
         *
         * @return the cost if the graph contains a longest valid path, 0.0
         * otherwise.
         */

        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",3);
        taba.doIt();
        assertEquals(0,taba.goalCost(),0);
    }
    @Test
    public void validationTest14() {
        /**
         * If a longest valid path exists, returns a ordered sequence of vertices
         * (beginning with the start vertex, and ending with the goal vertex)
         * representing that path.
         * <p>
         * IMPORTANT: given the existence of (by definition) two longest valid paths,
         * this method returns the List with the LESSER of the two List.hashCode()
         * instances.
         *
         * @return one of the two longest paths, Collections.EMPTY_LIST if the graph
         * doesn't contain a longest valid path.
         */

        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",3);
        taba.doIt();
        assertEquals(Collections.emptyList(),taba.getOneLongestPath());
    }
    @Test
    public void validationTest15() {
        /**
         * If a longest valid path exists, returns a ordered sequence of vertices
         * (beginning with the start vertex, and ending with the goal vertex)
         * representing that path.
         * <p>
         * IMPORTANT: given the existence of (by definition) two longest valid paths,
         * this method returns the List with the LESSER of the two List.hashCode()
         * instances.
         *
         * @return one of the two longest paths, Collections.EMPTY_LIST if the graph
         * doesn't contain a longest valid path.
         */

        ThereAndBackAgainBase taba = new ThereAndBackAgain("TESTING");
        taba.addEdge("v","w",3);
        taba.doIt();
        assertEquals(Collections.emptyList(),taba.getOtherLongestPath());
    }

    @Test
    public void testGoalVertex() {
        ThereAndBackAgain graph = new ThereAndBackAgain("A");
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 3.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 4.0);
        graph.doIt();
        assertEquals("D", graph.goalVertex());

        List<String> result1 = graph.getOneLongestPath();
        List<String> result2 = graph.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());
    }

    @Test
    public void testGoalCost() {
        ThereAndBackAgain graph = new ThereAndBackAgain("A");
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 3.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 4.0);

        graph.doIt();

        assertEquals(7.0, graph.goalCost(), 0.001);
        List<String> result1 = graph.getOneLongestPath();
        List<String> result2 = graph.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());
    }

    @Test
    public void testOneLongestPath() {
        ThereAndBackAgain graph = new ThereAndBackAgain("A");
        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 3.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 4.0);
        graph.doIt();
        assertEquals(graph.goalCost(),7,0);
        assertEquals(graph.goalVertex(),"D");
        System.out.println(graph.goalCost());
        List<String> result1 = graph.getOneLongestPath();
        List<String> result2 = graph.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());
        System.out.println(result1);
        System.out.println(result2);
    }
    @Test
    public void testOtherLongestPath() {
        ThereAndBackAgain graph = new ThereAndBackAgain("A");

        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 3.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 4.0);

        graph.doIt();


        List<String> result1 = graph.getOneLongestPath();
        List<String> result2 = graph.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());


        System.out.println(result1);
        System.out.println(result2);
    }
    @Test
    public void testComplexGraph() {
        ThereAndBackAgain graph = new ThereAndBackAgain("0");
        graph.addEdge("0", "1", 40);
        graph.addEdge("0", "2", 2);
        graph.addEdge("0", "4", 3);
        graph.addEdge("4", "6", 2);
        graph.addEdge("6", "7", 5);
        graph.addEdge("2", "8", 5);
        graph.addEdge("8", "7", 3);
        graph.addEdge("7", "9", 40);
        graph.addEdge("5", "9", 40);
        graph.addEdge("1", "5", 40);
        graph.addEdge("6", "1", 40);

        graph.doIt();
        System.out.println(graph.goalVertex());
        System.out.println(graph.goalVertex());
        System.out.println(graph.getOneLongestPath());
        System.out.println(graph.getOtherLongestPath());
        List<String> result1 = graph.getOneLongestPath();
        List<String> result2 = graph.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());

    }
    @Test
    public void professorTest(){
        final String startVertex = "a";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startVertex);
        taba.addEdge(startVertex,"b" ,1.0 ) ;
        taba . addEdge ( "b" , "c" , 2.0 ) ;
        taba.doIt();
        assertNull(taba.goalVertex());
        assertEquals(taba.goalCost(),0,0);
        assertEquals(taba.getOneLongestPath(),Collections.<String>emptyList());
        assertEquals(taba.getOtherLongestPath(),Collections.<String>emptyList());
    }
    @Test
    public void largeScaleTest() {
        /**GetOneLongestPath
         *  If a longest valid path exists, returns a ordered sequence of vertices
         * (beginning with the start vertex, and ending with the goal vertex)
         * representing that path.
         *
         * IMPORTANT: given the existence of (by definition) two longest valid paths,
         * this method returns the List with the LESSER of the two List.hashCode()
         * instances.
         *
         * @return one of the two longest paths, Collections.EMPTY_LIST if the graph
         * doesn't contain a longest valid path.
         */
        final String startVertex = "1";
        final ThereAndBackAgainBase taba = new ThereAndBackAgain(startVertex);
        int scale = 200000;
        taba.addEdge("1", "2", 1.0);
        taba.addEdge("1", "3", 1.0);
        taba.addEdge("2", "4", 1.0);
        taba.addEdge("3", "4", 1.0);

        for (int i = 4; i < scale; i++) {
            taba.addEdge(String.valueOf(i), String.valueOf(i + 1), 1.0);
        }
        taba.doIt();
        assertEquals(taba.goalVertex(), String.valueOf(scale));
        assertEquals(taba.goalCost(), scale - 2, 0);
        List<String> result1 = taba.getOneLongestPath();
        List<String> result2 = taba.getOtherLongestPath();
        assertTrue(result1.hashCode()<result2.hashCode());
    }
}