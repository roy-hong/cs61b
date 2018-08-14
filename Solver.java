package hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;




public class Solver {

    MinPQ<SearchNode> minheap;
    Board inputboard;


    SearchNode goalNode;
    int moves;



    int iteration = 0;


    public Solver(Board board) {

        inputboard = board;
        minheap = new MinPQ<>();
        minheap.insert(new SearchNode(board, null, 0));

        while (!minheap.min().getBoard().isGoal()) {
            //Repeat this procedure until the search node dequeued corresponds to a goal board.




            SearchNode currentNode = minheap.delMin();
            //Delete from the priority queue the search node with the minimum priority.


            for (Board child: BoardUtils.neighbors(currentNode.getBoard())) {
                //Insert onto the priority queue all neighboring search nodes

                if (currentNode.getPrevious() == null) { //base case for root node
                    minheap.insert(new SearchNode(child, currentNode, currentNode.getMoves() + 1));
                } else if (!child.equals(currentNode.getPrevious().getBoard())) {
                    minheap.insert(new SearchNode(child, currentNode, currentNode.getMoves() + 1));
                }

            }

        }

        if (minheap.min().getBoard().isGoal()) { //when it finds out a solution
            goalNode = minheap.min();
            moves = goalNode.getMoves();
        }

    }

    public class SearchNode implements Comparable<SearchNode> {

        private Board board;
        private SearchNode previous;
        private int moves;
        private int priority;

        public SearchNode(Board currentboard, SearchNode previousNode, int movestaken) {

            board = currentboard;
            previous = previousNode;
            moves = movestaken;
            priority = board.manhattan() + movestaken;
        }

        public SearchNode(Board currentboard, int movestaken) { //constructor for the root
            board = currentboard;
            previous = null;
            moves = movestaken;
            priority = board.manhattan() + movestaken;

        }


        public Board getBoard() {
            return this.board;
        }

        public SearchNode getPrevious() {
            return this.previous;
        }


        public int getMoves() {
            return this.moves;
        }


        public int compareTo(SearchNode o) { //comparing two Search Nodes

            //The lesser, the closer to the solution

            if (this.priority > o.priority) {
                return 1;
            } else if (this.priority == o.priority) {
                return 0;
            } else {
                return -1;
            }
        }
    }


    public int moves() {

        return moves; //Returns the minimum number of moves to solve the initial board
    }






    public Iterable<Board> solution() {

        LinkedList<Board> solutionSequence = new LinkedList<Board>();


        for (SearchNode i = goalNode; i != null; i = i.getPrevious()) {
            solutionSequence.addFirst(i.getBoard());
        }

        return solutionSequence;
    }




    // DO NOT MODIFY MAIN METHOD
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());


        for (Board board : solver.solution()) {
            StdOut.println(board);

        }




    }

}
