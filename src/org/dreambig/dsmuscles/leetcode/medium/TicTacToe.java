package org.dreambig.dsmuscles.leetcode.medium;

public class TicTacToe {

    private int[][] board;

    boolean isWin(int player, int r, int c, int n) {
        int currentMove = player == 1 ? 1 : -1;
        rows[r] += currentMove;
        cols[c] += currentMove;
        if (r == c)
            diagonal += currentMove;

        if (c == (n - 1 - r))
            antiDiagonal += currentMove;

        return Math.abs(rows[r]) == n || Math.abs(cols[c]) == n || Math.abs(diagonal) == n
                || Math.abs(antiDiagonal) == n;


    }

    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int n;

    public TicTacToe(int n) {
        this.n = n;
        board = new int[n][n];
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    public int move(int row, int col, int player) {
        board[row][col] = player;
        return isWin(player, row, col, n) ? player : 0;
    }


}
