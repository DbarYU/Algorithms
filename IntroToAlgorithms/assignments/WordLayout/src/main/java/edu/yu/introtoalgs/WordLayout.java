package edu.yu.introtoalgs;

import java.util.*;

public class WordLayout extends WordLayoutBase {
    private Grid grid;

    private List<String> stringList;
    private Map<String,List<LocationBase>> locations;

    private boolean[][] locationsCovered;

    /**
     * Creates a grid with the specified number of rows and columns such that
     * every one of the supplied words are successfully layed out on the grid.
     * Conceptually, a Grid instance is created (with random letters assigned to
     * all Grid locations), and then overlayed with the list of words to create a
     * valid layout.  The rules for a valid layout are specified in the
     * requirements document.
     *
     * @param nRows    number of rows in 0..n-1 representation, must be a
     *                 non-negative integer.
     * @param nColumns number of columns in 0..n-1 representation, must be a
     *                 non-negative integer.
     * @param words    a non-null, non-empty list of words.  Client maintains ownership.
     * @throws IllegalArgumentException ers violate the specified
     *                                  requirements.if it's impossible to layout the words in
     *                                  *                                  the specified grid or if the supplied paramet
     */
    public WordLayout(int nRows, int nColumns, List<String> words) {
        super(nRows, nColumns, words);
        if(words == null)
            throw new IllegalArgumentException();

        setUp(nRows,nColumns,words);
        if (this.stringList == null) {
            throw new IllegalArgumentException();
        }
        if (this.stringList.contains(null)) {
            throw new IllegalArgumentException();
        }
        if (this.stringList.get(this.stringList.size() - 1).length() > nColumns && this.stringList.get(this.stringList.size() - 1).length() > nRows)
            throw new IllegalArgumentException();
        int size = 0;
        for (String word : this.stringList) {
            size += word.length();
        }
        if (size > nColumns * nRows)
            throw new IllegalArgumentException();
        if (!iterateThroughGrid()) {
            setUp(nRows,nColumns,this.stringList);
                if (!lookThroughGrid(0))
                    throw new IllegalArgumentException();
            }
        }
    private void setUp(int nRows, int nColumns, List<String> Strings){
        this.grid = new Grid(nRows, nColumns);
        this.stringList = new ArrayList<>(Strings);
        this.locationsCovered = new boolean[nRows][nColumns];
        this.locations = new HashMap<>();

    }
    private void putWord(String str, int[] coordinates, boolean down) {
        ArrayList<LocationBase> locationBases = new ArrayList<>();
        if (down) {
            for (int i = 0; i < str.length(); i++) {
                this.grid.grid[coordinates[0]][coordinates[1] + i] = str.charAt(i);
                this.locationsCovered[coordinates[0]][coordinates[1] + i] = true;
                locationBases.add(i,new LocationBase(coordinates[0],coordinates[1]+i));
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                this.grid.grid[coordinates[0] + i][coordinates[1]] = str.charAt(i);
                this.locationsCovered[coordinates[0] + i][coordinates[1]] = true;
                locationBases.add(i,new LocationBase(coordinates[0]+i,coordinates[1]));
            }
        }
       this.locations.put(str,locationBases);
    }


    /**
     * Returns the grid locations that specify how a word is layed out on the
     * grid.  The locations must be sorted in ascending row coordinate, breaking
     * ties if necessary, by sorting in ascending column coordinate.
     *
     * @param word
     * @return List of locations that specify how the word is layed out on the
     * grid.
     * @throws IllegalArgumentException if the word is not an element of the List
     *   supplied in the constructor.
     */
    @Override
    public List<LocationBase> locations(String word) {
        if (!this.stringList.contains(word))
            throw new IllegalArgumentException();

        return this.locations.get(word);
    }

    /**
     * Returns the Grid after it has been filled in with all words
     *
     * @returns Grid instance.
     */
    @Override
    public Grid getGrid() {
        return this.grid;
    }

    private boolean iterateThroughGrid() {
        for (int i = this.stringList.size()-1; i >= 0 ; i--) {
            String word = this.stringList.get(i);
            boolean wordHasBeenPut = false;
            for (int k = 0; k < this.grid.grid.length; k++) {
                if(wordHasBeenPut)
                    break;
                for (int z = 0; z < this.grid.grid[k].length; z++) {
                    if (checkIfSideWays(word.length(),new int[]{k,z})) {
                        putWord(word,new int[] {k,z},false);
                        wordHasBeenPut = true;
                        break;

                    } else if (checkIfDown(word.length(),new int[]{k,z})) {
                        putWord(word,new int[] {k,z},true);
                        wordHasBeenPut = true;
                        break;
                    }
                }
            }
            if(!wordHasBeenPut)
                return false;

        }
        return true;
    }

    private boolean checkIfSideWays(int strLength, int[] startingCoordinates) {
        try {
        for (int i = 0; i < strLength; i++) {
                if (this.locationsCovered[startingCoordinates[0] + i][startingCoordinates[1]])
                    return false;
            }
        } catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean checkIfDown(int strLength, int[] startingCoordinates) {
        try {
            for (int i = 0; i < strLength; i++) {
                if (this.locationsCovered[startingCoordinates[0]][startingCoordinates[1]+i])
                    return false;
            }
        } catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }


    private boolean lookThroughGrid(int numOfPlaces){
        if(numOfPlaces == this.stringList.size())
            return true;

        String word = this.stringList.get(numOfPlaces);

        for (int k = 0; k < this.grid.grid.length; k++) {
            for (int z = 0; z < this.grid.grid[k].length; z++) {
                if (checkIfSideWays(word.length(),new int[]{k,z})) {
                    putWord(word,new int[] {k,z},false);

                    if(lookThroughGrid(numOfPlaces+1))
                        return true;

                    removeWord(word,new int[] {k,z},false);
                } else if (checkIfDown(word.length(),new int[]{k,z})) {
                    putWord(word,new int[] {k,z},true);
                    if(lookThroughGrid(numOfPlaces+1) )
                        return true;
                    removeWord(word,new int[] {k,z},true);
                }
            }
        }
        //Reached the end without finding a spot for the word , that mean that means we need to find a
        return false;



    }

    private void removeWord(String str, int[] coordinates, boolean down) {
        if (down) {
            for (int i = 0; i < str.length(); i++) {
                this.grid.grid[coordinates[0]][coordinates[1] + i] = str.charAt(i);
                this.locationsCovered[coordinates[0]][coordinates[1] + i] = false;
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                this.grid.grid[coordinates[0] + i][coordinates[1]] = str.charAt(i);
                this.locationsCovered[coordinates[0] + i][coordinates[1]] = false;
            }
        }
        this.locations.remove(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordLayout that = (WordLayout) o;
        for (String str : this.stringList) {
            if (!that.stringList.contains(str)) {
                return false;
            }
        }
        return (this.grid.grid.length == that.grid.grid.length || this.grid.grid.length == that.grid.grid[0].length) ||
                (this.grid.grid[0].length == that.grid.grid.length|| this.grid.grid.length == that.grid.grid[0].length);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(this.stringList);
    }
}
