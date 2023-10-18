package org.dreambig.dsmuscles.topic.backtracking;

public class SudokuProblem {

    private static boolean isSafe(int r, int c, int[][] sol, int val){
        // checking row for same value
        for (int i=0;i<sol.length;i++){
            if(sol[i][c]==val) return false;
        }
        // Column
        for (int i=0;i<sol.length;i++){
            if(sol[r][i]==val) return false;
        }
        return true;

    }
    public static boolean fillSudoku( int [][]sol){

        // find zero
        int r=0 ,c=0;
        boolean bo=false;
        for ( r=0;r<sol.length;r++){
            bo=false;
            for ( c=0;c<sol[r].length;c++){
                if(sol[r][c]==0) {
                    bo=true;
                    break;
                }
            }
            if(bo) break;
        }

        if(r==sol.length && c==sol.length) return true; // reached last, sudoku are square

        for (int a=1;a<=sol.length;a++) {
            if (isSafe(r, c, sol, a)) {
                sol[r][c] = a;
                if (fillSudoku(sol)) return true;
                sol[r][c] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [][]sol= {{5,0,0,0,0},{0,0,5,0,2},{0,5,0,2,0},{4,0,1,0,0},{0,0,0,0,5}};
        if(fillSudoku(sol))
        {
            for (int[] ints : sol) {
                for (int j = 0; j < sol[0].length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println(" ");
            }
        }
        System.out.println("---");
    }
}
