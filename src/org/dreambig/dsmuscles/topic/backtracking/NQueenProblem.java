package org.dreambig.dsmuscles.topic.backtracking;

/***
 * Place N Queen in N X N matrix such that they don't attack each other
 */
public class NQueenProblem {

    private static boolean isSafe(int r, int c, int [][]mtx){
        // check if have any queen in row
        for (int i=0;i<=c;i++){
            if(mtx[r][i]==1)
                return false;
        }
        // checking Diagonal left to righ
        for (int i=r, j=c;i>=0&&j>=0;j--,i--){
            if(mtx[i][j]==1) return false;
        }
        // checking Diagonal right to left
        for (int i=r, j=c; i<mtx.length&&j>=0;i++,j--){
            if(mtx[i][j]==1) return false;
        }
        return true;
    }
    private  static  boolean canPlaceQueen(int c, int N, int[][] mtx){
        if(c==N)// all queens are placed
            return true;
        for (int i=0; i<N;i++){
            if (isSafe(i,c, mtx)){
                mtx[i][c]=1;
                if(canPlaceQueen(c+1,N,mtx)) return true;
                mtx[i][c]=0;
            }
        }
        return false;
    }




    public static boolean canPlaceQueen(int N){
        int [][] mtx = new int[N][N];
        boolean res=  canPlaceQueen(0, N, mtx);
        /*if(res){
            for (int[] ints : mtx) {
                for (int j = 0; j < mtx[0].length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println(" ");
            }
        }*/
        return res;

    }

    public static void main(String[] args) {
        for (int i=30;i<100;i++)
            System.out.println("for i="+i+" it is "+canPlaceQueen(i));
    }
}
