package edu.yu.introtoalgs;

import java.util.*;

public class QuestForOil extends QuestForOilBase {
    /**
     * Constructor supplies the map.
     *
     * @param map a non-null, N by M (not necessarily a square!), two-dimensional
     *            matrix in which each element is either an 'S' (safe) or a 'U' (unsafe) to
     *            walk on. It's the client's responsibility to ensure that the matrix isn't
     *            "jagged". The client relinquishes ownership to the implementation.
     */
    private final char[][] map;
    private boolean[][] visited;
    public QuestForOil(char[][] map) {
        super(map);
        if(map == null)
            throw new IllegalArgumentException();

        this.visited = new boolean[map.length][map[0].length];
        this.map = map;
    }

    /**
     * Specifies the initial "start the search" square, explore the map to find
     * the maximum number of squares contiguous to that square (including the
     * "start the search" square itself).
     * <p>
     * Note: the client is allowed to repeatedly invoke this method, e.g., with
     * different start search squares, on the same QuestForOil instance.
     *
     * @param row    the row of the initial "start the search" square, 0..N-1
     *               indexing.
     * @param column the column of the initial "start the search" square, 0..M-1
     *               indexing.
     * @return the maximum number of squares contiguous to the inital square.
     */
    @Override
    public int nContiguous(int row, int column) {
        if(this.map[row][column] == 'U')
            return 0;


        int contiguous = findContiguous(row,column);
        this.visited = new boolean[map.length][map[0].length];
        return contiguous;
    }



    private int findContiguous(int startRow, int startColumn) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startColumn});
        int contiguousCount = 0;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            contiguousCount++;

            this.visited[row][col] = true;


            if (canMoveNorth(row, col)) {
                stack.push(new int[]{row - 1, col});
                this.visited[row - 1][col] = true;
            }


            if (canMoveNorthEast(row, col)) {
                stack.push(new int[]{row - 1, col + 1});
                this.visited[row - 1][col + 1] = true;

            }
            if (canMoveEast(row, col)) {
                stack.push(new int[]{row, col + 1});
                this.visited[row][col + 1] = true;
            }


            if (canMoveSouthEast(row, col)) {
                stack.push(new int[]{row + 1, col + 1});
                this.visited[row + 1][col + 1] = true;
            }


            if (canMoveSouth(row, col)) {
                stack.push(new int[]{row + 1, col});
                this.visited[row + 1][col] = true;
            }


            if (canMoveSouthWest(row, col)) {
                stack.push(new int[]{row + 1, col - 1});
                this.visited[row + 1][col - 1] = true;
            }


            if (canMoveWest(row, col)) {
                stack.push(new int[]{row, col - 1});
                this.visited[row][col - 1] = true;
            }


            if (canMoveNorthWest(row, col)) {
                stack.push(new int[]{row - 1, col - 1});
                this.visited[row - 1][col - 1] = true;
            }
        }

        return contiguousCount;
    }
    private boolean canMoveNorth(int row, int column) {
        return row - 1 >= 0 && this.map[row - 1][column] == 'S' && !this.visited[row - 1][column];
    }

    private boolean canMoveSouth(int row, int column) {
        return row + 1 < this.map.length && this.map[row + 1][column] == 'S' && !this.visited[row + 1][column];
    }

    private boolean canMoveEast(int row, int column) {
        return column + 1 < this.map[row].length && this.map[row][column + 1] == 'S' && !this.visited[row][column + 1];
    }

    private boolean canMoveWest(int row, int column) {
        return column - 1 >= 0 && this.map[row][column - 1] == 'S' && !this.visited[row][column - 1];
    }

    private boolean canMoveNorthEast(int row, int column) {
        return row - 1 >= 0 && column + 1 < this.map[row].length && this.map[row - 1][column + 1] == 'S'
                && !this.visited[row - 1][column + 1];
    }

    private boolean canMoveNorthWest(int row, int column) {
        return row - 1 >= 0 && column - 1 >= 0 && this.map[row - 1][column - 1] == 'S' && !this.visited[row - 1][column - 1];
    }

    private boolean canMoveSouthEast(int row, int column) {
        return row + 1 < this.map.length && column + 1 < this.map[row].length && this.map[row + 1][column + 1] == 'S'
                && !this.visited[row + 1][column + 1];
    }

    private boolean canMoveSouthWest(int row, int column) {
        return row + 1 < this.map.length && column - 1 >= 0 && this.map[row + 1][column - 1] == 'S'
                && !this.visited[row + 1][column - 1];
    }
}
