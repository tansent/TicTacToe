package jtli.com.tictactoe_jingtian.pvp.model;

/**
 * Created by Jingtian(Tansent).
 *
 * Time Complexity: O(1)
 */

public class TicTacToe {

    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int N;

    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        N = n;
    }

    /**
     * The core function to judge a move.
     * @param row placed row position
     * @param col placed col position
     * @param player whose turn; 1 for player1, -1 for player2
     * @return
     * return 1 : player 1 wins; return -1: player 2 wins; return 0: no winner this round
     */
    public int move(int row, int col, int player) {
        rows[row] += player == 1 ? 1 : -1;
        cols[col] += player == 1 ? 1 : -1;
        if (row == col) diagonal += player == 1 ? 1 : -1;
        if (row + col + 1 == N) antiDiagonal += player == 1 ? 1 : -1;

        if ( Math.abs(rows[row]) == N || Math.abs(cols[col]) ==N || Math.abs(diagonal) == N || Math.abs(antiDiagonal) == N) {
            return player;
        }
        return 0;
    }

    /**
     * Clear all the setting for a new round
     */
    public void reset(){
        diagonal = 0;
        antiDiagonal = 0;
        rows = new int[N];
        cols = new int[N];
    }

}
