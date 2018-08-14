package hw4.puzzle;

public class Board {


    private int[][] puzzletiles;
    private int N;


    public Board(int[][] tiles) {

        N = tiles.length;

        puzzletiles = new int[N][N];



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                puzzletiles[i][j] = tiles[i][j];
            }
        }


    }
    public int tileAt(int i, int j) {

        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("i & j should be between 0 thru N - 1. ");
        }
        return puzzletiles[i][j];

    }



    public int size() {
        return N;
    }


    public int hamming() {
        int hamming = -1; //set to minus one since 0 is always counted as wrong position

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (puzzletiles[i][j] != N * i + j + 1) {
                    hamming += 1;
                }
            }
        }
        return hamming; //number of moves should be added later
    }


    public int manhattan() {

        int manhattan = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = puzzletiles[i][j] - 1;

                int dx = Math.abs(i - (value / size()));
                int dy = Math.abs(j - (value % size()));
                if (puzzletiles[i][j] != 0) { //ignore the 0's position
                    manhattan = manhattan + dx + dy;
                }
            }
        }

        return manhattan; //number of moves should be added later
    }

    public boolean isGoal() {


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) { //this would be the last for-loop
                    if (puzzletiles[i][j] == 0) {
                        return true;
                    }
                }

                if (puzzletiles[i][j] != N * i + j + 1) {
                    return false; //general cases
                }
            }
        }

        return true;
    }



    public boolean equals(Object y) {

        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (this.size() != that.size()) {
            return false;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (puzzletiles[i][j] != that.puzzletiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public int hashCode() {
        return 1;
    }

    /** Returns the string representation of the board. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int M = size();
        s.append(M + "\n");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
